package com.example.extra.domain.controller;

import com.example.extra.domain.dto.StudyRoomDto;
import com.example.extra.domain.entity.PageResponse;
import com.example.extra.domain.entity.StudyRoom;
import com.example.extra.domain.service.StudyRoomService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    @ApiOperation(value = "", notes = "")
    @GetMapping("/user/create/studyRoom")
    public String createStudyRoom(StudyRoom studyRoom, Model model){
        model.addAttribute("studyRoomDto",new StudyRoomDto());
        return "/studyRoom";
    }

    @ApiOperation(value = "", notes = "")
    @PostMapping("/user/create/studyRoom")
    public String studyRoom(@Valid @RequestBody StudyRoomDto studyRoomDto, BindingResult bindingResult,
                            Model model) throws Exception {
        if(bindingResult.hasErrors()) {
            model.addAttribute("studyRoomDto",studyRoomDto);
            System.out.println("강의실 개설에 실패하였습니다 다시 시도해주세요");
            return "studyRoom";
        }else {
            studyRoomService.registerStudyRoom(studyRoomDto);
            System.out.println("StudyRoomController.registerStudyRoom");
            System.out.println("studyRoomDto = " + studyRoomDto);
            System.out.println("강의실 개설에 성공 하였습니다");
        }
        return "강의실 개설 성공";
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/studyRoom")
    public ResponseEntity<List<StudyRoomDto>> getStudyRoom(){
        List<StudyRoom> studyRoomList = new ArrayList<>();

        return new ResponseEntity<>(studyRoomService.getAllStudyRoom(),HttpStatus.OK);
//        return new ResponseEntity<>(studyRoomService.getAllStudyRoom(), HttpStatus.OK);
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/user/studyRoom/{roomId}")
    public ResponseEntity<StudyRoomDto> studyRoomDetail(@PathVariable int roomId) {

        return ResponseEntity.ok(studyRoomService.getByRoomId(roomId));
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/user/login/studyRoom/page")
    public PageResponse readAllPaging(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "roomId", required = false) String sortBy

    ) {
        log.info("Read Paging All");
        return studyRoomService.searchAllPaging(pageNo, pageSize, sortBy);
    }
}
