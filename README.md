# Scheduler
## 1. API 명세서
### users / schedules
| 진행현황 | 분류 | 기능 | Method | URL | request | response | 상태코드
|---|:---:|:---:|:---:|:------:|:----:|:---:|:------------|
| 시작 전 | schedules | 일정 등록 | POST | /schedules| body | 일정 등록 정보 | 201:리소스 생성 |
| 시작 전 | schedules | 전체 일정 조회 | GET | /schedules | X | 다건 응답 정보 | 200:정상 조회, 404:지정 리소스 없음 |
| 시작 전 | schedules | 선택 일정 조회 | GET | /schedules/{id} | X | 단건 응답 정보 | 200:정상 조회, 404:지정 리소스 없음 |
| 시작 전 | schedules | 선택 일정 수정 | PUT | /schedules/{id} | body(수정) | 수정 정보 | 200:정상 수정, 401: 권한없음 |
| 시작 전 | schedules | 선택 일정 삭제 | DELETE | /schedules/{id} | X | 삭제 정보 | 200:정상 삭제, 401: 권한없음|

***


### 1) 일정등록
▼ request(요청) 예시
<pre><code>	
{
  "title":"LV0 과제 제출",
  "content":"API명세서 작성해서 검사 받기",
  "create_date":now(),
  "updated_date":now()
}
</code></pre>

▼ response(응답) 예시 : HTTP 1.1 /201 Created
<pre><code>	
{
  "id" : "001"
}
</code></pre>

### 2) 전체 일정 조회
▼ request(요청) : GET /schedules


▼ response(응답) 예시
<pre><code>	
  "schedules" [ {
  "id" : "t01",
  "id" : "001",
  "title" : "LV0 과제 제출",
  "content" : "API명세서 작성해서 검사 받기",
  "create_date" : "2024-11-01 14:00:00", ----> 조회를 했을 때 
  "endDate" : "2024-11-01 15:00:00"
  },
  {
  "id" : "t02",
  "id" : "001",
  "title" : "일정 관리 앱 만들기",
  "content" : "구현 완료",
  "startDate" : "2024-11-08 14:00:00",
  "endDate" : "2024-11-08 15:00:00",
  "color" : "RED" 
  } ]
</code></pre>

### 4)  전체 일정 조회
▼ request(요청) X : GET /schedules/{id}

▼ response(응답) 예시
<pre><code>	
  {
  "id" : "t02",
  "id" : "001",
  "title" : "일정 관리 앱 만들기",
  "content" : "구현 완료",
  "startDate" : "2024-11-08 14:00:00",
  "endDate" : "2024-11-08 15:00:00",
  "color" : "RED" 
  }
</code></pre>

### 5)  선택 일정 수정
▼ request(요청) X : GET schedules{id}

▼ response(응답) 예시
<pre><code>	
  {
  "title" : "LV0 과제 피드백 받기",
  "content" : "API명세서 작성하기"
  }
</code></pre>

### 6)  선택 일정 삭제
▼ request(요청) X : DELETE /schedules/{id}

▼ response(응답) 예시
<pre><code>	
  {
  "id" : "t02"
  }
</code></pre>


*** 

## 2. ERD
![image](https://github.com/user-attachments/assets/fdea908e-e0b0-4b45-a6bb-ad658840903b)



*** 

## 3. SQL 쿼리 작성
**1) Create**

■ 필수 기능 가이드 개발에 필요한 테이블을 생성하는 query를 작성
<pre><code>	
# Create users table
CREATE TABLE users (
id INTEGER(30) PRIMARY KEY,
password VARCHAR(20) NOT NULL,
startDate DATETIME NOT NULL,
endDate DATETIME NOT NULL,
);
  
# Create schedules table
CREATE schedules users (
id INTEGER(30) NOT NULL,
title VARCHAR(100) NOT NULL, 
content VARCHAR(100) NOT NULL, 
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
)
VALUES(
'LV0 과제 제출',
'API명세서 작성하여 검사 받기',
now(),
now()
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
