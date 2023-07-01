package com.example.extra.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiTestDto {

    @ApiModelProperty(value = "이름")
    private String name;
}
