package com.example.schedule.dto;

import lombok.Getter;

@Getter

public class ScheduleRequestDto {

    private String title; //일정 제목
    private String content; //할일
    private String username; //작성자명
    private Integer password; //비밀번호

}
