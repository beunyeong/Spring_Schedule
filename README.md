# Scheduler
## 1. API 명세서
| 진행현황 | 분류 | 기능 | Method | URL | request | response | 상태코드
|---|:---:|:---:|:---:|:------:|:----:|:---:|:------------|
| 완료 | schedules | 일정 등록 | POST | /schedules| body | 일정 등록 정보 | 201(Created), 없음 |
| 완료 | schedules | 전체 일정 조회 | GET | /schedules | Params | 다건 응답 정보 | 200(OK), 404(Not Found) |
| 완료 | schedules | 선택 일정 조회 | GET | /schedules/{id} | body 없음 | 단건 응답 정보 | 200(OK), 404(Not Found) |
| 완료 | schedules | 선택 일정 수정 | PUT | /schedules/{id} | body | 수정 정보 |  200(OK), 400(Bad Request), 401(Unauthorized) |
| 완료 | schedules | 선택 일정 삭제 | DELETE | /schedules/{id} | Params | 삭제 정보 | 200(OK), 401(Unauthorized) |

***


### 1) 일정등록
▼ Request Body
- Method : POST
- URL : /schedules


<예제>
<pre><code>	
{
  "title": "제목1",
  "content": "내용1",
  "username": "작성자1",
  "password": "3917"
}
</code></pre>

▼ Response Body
- HTTP 1.1 /201 Created
- Exception :  없음


<pre><code>	
{
    "id": 1,
    "title": "제목1",
    "content": "제목1",
    "username": "작성자1",
    "createdDate": "2024-11-05T18:44:42.8094417",
    "updatedDate": "2024-11-05T18:44:42.8094417"
}
</code></pre>

***

### 2) 전체 일정 조회
▼ Request Body
- Method : GET 
- URL : /schedules
- Params : /Schedules?updatedDate=2024-11-07


▼ Response Body
- HTTP 1.1 /200 OK
- Exception : 404 Not Found


<예제>
<pre><code>	
[
    {
        "id": 3,
        "title": "제목1",
        "content": "내용1",
        "username": "작성자1",
        "createdDate": "2024-11-07 15:19:30",
        "updatedDate": "2024-11-07"
    },
    {
        "id": 2,
        "title": "제목1",
        "content": "내용1",
        "username": "작성자1",
        "createdDate": "2024-11-07 15:19:29",
        "updatedDate": "2024-11-07"
    },
    {
        "id": 1,
        "title": "제목1",
        "content": "내용1",
        "username": "작성자1",
        "createdDate": "2024-11-07 15:19:29",
        "updatedDate": "2024-11-07"
    }
]
</code></pre>

***

### 3)  선택 일정 조회
▼ Request Body
- Method : GET 
- URL : /schedules/{id}
- Body : 없음


▼ Response Body
- HTTP 1.1 /200 OK
- Exception : 404 Not Found

<pre><code>	
{
    "id": 1,
    "title": "제목1",
    "content": "내용1",
    "username": "작성자1",
    "createdDate": "2024-11-05T19:55:27.6976584",
    "updatedDate": "2024-11-05T19:55:27.6976584"
}
</code></pre>

***

### 4)  선택 일정 수정
▼ Request Body
- Method : PATCH
- URL : /schedules/{id}

  
<예제> : 200 OK
<pre><code>	
   {
    "content": "수정된 내용2", 
    "username": "수정된 작성자2",  
    "password": "3917" 
   }
</code></pre>


<Exception 예제> : 400 Bad Request
<pre><code>	
   {
    "title": "제목2",  
    "content": "수정된 내용2", 
    "username": "수정된 작성자2",  
    "password": "3917" 
   }
</code></pre>


<Exception 예제> : 401 Unauthorized
<pre><code>	
   {
    "content": "수정된 내용2", 
    "username": "수정된 작성자2",  
    "password": "3918" 
   }
</code></pre>


▼ Response Body
- HTTP 1.1 /200 OK
- Exception : 400 Bad Request, 401 Unauthorized

  
<pre><code>	
{
    "id": 2,
    "title": "제목2",
    "content": "수정된 내용2",
    "username": "수정된 작성자2",
    "createdDate": "2024-11-05T20:47:14.0215951",
    "updatedDate": "2024-11-05T20:47:14.0215951"
}
</code></pre>

***

### 5)  선택 일정 삭제
▼ Request Body
- Method : DELETE
- URL : /schedules{id}
- Params : /Schedules/1?password=3917


▼ Response Body
- HTTP 1.1 /200 OK
- Exception : 401 Unauthorized

<pre><code>	
  {
  "정상: 선택된 일정이 삭제되었습니다.",
  }
</code></pre>


<pre><code>	
  {
  "오류: 선택된 일정의 비밀번호가 일치하지 않습니다.",
  }
</code></pre>


*** 

## 2. ERD
![image](https://github.com/user-attachments/assets/3f8b4204-f70a-4326-b3c0-7750f8939fe4)



*** 

## 3. SQL 쿼리 작성
**1) Create**

■ 필수 기능 가이드 개발에 필요한 테이블을 생성하는 query를 작성
<pre><code>	 
CREATE schedules users (
id INTEGER(30) NOT NULL,
password VARCHAR(20) NOT NULL,  
title VARCHAR(100) NOT NULL, 
content VARCHAR(100) NOT NULL, 
createdate DATETIME NOT NULL,
updateddate DATETIME NOT NULL,
username VARCHAR(20) NOT NULL
);    
</code></pre>

**2) Insert**

■  일정 생성을 하는 query를 작성
<pre><code>
INSERT INTO schedules (
"title": "LV0 과제 제출",
"content": "API명세서 작성하여 검사 받기",
"createdate": "now()",
"updateddate": "now()"
)
</code></pre>

**3) Select**

■ 전체 일정을 조회하는 query를 작성
<pre><code>
SELECT *
FROM schedules
ORDER BY createdate DESC;
</code></pre>

**4) Select**

■  선택 일정을 조회하는 query를 작성
<pre><code>
SELECT *
FROM schedules
WHERE id='1';
</code></pre>

**5) update**

■  선택한 일정을 수정하는 query를 작성
<pre><code>
UPDATE schedules
SET title='LV0 과제 검토' --> 할일,작성자명만 수정
WHERE title='LV0 과제 제출';
</code></pre>

**6) Delete**

■  선택한 일정을 삭제하는 query를 작성
<pre><code>
DELETE FROM users
WHERE id = 1;
</code></pre>

*** 

