package org.example.task3calender.challenge.repository;


import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.challenge.dto.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class SchedulerRepositoryImpl implements SchedulerRepository {

    private final JdbcTemplate jdbcTemplate;

    public SchedulerRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long createSchedule(CreateScheduleDTO dto) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedules_challenge") // INSERT 할 테이블
                .usingGeneratedKeyColumns("no") // AUTO_INCREASE 열 지정
                // Columns 명시하지 않으면 current_time cannot be null 오류 발생.
                .usingColumns("writer", "password", "start_time", "end_time", "title", "memo");

//        log.info(insert.getColumnNames().toString());

        Map<String, Object> params = new HashMap<>();
        params.put("writer", dto.getWriter());
        params.put("password", dto.getPassword());
        params.put("start_time", new Timestamp(dto.getStartTime()));
        params.put("end_time", new Timestamp(dto.getEndTime()));
        params.put("title", dto.getTitle());
        params.put("memo", dto.getMemo());

        return insert.executeAndReturnKey(params).longValue();
    }


    @Override
    public List<ResponseSchedulesDTO> getSchedules(RequestSchedulesDTO dto) {

        StringBuilder query = new StringBuilder("SELECT no, title, start_time, end_time FROM schedules_challenge " +
                "WHERE true");

        List<Object> params = new ArrayList<>();

        if (dto.getWriter() != null) {
            query.append(" AND writer = ?");
            params.add(dto.getWriter());
        }
        if (dto.getRevisionStart() != null){
            query.append(" AND revision_time > FROM_UNIXTIME(?)");
            params.add(dto.getRevisionStart());
        }
        if (dto.getRevisionEnd() != null) {
            query.append(" AND revision_time < FROM_UNIXTIME(?)");
            params.add(dto.getRevisionEnd());
        }
//        if (dto.getRevisionStart() != null){
//            query.append(" AND revision_time > ?");
//            params.add(dto.getRevisionStart());
//        }
//        if (dto.getRevisionEnd() != null) {
//            query.append(" AND revision_time < ?");
//            params.add(dto.getRevisionEnd());
//        }
        if (dto.getScheduleStart() != null){
            query.append(" AND ? BETWEEN start_time AND end_time");
            params.add(dto.getScheduleStart());
        }
        if (dto.getScheduleEnd() != null){
            query.append(" AND ? BETWEEN start_time AND end_time");
            params.add(dto.getScheduleEnd());
        }


        // 시나리오
        // 1. 기본 검색 : 전 범위 검색
        // 2. 조건 추가 : WHERE (AND)
        //  a. writer : writer IS ?
        //  b. revisionStart : revision_time > ?
        //  c. revisionEnd : revision_time < ?
        //  d. scheduleStart : ? BETWEEN start_time AND end_time
        //  e. scheduleEnd : ? BETWEEN start_time AND end_time

        PreparedStatementSetter psmts = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                for (int i=0; i < params.size(); i++){
                    ps.setObject(i+1, params.get(i));
                }
            }
        };

        return jdbcTemplate.query(query.toString(), psmts, (rs, rowNum) -> new ResponseSchedulesDTO(
                rs.getLong("no"),
                rs.getString("title"),
                rs.getTimestamp("start_time").getTime(),
                rs.getTimestamp("end_time").getTime()
        ));
    }

    @Override
    public ResponseScheduleDTO getSchedule(long l) {
        String query = "SELECT * FROM schedules_challenge WHERE no=?";

        PreparedStatementSetter psmts = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, l);
            }
        };

        List<ResponseScheduleDTO> result = jdbcTemplate.query(query, psmts, (rs, rowNum) -> {

            var dto = new ResponseScheduleDTO(
                    rs.getLong("no"),
                    rs.getString("title"),
                    rs.getString("memo"),
                    rs.getString("writer"),

                    // MySQL TIMESTAMP Default 단위 : 초
                    // Java / JS TIMESTAMP 단위 : 밀리초
                    // JDBC 자동 변환중
                    rs.getTimestamp("start_time").getTime(),
                    rs.getTimestamp("end_time").getTime(),
                    rs.getTimestamp("creation_time").getTime(),
                    rs.getTimestamp("revision_time").getTime()
            );

            return dto;
        });

        // query 정보가 없으면 return null
        return (result.isEmpty()) ? null : result.get(0);

    }

    @Override
    public Boolean patchSchedule(PatchScheduleDTO dto) {
        String query = "UPDATE schedules_challenge SET writer=?, title=?, memo=? WHERE no=? AND password=?";

        PreparedStatementSetter psmts = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, dto.getWriter());
                ps.setString(2, dto.getTitle());
                ps.setString(3, dto.getMemo());
                ps.setLong(4, dto.getNo());
                ps.setString(5,dto.getPassword());
            }
        };


        var result = jdbcTemplate.update(query, psmts);

        // query 정보가 없으면 return null
        return (result!=1) ? null : true;

    }

    @Override
    public Boolean deleteSchedule(DeleteScheduleDTO dto) {

        // 시나리오
        // 해당 no의 Row가 있는지 체크
        // 없다면 return null, 있다면 DELETE
        // DELETE 반환이 0이라면 false, 1이라면 true

        String query = "SELECT no FROM schedules_challenge WHERE no=? LIMIT 1";

        PreparedStatementSetter psmts = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, dto.getNo());
//                ps.setString(5,dto.getPassword());
            }
        };

        List<Boolean> result = jdbcTemplate.query(query, psmts, (rs, rowNum) -> true);

        if(result.isEmpty()) return null;

        query = "DELETE FROM schedules_challenge WHERE no=? AND password=?";

        psmts = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, dto.getNo());
                ps.setString(2, dto.getPassword());
            }
        };

        int result2 = jdbcTemplate.update(query, psmts);



        // query 정보가 없으면 return null
        return result2 == 1;



    }
}
