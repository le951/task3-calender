package org.example.task3calender.develop.service;


import lombok.extern.slf4j.Slf4j;
import org.example.task3calender.develop.config.PasswordEncoder;
import org.example.task3calender.develop.dto.request.CreateUserRequestDto;
import org.example.task3calender.develop.dto.request.LoginRequestDto;
import org.example.task3calender.develop.entity.User;
import org.example.task3calender.develop.exception.PasswordValidationException;
import org.example.task3calender.develop.repository.UserRepository;
import org.example.task3calender.develop.validator.PasswordValidator;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepo;

    UserService(UserRepository userRepo) { this.userRepo = userRepo; }

//    private final PasswordEncoder hashEncoder = new BCryptPasswordEncoder();
    private final PasswordEncoder hashEncoder = new PasswordEncoder();


    @Transactional
    public void createUser(CreateUserRequestDto dto){

        // 오류 검출 시 throw
        // front에서 미리 검출 하는 것을 가정함. 비정상 흐름이기에 자주 발생하지 않는 전제.
        // 자주 발생하는 경우 공격으로 간주하여 차단하는 것이 필요.
        PasswordValidator.validate(dto.getPassword());

        String password = hashEncoder.encode(dto.getPassword());

        User user = new User(dto.getAccount(), password);

        userRepo.save(user);

    }

    @Transactional
    public Long login(LoginRequestDto dto){

        var result = userRepo.findByAccount(dto.getAccount());

        if(result.isEmpty()) throw new IllegalArgumentException("존재하지 않는 계정입니다.");

        User user = result.get(0);

        if(!hashEncoder.matches(dto.getPassword(), user.getPassword())) throw new PasswordValidationException("비밀번호가 일치하지 않습니다.");

        return user.getId();

    }

}
