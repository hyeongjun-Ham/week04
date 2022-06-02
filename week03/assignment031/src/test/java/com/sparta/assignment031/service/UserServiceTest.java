package com.sparta.assignment031.service;

import com.sparta.assignment031.dto.SignupRequestDto;
import com.sparta.assignment031.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;


    @AfterEach
    public void afterEach() {
    }

    @Test
    @DisplayName("아이디 테스트")
    void idTest() {

        //given
        String username1 = "tes1234";
        String username2 = "ab";
        String password = "123456";
        String checkPassword = "123456";

        SignupRequestDto signupRequestDto1 = new SignupRequestDto(username1, password, checkPassword);
        SignupRequestDto signupRequestDto2 = new SignupRequestDto(username2, password, checkPassword);
        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        ResponseEntity result1 = userService.registerUser(signupRequestDto1);
        ResponseEntity result2 = userService.registerUser(signupRequestDto2);
        System.out.println("result = " + result1.getBody());
        System.out.println("result = " + result2.getBody());

        //then
        Assertions.assertThat(result1.getBody()).isEqualTo("회원가입 완료");
        Assertions.assertThat(result2.getBody()).isEqualTo("아이디를 다시 확인해 주세요");
    }

    @Test
    @DisplayName("비밀번호 테스트")
    void passwordTest() {

        //given
        String username = "tes1234";
        String password = "asd123";
        String checkPassword = "asd123";

        String password1 = "as1";
        String checkPassword1 = "as1";

        String password2 = "2tes12341";
        String checkPassword2 = "2tes12341";

        SignupRequestDto signupRequestDto = new SignupRequestDto(username, password, checkPassword);
        SignupRequestDto signupRequestDto1 = new SignupRequestDto(username, password1, checkPassword1);
        SignupRequestDto signupRequestDto2 = new SignupRequestDto(username, password2, checkPassword2);
        UserService userService = new UserService(userRepository, passwordEncoder);

        //when
        ResponseEntity result = userService.registerUser(signupRequestDto);
        ResponseEntity result1 = userService.registerUser(signupRequestDto1);
        ResponseEntity result2 = userService.registerUser(signupRequestDto2);
        System.out.println("result = " + result.getBody());
        System.out.println("result = " + result1.getBody());
        System.out.println("result = " + result2.getBody());

        //then
        Assertions.assertThat(result.getBody()).isEqualTo("회원가입 완료");
        Assertions.assertThat(result1.getBody()).isEqualTo("비밀번호 확인해 주세요");
        Assertions.assertThat(result2.getBody()).isEqualTo("비밀번호 확인해 주세요");
    }
    @Test
    @DisplayName("중복된 아이디 테스트")
    void duplicationTest() {


        //given
        String username1 = "tes1234";
        String username2 = "tes1234";
        String password = "123456";
        String checkPassword = "123456";

        SignupRequestDto signupRequestDto1 = new SignupRequestDto(username1, password, checkPassword);
        SignupRequestDto signupRequestDto2 = new SignupRequestDto(username2, password, checkPassword);
        UserService userService = new UserService(userRepository, passwordEncoder);

        ResponseEntity result1 = userService.registerUser(signupRequestDto1);
        System.out.println("result = " + result1.getBody());
        //when

        ResponseEntity result2 = userService.registerUser(signupRequestDto2);

        System.out.println("result = " + result2.getBody());

        //then
        Assertions.assertThat(result1.getBody()).isEqualTo("회원가입 완료");
        Assertions.assertThat(result2.getBody()).isEqualTo("중복된 사용자 ID가 존재합니다.");
    }

}
