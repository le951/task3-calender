## 과제 3-2 일정관리

### 핵심 기술

Gradle\
Spring Boot\
Spring Data JPA\
MySQL

<br>

### 개발 환경

InteliJ 2024.3.3\
springframework.boot 3.4.4\
Java SDK 17

<br>

### 테스트 방법

1. DB ID / Password 등록
   - Run Environment Variable 설정
   - DB_USERNAME=id;DB_PASSWORD=password
   - 또는 application.properties 수동 등록


2. 실행


3. HTTP Request\.html
   - Create Account -> Login -> JSESSIONID 확인 -> Create Schedule - Cookie에 입력 -> Create Schedule

<br>

### 구조 해설

Spring Boot와 JPA를 사용했습니다.\
아직 Spring Security는 배우지 않아 사용하지 않았고,\
대신 BCrypt를 추가하여 작업하였습니다.

Login 검증은 ServletFilter\
오류 처리는 @ExceptionHandler 사용하였습니다.


본래 목표는\
    1. User CRUD\
    2. Schedule CRUD\
    3. Comment CRUD\
    4. Schedule History CRUD

였으나\
User Create 와 Schedule Create 까지만 구현했습니다.


