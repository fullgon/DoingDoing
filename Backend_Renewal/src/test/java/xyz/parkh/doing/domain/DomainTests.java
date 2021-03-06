package xyz.parkh.doing.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.parkh.doing.domain.entity.ScheduleEntity;
import xyz.parkh.doing.domain.entity.UserEntity;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class DomainTests {

    @Autowired
    EntityManager em;

    @Test
    public void equalsToInsertAndFindSchedule() {
        UserEntity userEntity = UserEntity.builder().userId("PHJ").build();
        em.persist(userEntity);

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setUserEntity(userEntity);
        em.persist(scheduleEntity);

        ScheduleEntity findScheduleEntity = em.find(ScheduleEntity.class, scheduleEntity.getNo());
        Assert.assertTrue(scheduleEntity == findScheduleEntity); // 1차 캐시 때문에 == 가능
    }

    @Test
    public void setUserNameForScheduleInUser() {
        UserEntity userEntity = UserEntity.builder().userId("PHJ").build();
        em.persist(userEntity);

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setUserEntity(userEntity);
        em.persist(scheduleEntity);

        // DB 에 반영 되었는지 확인
        ScheduleEntity findScheduleEntity = em.find(ScheduleEntity.class, scheduleEntity.getNo());
        Assert.assertEquals(findScheduleEntity.getUserEntity(), userEntity);

        // 수정 된 값이 commit 되지 않아도 반영 되는지 확인
        // JPA 는 영속성 컨텍스트가 관리하는 1차 캐시가 존재해,
        // 같은 Tx 내에서 find 해 올 경우 아이디를 비교해
        // 1차 캐시에 있는 객체를 반환한다.
        UserEntity scheduleInUserEntity = findScheduleEntity.getUserEntity();
        scheduleInUserEntity.setName("parkh");
        UserEntity findUserEntity = em.find(UserEntity.class, scheduleInUserEntity.getNo());
        Assert.assertEquals(scheduleInUserEntity, findUserEntity);

        // 자바의 객체는 값을 그대로 복사하는 것이 아니라
        // 객체의 주소값을 넘겨주는 것이라 == 비교 가능
        System.out.println("findSchedule.getUser() = " + findScheduleEntity.getUserEntity());
        System.out.println("scheduleInUser = " + scheduleInUserEntity);
        Assert.assertTrue(findScheduleEntity.getUserEntity() == scheduleInUserEntity);
    }

}
