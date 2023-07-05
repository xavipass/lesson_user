package com.example.extra.domain.dto;

import com.example.extra.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// user 데이터를 userService로 전송
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private int id;

    private String email;

    private String password;

    private String username;

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
