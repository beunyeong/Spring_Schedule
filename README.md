# Scheduler
## API 명세서
### schedules
| 진행현황 | 분류 | 기능 | Method | URL | request | response | 상태코드
|---|:---:|:---:|:---:|:------:|:----:|:---:|:---:|
| 시작 전 | [회원 관리] | 회원 가입 | POST | /users | body | 회원가입 정보 | 200:정상 등록 |
| 시작 전 | [회원 관리] | 로그인 | POST | /users/login | body | 로그인 정보 | 200:정상 등록 |
| 시작 전 | [일정 관리] | 일정 등록 | POST | /schedules| body | 스케줄 등록 정보 | 200:정상 등록 |
| 시작 전 | [일정 관리] | 전체 일정 조회 | GET | /schedules | X | 다건 응답 정보 | 200:정상 조회 |
| 시작 전 | [일정 관리] | 선택 일정 조회 | GET | /schedules{schedule_id} | body | 단건 응답 정보 | 200:정상 조회 |
| 시작 전 | [일정 관리] | 선택 일정 수정 | PUT | /schedules{schedule_id} | body | 수정 정보 | 200:정상 수정 |
| 시작 전 | [일정 관리] | 선택 일정 삭제 | DELETE | /schedules{schedule_id} | body | 삭제 정보 | 200:정상 삭제 |


response(응답)

*** 
