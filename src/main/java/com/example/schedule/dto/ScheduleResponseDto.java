package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id; // 고유 식별자 ID(필수)
    private String title; //일정 제목
    private String content; //할일 (필수)
    private String username; //작성자명(필수)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //Format 변경
    private LocalDateTime createdDate; // 작성일
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  //Format 변경
    private LocalDateTime updatedDate;// 수정일
//    private Integer password; //반환 받은 일정 정보에 비밀번호는 제외(저장만 한다.)


    // ScheduleController에서 불러낼 생성자  --> return new ScheduleResponseDto(schedule);
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.createdDate = schedule.getCreatedDate();  // 작성일
        this.updatedDate = schedule.getUpdatedDate();  //수정일

    }
}
