■ Lv0. API 명세서 및 ERD, SQL 작성



1. API 명세서

[참고]

https://cobinding.tistory.com/165

https://blog.naver.com/dl8889/223160929578

https://beunyeong.tistory.com/41

진행 현황	분류	기능	Method	URL	request
(요청)	response
(응답)	상태코드
시작 전	[회원 관리]	회원 가입	POST	/users	body	　회원 가입 정보	200:
정상 등록 　
시작 전	[회원 관리]	로그인	POST	/users/login	body	로그인 정보	200:
정상 등록
시작 전	[일정 관리]	일정 등록	POST	/schedules	body	스케줄 등록 정보	200:
정상 등록
시작 전	[일정 관리]	전체 일정 조회	GET	/schedules	X	다건 응답 정보	200:
정상 조회
시작 전	[일정 관리]	선택 일정 조회	GET	/schedules{schedule_id}	body	단건 응답 정보	200:
정상 조회
시작 전	[일정 관리]	선택 일정 수정	PUT	/schedules{schedule_id}	　 body	수정 정보	200:
정상 수정
시작 전	[일정 관리]	선택 일정 삭제	DELETE	/schedules{schedule_id}	　 body	삭제 정보	200:
정상 삭제




2. ERD


3. SQL 쿼리 작성

https://blog.naver.com/jyani_log/223440146406

https://velog.io/@hiy7030/DB-Primary-key%EC%99%80-foreign-key

1) Create

■ 필수 기능 가이드 개발에 필요한 테이블을 생성하는 query를 작성

# Create users table
CREATE TABLE users (
user_name VARCHAR NOT NULL,
user_id INTEGER(30) PRIMARY KEY,
user_password VARCHAR(20) NOT NULL,
start_date DATETIME NOT NULL,
end_date DATETIME NOT NULL,
);

# Create schedules table
CREATE schedules users (
user_id INTEGER(30) NOT NULL,
schedule_id INTEGER NOT NULL, 
title VARCHAR(100) NOT NULL, 
content VARCHAR(100) NOT NULL, 
color VARCHAR NOT NULL, 
start_date DATETIME NOT NULL, 
end_date DATETIME NOT NULL, 
FOREIGN KEY (user_id) REFERENCES users (user_id)
);


2) Insert

■  일정 생성을 하는 query를 작성

# request(요청)

이름	타입	설명	필수
title	VARCHAR	일정 제목	O
content	VARCHAR	일정 내용	X
start_date	DATETIME	일정 시간일시	O
end_date	DATETIME	일정 종료일시	O
color	VARCHAR	일정 구분	X
INSERT INTO schedules (
title,
content,
start_date,
end_date,
color
)
VALUES(
'LV0 과제 제출'
'API명세서 작성하여 검사 받기'
'2024-11-01 14:00:00'
'2024-11-01 15:00:00'
'RED'
);


3) Select

■ 전체 일정을 조회하는 query를 작성

[참고]

https://blog.naver.com/jyani_log/223624227036

SELECT *
FROM schedules
ORDER BY start_date DESC;


4) Select

■  선택 일정을 조회하는 query를 작성

[참고]

https://beunyeong.tistory.com/2

SELECT *
FROM schedules
WHERE title='LV0 과제 제출';


5) update

■  선택한 일정을 수정하는 query를 작성

[참고]

https://blog.naver.com/gold5doo/223541476996
UPDATE schedules
SET title='LV0 과제 검토'
WHERE title='LV0 과제 제출';


6) Delete

■  선택한 일정을 삭제하는 query를 작성

[참고]

https://blog.naver.com/shimdh/223641392057

DELETE FROM users
WHERE user_id = 1;


