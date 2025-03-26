
# 이미 만들었다면 생략
CREATE SCHEMA task3;

USE task3;

# 테이블 이전
# RENAME TABLE task3_challenge.schedules TO task3.schedules_challenge;
# DROP DATABASE task3_challenge;

# 최초 테이블 생성.
CREATE TABLE schedules_challenge(
    no BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '식별자',
    title VARCHAR(100) NOT NULL COMMENT '일정 이름',
    memo TEXT NOT NULL COMMENT '세부 기록',
    start_time TIMESTAMP NOT NULL COMMENT '시작 시각',
    end_time TIMESTAMP NOT NULL COMMENT '종료 시각',
    writer VARCHAR(100) NOT NULL COMMENT '작성자',
    password VARCHAR(100) NOT NULL COMMENT '글 암호',
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시점',
    revision_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시점',

    INDEX idx_start_time(start_time),
    INDEX idx_end_time(end_time),
    INDEX idx_times(start_time, end_time),
    INDEX idx_revision_time(revision_time)
);

CREATE TABLE users(

);

# 필수 과제 테이블 테스트용 데이터
INSERT INTO schedules_challenge(title, memo, start_time, end_time, writer, password)
    VALUES ('테스트 1', '테스트 1 메모', '2025-03-25', '2025-03-26', 'Lee', '1'),
           ('테스트 2', '테스트 2 메모', '2025-03-24', '2025-03-24', 'H', '2'),
           ('테스트 3', '테스트 3 메모', '2025-03-27', '2025-03-27', 'H', '3');

INSERT INTO schedules_challenge(title, memo, start_time, end_time, writer, password)
VALUES ('테스트 4', '테스트 4 메모', '2025-03-25', '2025-03-26', 'Lee', '4'),
       ('테스트 5', '테스트 5 메모', '2025-03-24', '2025-03-24', 'H', '5'),
       ('테스트 6', '테스트 6 메모', '2025-03-27', '2025-03-27', 'H', '6');


INSERT INTO schedules_challenge(title, memo, start_time, end_time, writer, password)
VALUES ('테스트 7', '', '2025-03-26', '2025-03-26', 'Lee', '7');


# revision_time 작동 확인
UPDATE schedules_challenge SET writer = 'M' WHERE no=6;


# Query문 동작 테스트
SELECT *
FROM schedules_challenge
WHERE true AND writer = 'Lee'  AND revision_time > FROM_UNIXTIME(1) AND revision_time < FROM_UNIXTIME(2042878852)
    AND '2025-03-25 10:00:00' BETWEEN start_time AND end_time
    AND '2025-03-25 10:00:00' BETWEEN start_time AND end_time;

UPDATE schedules_challenge SET title = 'renewTitle' WHERE no=10 AND password='p1';
UPDATE schedules_challenge SET title = 'renew!', memo='renewMemo' WHERE no=10 AND password='p1';

DELETE FROM schedules_challenge WHERE no=13;

SELECT EXISTS (SELECT 1 FROM schedules_challenge WHERE no > 1);

SELECT no FROM schedules_challenge WHERE no=1 LIMIT 1;

# 검색 시 사용되는 start / end time들에 대해 INDEX 설정.
# 생성에서 추가했다면 실행 X.

# CREATE INDEX idx_start_time ON schedules_challenge (start_time);
# CREATE INDEX idx_end_time ON schedules_challenge (end_time);

# CREATE INDEX idx_times ON schedules_challenge (start_time, end_time);
# CREATE INDEX idx_revision_time ON schedules_challenge (revision_time);

# 설정 변경
ALTER TABLE schedules_challenge
    MODIFY COLUMN title VARCHAR(100) NOT NULL COMMENT '일정 이름',
    MODIFY COLUMN memo TEXT NOT NULL COMMENT '세부 기록',
    MODIFY COLUMN start_time TIMESTAMP NOT NULL COMMENT '시작 시각',
    MODIFY COLUMN end_time TIMESTAMP NOT NULL COMMENT '종료 시각',
    MODIFY COLUMN writer VARCHAR(100) NOT NULL COMMENT '작성자',
    MODIFY COLUMN creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '생성 시점',
    MODIFY COLUMN revision_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
      ON UPDATE CURRENT_TIMESTAMP comment '수정 시점',
    MODIFY COLUMN password VARCHAR(100) NOT NULL COMMENT '글 암호';

# PASSWORD Column 추가

# ALTER TABLE schedules_challenge
#     ADD COLUMN password VARCHAR(100) NOT NULL COMMENT '글 암호';


