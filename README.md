# Scheduler
## 1. API 명세서
### users / schedules
| 진행현황 | 분류 | 기능 | Method | URL | request | response | 상태코드
|---|:---:|:---:|:---:|:------:|:----:|:---:|:---:|
| 시작 전 | users | 회원 가입 | POST | /users | body | 회원가입 정보 | 201: 리소스 생성 |
| 시작 전 | schedules | 일정 등록 | POST | /schedules| body | 스케줄 등록 정보 | 201:리소스 생성 |
| 시작 전 | schedules | 전체 일정 조회 | GET | /schedules | X | 다건 응답 정보 | 200:정상 조회 |
| 시작 전 | schedules | 선택 일정 조회 | GET | /schedules/{id} | body | 단건 응답 정보 | 200:정상 조회 |
| 시작 전 | schedules | 선택 일정 수정 | PUT | /schedules/{id} | body | 수정 정보 | 200:정상 수정 |
| 시작 전 | schedules | 선택 일정 삭제 | DELETE | /schedules/{id} | body | 삭제 정보 | 200:정상 삭제 |

***

// request, response 어떤 값이 들어갈지 예제문제 만들기
### 1) 회원 가입
▼ request(요청) 예시
<pre><code>	
{
  "id" : "백*영",
  "password" : "0000"
}
</code></pre>

▼ response(응답) 예시
<pre><code>	
{
"id" : "001"
}
</code></pre>

### 2) 일정등록
▼ request(요청) 예시
<pre><code>	
{
  "id" : "001",
  "title" : "LV0 과제 제출",
  "content" : "API명세서 작성해서 검사 받기",
  "startDate" : "2024-11-01 14:00:00",
  "endDate" : "2024-11-01 15:00:00",
  "color" : "RED"
}
</code></pre>

▼ response(응답) 예시
<pre><code>	
{

}
</code></pre>


*** 

## 2. ERD
![image](https://github.com/user-attachments/assets/9fcd3568-758a-4a0f-a09d-dcd7fee0ebe3)

// user_id 보다는 그냥 id로 -> schedule_id보다는 그냥 id 로  => 테이블이 다르기 떄문에 괜찮
// user_name 보다는 그냥 name으로



*** 

## 3. SQL 쿼리 작성
**1) Create**

■ 필수 기능 가이드 개발에 필요한 테이블을 생성하는 query를 작성
<pre><code>	
# Create users table
CREATE TABLE users (
name VARCHAR(30) NOT NULL,
id INTEGER(30) PRIMARY KEY,
password VARCHAR(20) NOT NULL,
startDate DATETIME NOT NULL,
endDate DATETIME NOT NULL,
);
// start_date  -> startDate로 수정
// end_date -> endDate로 수정
  
# Create schedules table
CREATE schedules users (
id INTEGER(30) NOT NULL,
title VARCHAR(100) NOT NULL, 
content VARCHAR(100) NOT NULL, 
color VARCHAR(30) NOT NULL, 
startDate DATETIME NOT NULL, 
endDate DATETIME NOT NULL, 
FOREIGN KEY (id) REFERENCES users (id)
);    
</code></pre>

**2) Insert**

■  일정 생성을 하는 query를 작성
<pre><code>
INSERT INTO schedules (
title,
content,
startDate,
endDate,
color
)
VALUES(
'LV0 과제 제출',
'API명세서 작성하여 검사 받기',
'2024-11-01 14:00:00',
'2024-11-01 15:00:00',
'RED'
);
</code></pre>

**3) Select**

■ 전체 일정을 조회하는 query를 작성
<pre><code>
SELECT *
FROM schedules
ORDER BY startDate DESC;
</code></pre>

**4) Select**

■  선택 일정을 조회하는 query를 작성
<pre><code>
SELECT *
FROM schedules
WHERE title='LV0 과제 제출';
</code></pre>

**5) update**

■  선택한 일정을 수정하는 query를 작성
<pre><code>
UPDATE schedules
SET title='LV0 과제 검토'
WHERE title='LV0 과제 제출';
</code></pre>

**6) Delete**

■  선택한 일정을 삭제하는 query를 작성
<pre><code>
DELETE FROM users
WHERE id = 1;
</code></pre>

*** 

[일정관리 앱 만들기(1)] https://beunyeong.tistory.com/44


*** 
