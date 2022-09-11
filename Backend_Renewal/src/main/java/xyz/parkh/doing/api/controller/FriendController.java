package xyz.parkh.doing.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.service.FriendService;
import xyz.parkh.doing.domain.user.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 목록 조회
    @GetMapping
    public ResponseEntity getFriendList(String userInJwt) {
        List<User> friendList = friendService.getFriendList(userInJwt);

        return new ResponseEntity(null);
    }

    // 친구 신청 요청
    @GetMapping("/request/{targetId}")
    public ResponseEntity sendFriendRequest(@PathVariable String targetId,
                                            String userInJwt) {
        friendService.requestFriendApplication(userInJwt, targetId);
        return new ResponseEntity(null);
    }

    // 친구 신청 취소
    @GetMapping("/cancel/{targetId}")
    public ResponseEntity sendFriendRequestCancel(@PathVariable String targetId,
                                                  String userInJwt) {
        friendService.requestCancelFriendApplication(userInJwt, targetId);
        return new ResponseEntity(null);
    }


    @GetMapping("/request/{id}")
    public ResponseEntity responseRequest(@PathVariable Long id) {
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
