package com.example.schedule.controller;

import com.example.schedule.dto.FindAllResponseDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.System.getLogger;

@RestController //데이터를 Json형태로
@RequestMapping("/Schedules")
public class ScheduleController {

    // Map 자료구조: DB 역할
    private final Map<Long, Schedule> scheduleList = new HashMap<>(); //초기화


    //#1. 일정 등록(createSchedule)
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestdto) {

        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;

        Schedule schedule = new Schedule(
                scheduleId,
                requestdto.getTitle(),
                requestdto.getContent(),
                requestdto.getUsername(),
                requestdto.getPassword()
        );

        scheduleList.put(scheduleId, schedule);  //key,저장데이터

        return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.CREATED);
    }



    // #2. 전체 일정 조회(findAllSchedule)
    @GetMapping
    public ResponseEntity<List<FindAllResponseDto>> findAllSchedule(
            @RequestParam(required = false) String updatedDate,
            @RequestParam(required = false) String username
    ) {
        List<FindAllResponseDto> findAllScheduleList = new ArrayList<>();

        for (Schedule schedule : scheduleList.values()) {
            boolean matches = true;

            // (1): 수정일이 있는 경우
            if (updatedDate != null && !schedule.getUpdatedDate().toLocalDate().toString().equals(updatedDate)) {
                matches = false;
            }

            // (2): 작성자명이 있는 경우
            if (username != null && !schedule.getUsername().equalsIgnoreCase(username)) {
                matches = false;
            }

            // 모두 충족하는 경우 -> findAllScheduleList에 추가
            if (matches) {
                FindAllResponseDto responseDto = new FindAllResponseDto(schedule);
                findAllScheduleList.add(responseDto);
            } else {
                return new ResponseEntity<>(findAllScheduleList, HttpStatus.NOT_FOUND);
            }
        }

        // 수정일 기준 내림차순 정렬
        findAllScheduleList.sort((a, b) -> b.getUpdatedDate().compareTo(a.getUpdatedDate()));

        return new ResponseEntity<>(findAllScheduleList, HttpStatus.OK);
    }



    //#3. 선택 일정Id 조회(choiceScheduleId)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> choiceScheduleId(@PathVariable Long id) {

        Schedule schedule = scheduleList.get(id);

        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
        }
    }



    // #4. 선택일정 수정(updatePartSchedule: 일부 수정)
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updatePartSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto
    ) {

        Schedule schedule = scheduleList.get(id);

            // 오류 검증
            if (schedule == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

            // 선택한 일정 내용 중 할일, 작성자명 만 수정 가능  != null(있으면 안된다)
            if (dto.getTitle() != null || dto.getPassword() == null ||
                    dto.getContent() == null || dto.getUsername() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

            if (!dto.getPassword().equals(schedule.getPassword())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            schedule.updatePart(dto);  // 수정일시가 변경되는 부분

            return new ResponseEntity<>(new ScheduleResponseDto(schedule), HttpStatus.OK);
        }



    // #4. 선택일정 삭제(Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id,
            @RequestParam Integer password
    ) {
        Schedule schedule = scheduleList.get(id);

        if (schedule.getPassword() != null && password.equals(schedule.getPassword())) {
            scheduleList.remove(id);

            return new ResponseEntity<>("정상: 선택된 일정이 삭제 되었습니다.", HttpStatus.OK);

        } else {

            return new ResponseEntity<>("오류: 선택된 일정의 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }


    }

}