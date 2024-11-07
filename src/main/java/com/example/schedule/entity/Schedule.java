package com.example.schedule.entity;

import com.example.schedule.dto.ScheduleRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class Schedule {

    private Long id; // 고유 식별자 ID
    private String title; //일정 제목
    private String content; //할일
    private String username; //작성자명
    private Integer password; //비밀번호
    private LocalDateTime createdDate; // 작성일
    private LocalDateTime updatedDate;  // 수정일


    // 생성자
    public Schedule(Long id, String title, String content, String username, Integer password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.password = password;
        this.createdDate = LocalDateTime.now();  // 작성일은 작성한 현재 일시로 설정
        this.updatedDate = createdDate;  // 처음에 수정일은 작성일과 동일하게 설정
    }


    // 선택한 일정 내용 중 할일, 작성자명 만 수정 가능
    public void updatePart(ScheduleRequestDto dto) {
        this.content = dto.getContent();
        this.username = dto.getUsername();
        this.updatedDate = LocalDateTime.now(); // 수정 시 수정일만 갱신
    }

}



