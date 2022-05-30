package xyz.parkh.doing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.ScheduleVo;
import xyz.parkh.doing.domain.model.ScheduleShortInfo;
import xyz.parkh.doing.domain.model.ScheduleStatusDto;
import xyz.parkh.doing.exception.DifferentAuthException;
import xyz.parkh.doing.exception.ValueNullException;
import xyz.parkh.doing.mapper.ScheduleMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;

    // 접근 제한의 경우 부정을 먼저

    // 일정 상세 조회
    public ResponseEntity<ScheduleVo> findSchedule(String userIdInJwt, String userId, Integer scheduleNo) {
        // 필수 인자가 없는 경우
        if (userIdInJwt == null && userId == null && scheduleNo == null) {
            throw new ValueNullException("필수 인자가 없습니다.");
        }

        // TODO 친구 기능 추가 시 권한 확인하는 코드 작성 필요
        // 현재는 본인 정보만 조회 가능.
        // 본인이 아닐 경우
        if (!userId.equals(userIdInJwt)) {
            throw new DifferentAuthException("접근 할 수 있는 권한이 없습니다.");
        }

        ScheduleVo schedule = scheduleMapper.selectByNo(scheduleNo);

        return ResponseEntity.ok().body(schedule);
    }

    public List<ScheduleShortInfo> findScheduleList(String userId, ScheduleStatusDto scheduleStatusDto) {
        List<ScheduleVo> scheduleList = scheduleMapper.selectByUserId(userId);
        Boolean isComplete = scheduleStatusDto.getIsComplete();
        Boolean isTimeOut = scheduleStatusDto.getIsTimeOut();

        ArrayList<ScheduleShortInfo> scheduleShortInfoList = new ArrayList<>();

        for (ScheduleVo schedule : scheduleList) {
            // TODO 완료하지 않고 기한이 지난 것은?

            // 완료 O, 기한 X, 기한 O
            if (isComplete) {

            } else if (isTimeOut) {
                // 완료 X, 기한 O

                // 완료 X, 기한 X

            }
        }

        return scheduleShortInfoList;
    }


    public ResponseEntity<List<ScheduleShortInfo>> findScheduleList(String userIdInJwt, String userId, ScheduleStatusDto scheduleStatusDto) {
        List<ScheduleShortInfo> scheduleList = findScheduleList(userId, scheduleStatusDto);

        return ResponseEntity.ok().body(scheduleList);
    }

    public Map<String, Object> addSchedule(String userIdInJwt, ScheduleVo scheduleVo) {

        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleVo);
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> modifySchedule(String userIdInJwt, String userId, ScheduleVo scheduleVo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", scheduleVo);
        jsonData.put("result", result);
        return jsonData;
    }

    public Map<String, Object> removeSchedule(String userIdInJwt, Integer scheduleNo) {
        HashMap<String, Object> jsonData = new HashMap<>();

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "result");
        result.put("message", "message");
        jsonData.put("result", userIdInJwt);
        return jsonData;
    }
}
