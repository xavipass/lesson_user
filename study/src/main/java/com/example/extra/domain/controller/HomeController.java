package com.example.extra.domain.controller;

import com.example.extra.domain.dto.ApiTestDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(method= {RequestMethod.POST})
@RestController
public class HomeController {

    @GetMapping("/")
    public String indexForm() {

        return "인덱스 테스트";
    }

    @GetMapping(value = "/call")
    @ApiOperation(value="테스트", notes="테스트 중입니다.")
    public ResponseEntity<List<ApiTestDto>> Call(@RequestParam("name") String name)  throws Exception {
        List<ApiTestDto> data = new ArrayList<>();
        return ResponseEntity.ok(data);
    }
}
