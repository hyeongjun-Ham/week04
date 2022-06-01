package com.sparta.assignment031.service;

import com.sparta.assignment031.domain.User;
import com.sparta.assignment031.dto.SignupRequestDto;
import com.sparta.assignment031.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // username 최소 3자 이상 알파벳,숫자가능
        String patternId = "^([a-zA-z\\d]{3,})$";
        if(!Pattern.matches(patternId,username)){
            throw new IllegalArgumentException("아이디를 다시 확인해 주세요");
//            new ResponseEntity<>("아이디를 다시 확인해 주세요", HttpStatus.BAD_REQUEST);
        }

        //password 최소 4자, 닉네임과 같은값 포함된 경우 회원가입 실패
        String patternPW = "^(.{4,})$";
        if (!Pattern.matches(patternPW,password)|password.contains(username)){
            throw new IllegalArgumentException("비밀번호 확인해 주세요");
        }

        // 패스워드 일치확인
        if (!password.equals(requestDto.getCheckPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        //중복 id 체크
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");

        }

        // 패스워드 암호화
        String encryptedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, encryptedPassword);
        userRepository.save(user);
    }

}
