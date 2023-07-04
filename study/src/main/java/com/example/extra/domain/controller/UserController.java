package com.example.extra.domain.controller;

import com.example.extra.domain.dto.UserDto;
import com.example.extra.domain.entity.User;
import com.example.extra.domain.service.MailService;
import com.example.extra.domain.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final MailService emailService;


    @ApiOperation(value = "user 등록")
    @GetMapping("/user/register")
    public String register(User user, Model model)  {
        model.addAttribute("userDto",new UserDto());
        return "register";
    }

    @ApiOperation(value = "user 등록")
    @PostMapping("/user/register")
    public String register(@Valid @RequestBody UserDto userDto,BindingResult bindingResult,
                           Model model) throws Exception {
        if(bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            System.out.println("회원가입에 실패하였습니다 다시 시도해주세요");
            return "register";
        }else{
            userService.register(userDto);
            System.out.println("AccountController.register");
            System.out.println("userDto = " + userDto);
            System.out.println("회원가입 성공 하였습니다");
        }
        return "회원가입 성공 하였습니다";

    }

    @ApiOperation(value = "user 로그인")
    @GetMapping("/user/login")
    public String loginForm() {

        return "login";
    }

    @ApiOperation(value = "user 로그인")
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDto userDto, HttpSession httpSession, BindingResult bindingResult) {
        UserDto loginResult = userService.login(userDto);
        if (loginResult != null) {
            //login 성공
            httpSession.setAttribute("email", loginResult.getEmail());
            httpSession.setAttribute("password", loginResult.getPassword());
            httpSession.setAttribute("username", loginResult.getUsername());
            return "로그인 성공 하였습니다";

        } else{
            //login 실패
            System.out.println("userDto = " + userDto + ", httpSession = " + httpSession);
            System.out.println("로그인에 실패하였습니다 , 다시 시도해 주세요");
            return "로그인 실패";
        }
    }

    @ApiOperation(value = "user 목록 전체 조회")
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> userEntityList = new ArrayList<>();

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

    @ApiOperation(value = "user 번호에 따라 조회")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> userDetail(@PathVariable int id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation(value = "user 번호에 따라 정보 수정")
    @PutMapping("/user/{id}/update")
    public ResponseEntity<UserDto> updateForm(@RequestBody UserDto userDto, @PathVariable("id") int id) {
        UserDto response = userService.updateForm(userDto, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "해당 id user 삭제")
    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        userService.delete(id);

        return ResponseEntity.ok("해당 사용자가 삭제 되었습니다");
    }

    @ApiOperation(value = "user 회원 가입시 인증번호 mail 발송")
    @PostMapping("/user/login/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }

    @ApiOperation(value = "user 로그아웃")
    @GetMapping("/user/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        System.out.println("로그아웃 완료 되었습니다");

        return "redirect:/";
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/jwt-test")
    public String jwtTest() {

        return "jwtTest 요청 성공";

    }

}
