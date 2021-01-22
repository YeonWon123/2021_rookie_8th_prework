# ex2 : Maria 데이터베이스와 Spring Data JPA 연동

* 프로젝트 설정 : Gradle 프로젝트
* Group : 'org.zerock'
* Artifact : 'ex2'
* Packaging : 'war'
* JDK ver. : 11
* 프로젝트에 추가하는 의존성 항목 : Spring Boot DevTools, Lombok, Spring Web, Spring Data JPA
* build.gradle 파일의 dependencies 항목에 Maria JDBC 드라이버의 Gradle 관련 설정 추가
* application.properties 파일 내에 MariaDB 계정 정보 추가
* entity 패키지를 추가하고, Memo 클래스 정의
* JpaRepository 인터페이스 구현 (insert, select, update, delete) : MemoRepository, MemoRepositoryTests 클래스 구현
