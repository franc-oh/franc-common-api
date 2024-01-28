# 나만의 공통 API (Mybatis)
> API 어플리케이션 생성 시 참고할 나만의 표준을 개발

## 기능
### 1. 공통화
1. 글로벌 예외처리 + 예외처리 규격화
   - `exception.*`
2. 공통 Response
   - `common.response.*`
3. swagger 커스텀 어노테이션으로 공통화
   - `config.annotation.Swagger*`
4. ObjectMapper Date 타입 포맷팅
   - `ObjectMapperConfig`
5. MapStruct 적용
   - VO <-> DTO 데이터 교환
   - `account.controller.mapper.*`

### 2. 샘플코드
1. 나만의 API 표준 샘플
   - Request ~ Response
2. build.gradle 샘플
3. 테스트코드 샘플
4. 멀티 DB 연동
   - `config.datasource.*`
5. API swagger 명세 자동화
6. Mybatis-Config
7. log4j2, log4jdbc 설정
8. init data 적용
   - `classpath:init-data.h2db.*`

### 3. To-do
1. 공통 Message 및 국제화
2. 공통유틸 (Map, String, Date ...)

---

## 개발환경
* Jdk17
* Spring Boot 3.2.0
* Gradle 8.4
* H2db (mode=MySQL)
* Mybatis
* Lombok
* Swagger 2.3.0(openapi)
* Mapstruct
* JUnit5
* Log4j, Log4jdbc
