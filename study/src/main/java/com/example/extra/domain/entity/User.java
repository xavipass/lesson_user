package com.example.extra.domain.entity;

import com.example.extra.domain.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "_users")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
// (access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @ApiModelProperty(example = "이메일", notes = "회원 이메일")
    @Column(nullable = false, unique = true, length = 50)
    private String email; // 이메일

    @ApiModelProperty(example = "비밀번호", notes = "회원 비밀번호")
    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @ApiModelProperty(example = "이름",notes = "회원 이름")
    @Column(nullable = false, length = 30)
    private String username; // 사용자 이름

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date join_time;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; //  KAKAO, GOOGLE

    private String socialId; // 로그인 한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    private String refreshToken; // 리프레시 토큰

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void updateUsername(String updateUsername) {
        this.username = updateUsername;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(updatePassword);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }


    public static User toUpdateUserEntity(UserDto userDto) {
        User userEntity = new User();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setUsername(userDto.getUsername());
        return userEntity;
    }

}
