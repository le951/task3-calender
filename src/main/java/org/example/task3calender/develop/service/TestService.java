package org.example.task3calender.develop.service;

import org.example.task3calender.develop.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    private final UserRepository userRepo;

    TestService(UserRepository repository){
        userRepo = repository;
    }



}
