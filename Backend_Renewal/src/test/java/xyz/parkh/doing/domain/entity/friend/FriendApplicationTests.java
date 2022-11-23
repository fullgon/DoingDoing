package xyz.parkh.doing.domain.entity.friend;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.friend.entity.FriendApplication;
import xyz.parkh.doing.domain.friend.model.FriendshipState;
import xyz.parkh.doing.domain.friend.repository.FriendRequestRepository;
import xyz.parkh.doing.domain.user.entity.User;
import xyz.parkh.doing.domain.user.repository.UserRepository;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FriendApplicationTests {
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @DisplayName("친구_신청")
    @Test
    public void friendApplication() {
        User park1 = User.builder().name("park1").build();
        User park2 = User.builder().name("park2").build();
        userRepository.save(park1);
        userRepository.save(park2);
        FriendshipState state = FriendshipState.REQUEST;
        FriendApplication friendApplication = new FriendApplication(park1, park2, state);
        friendRequestRepository.save(friendApplication);
        em.flush();
        em.close();

        FriendApplication findFriendApplication = friendRequestRepository.findById(friendApplication.getId()).orElseThrow();

        Assertions.assertThat(findFriendApplication).isEqualTo(friendApplication);
        Assertions.assertThat(friendApplication).isEqualTo(findFriendApplication);
    }


    @DisplayName("친구_신청_엔티티_equals_공부")
    @Test
    public void studyEquals() {
        User park1 = User.builder().name("park1").build();
        User park2 = User.builder().name("park2").build();
        FriendshipState state = FriendshipState.REQUEST;
        FriendApplication friendApplication = new FriendApplication(park1, park2, state);

        userRepository.save(park1);
        userRepository.save(park2);
        friendRequestRepository.save(friendApplication);

        em.flush();
        em.clear();

        // equals 를 잘 못 구현할 경우
        // DB 에서 값을 읽어오고, 기존 객체와 비교할 때 에러 발생함.
        // -> findBy~ 로 찾으려는 key 의 객체가 1차 캐시에 존재하지 않으면 값을 가져오는데,
        //    연관 관계가 있는 엔티티가 있는데 지연 로딩으로 설정 되어 있으면, 프록시로 가져온다.(빈 껍데기)
        FriendApplication findFriendApplication = friendRequestRepository.findById(friendApplication.getId()).orElseThrow();

        // 현재 original 은 영속성 컨텍스트가 관리 안하는 상태.
        System.out.println("original : friendRequest = " + friendApplication);
        Long originalId = friendApplication.getId();
        FriendshipState originalFriendshipState = friendApplication.getFriendshipState();
        User originalTarget = friendApplication.getTarget();
        User originalRequester = friendApplication.getRequester();

//        String originalName = originalTarget.name; // public 으로 바꿔도 객체이기 때문에 제대로 가져와짐
        String originalGetName = originalTarget.getName();

        // 현재 find 는 영속성 컨텍스트가 관리 하는 상태
        // -> target, Requester 이 지연로딩으로 설정 되어 있어,
        //    실제 객체가 아닌 빈 값인 프록시를 가져온다.
        System.out.println("proxy : findFriendRequest = " + findFriendApplication);
        Long findId = findFriendApplication.getId();
        FriendshipState findFriendshipState = findFriendApplication.getFriendshipState();
        User findTarget = findFriendApplication.getTarget();
        User findRequester = findFriendApplication.getRequester();

//        String findName = findTarget.name; // public 으로 바꿔서 가져와보면 프록시이기 때문에 null
        String findGetName = findTarget.getName();

        // 지연로딩이 아닌 값은 바로 가져오기 때문에 큰 이슈 없이 비교 가능
        boolean equalsId1 = originalId.equals(findId);
        boolean equalsId2 = findId.equals(originalId);

        //  -> equals 구현시 get~으로 가져오니 true 가 됐다?
        // 아 지연로딩으로 가져오니까 필드 값에 직접 접근하면 null 이고,
        // get 으로 가져오니까 값이 가져와지네

        /**
         * equals 를 잘 못 구현할 경우. 아래와 같은 결과 발생
         * 실제 User 를 가져오는 것이 아닌 프록시를 가져오는데,
         * 프록시에서 필드는 비어 있어,
         * 프록시.field = null 을 가져오고
         * 프록시.getField() 를 해야 제대로 된 값을 가져온다.
         *
         // original.equals(proxy) false
         boolean equalsTarget1 = originalTarget.equals(findTarget);

         // proxy.equals(original) true
         boolean equalsTarget2 = findTarget.equals(originalTarget);
         */

        boolean equalsRequester1 = originalRequester.equals(findRequester);
        boolean equalsRequester2 = findRequester.equals(originalRequester);

        // 프록시.equals(진짜객체) : True -> 진짜 객체, 프록시 차이가 아니라 지연로딩으로 null 값이 있냐 없냐 차이였음
        System.out.println(findFriendApplication.equals(friendApplication));

        // 진짜객체.equals(프록시) : False
        System.out.println(friendApplication.equals(findFriendApplication));

        Assertions.assertThat(friendApplication).isEqualTo(findFriendApplication);
        Assertions.assertThat(findFriendApplication).isEqualTo(friendApplication);
    }
}
