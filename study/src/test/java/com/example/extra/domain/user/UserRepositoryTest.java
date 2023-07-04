package com.example.extra.domain.user;

import com.example.extra.domain.entity.User;
import com.example.extra.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
@Commit
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private User user;


    @DisplayName("1. 고객 정보 저장 테스트")
    @Test
    public void getSaveTest(){
        User userSave = new User();
        userSave.setId(Math.toIntExact(Long.valueOf(12L)));
        userSave.setUsername("yong");
        userSave.setPassword("6666");
        userSave.setEmail("gqiew@gmail.com");
        userRepository.save(userSave);
        System.out.println("UserRepositoryTest.getSaveTest");

    }



    @DisplayName("2. 고객 조회 테스트")
    @Test
    public void getSelectTest(){
//        List<UserEntity> userEntityList = UserRepository.findAll();
//        userRepository.findByUserId(userEntity.getUserId());
//        assertThat(userRepository);
        // 해당 userId 번호 조회
        Long userId = 3L;
        Optional<User> selectUserEntity = userRepository.findById(userId);

        System.out.println("==========================");

        if(selectUserEntity.isPresent()){
            User user = selectUserEntity.get();
            System.out.println(user);
        }else{
            System.out.println("해당 사용자가 없습니다, 다시 검색하여 주십시요");
        }
    }

    @DisplayName("3. 고객 정보 수정 테스트")
    @Test
    public void getUpdateTest(){
        User updateUser = User.builder().id(12).email("saawq@naver.com").password("ooready581$")
                .id(Math.toIntExact(Long.valueOf(12))).username("bigCat").build();
        // Entity 명.builder().userId().email().password().loginId().name().build();
        userRepository.save(updateUser);
        System.out.println(userRepository.save(updateUser));
    }

    @Test
    @DisplayName("4. 고객 정보 삭제 테스트")
    public void getDeleteTest() {
        userRepository.deleteAll();
    }
}
