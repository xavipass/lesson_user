package com.example.extra.domain.service;

import com.example.extra.domain.dto.UserDto;
import com.example.extra.domain.entity.Role;
import com.example.extra.domain.entity.User;
import com.example.extra.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(UserDto userDto) throws Exception {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new Exception("이미 존재하는 이름 입니다.");
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }


    public UserDto login(UserDto userDto) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<User> byUsername = userRepository.findByUsername((userDto.getUsername()));
        if (byUsername.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            User user = byUsername.get();
                if (user.getPassword().equals(userDto.getPassword())){
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                UserDto dto = UserDto.toUserDto(user);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                    System.out.println("비밀번호를 다시 확인 해주세요");
                return null;
            }
        } else {
            System.out.println("해당 이메일 조회 결과가 없습니다");
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    public List<UserDto> getAllUser() {
        List<User> userEntity = userRepository.findAll();

        return userEntity.stream().map(p ->mapToDto(p)).collect(Collectors.toList());
    }

    public UserDto getUserById(int id) {
        User userEntity = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
        return mapToDto(userEntity);
    }

    public UserDto updateForm(UserDto userDto, int id) {
        User userEntity = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("사용자가 업데이트 되지 않았습니다"));

        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());

        User updateUser = userRepository.save(userEntity);
        return mapToDto(updateUser);

    }

    public void delete(int id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("사용자를 삭제 할 수 없습니다"));
        userRepository.delete(user);

    }

    private UserDto mapToDto(User userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setUsername(userEntity.getUsername());
        return userDto;
    }

}