package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@AllArgsConstructor   // -> id ~ 작성일까지 생성자를 만든다. 필수값 체크는 직접 하는 것이 좋다

public class Schedule {

    private Long id; // 고유 식별자 ID
    private String title; //일정 제목
    private String content; //할일
    private String username; //작성자명
    private Integer password; //비밀번호
    private LocalDateTime createdDate; // 작성일
    private LocalDateTime updatedDate;  // 수정일
    //★★★★★ 트러블 슈팅 내용 -> 일정 생성시 오류가 났음
    // LocalDateTime.now()          //현재 시간으로 작성일과 수정일 설정을 해야 하는데 ->     private LocalDateTime updatedDate;// 수정일을 입력 해놔서 오류가 남.
    // 이유: 현재 시간을 작성일과 동일하게 해야 하기 때문에


    // 생성자
    public Schedule(Long id, String title, String content, String username, Integer password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.password = password;
        this.createdDate = LocalDateTime.now();  // --> 생성은 밖에서 넘겨주지 않아도 됨, 여기서 처리
        this.updatedDate = LocalDateTime.now();
    }

    // 일정을 수정할 경우 사용하는 update 메소드를 생성한다.
    public void update(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.updatedDate = LocalDateTime.now();  // 수정 시 수정일만 갱신

    }

    // 선택한 일정 내용 중 할일, 작성자명 만 수정 가능
    public void updatePart(ScheduleRequestDto dto) {
        this.content = dto.getContent();
        this.username = dto.getUsername();
        this.updatedDate = LocalDateTime.now(); // 수정 시 수정일만 갱신
    }


}