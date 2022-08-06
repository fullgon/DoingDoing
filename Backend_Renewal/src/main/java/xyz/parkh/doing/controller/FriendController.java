package xyz.parkh.doing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @GetMapping("/{id}")
    public ResponseEntity getFriendList(@PathVariable String id) {
        return new ResponseEntity(null);
    }

    @GetMapping("/{requesterId}/{targetId}")
    public ResponseEntity sendFriendRequest(@PathVariable String requesterId, @PathVariable String targetId) {
        // ?type = request, cancel
        // 친구 신청, 친구 신청 취소
        return new ResponseEntity(null);
    }

    @GetMapping("/request/{no}")
    public ResponseEntity responseRequest(@PathVariable Long no) {
        // ?answer=yes, no
        // 친구 신청 수락, 친구 신청 거절
        return new ResponseEntity(null);
    }

    @GetMapping("/requests/")
    public ResponseEntity getFriendRequestList() {
        // 들어온 친구 신청 목록 확인
        return new ResponseEntity(null);
    }
}
