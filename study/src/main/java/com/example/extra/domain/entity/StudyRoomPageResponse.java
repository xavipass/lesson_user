package com.example.extra.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomPageResponse {

    private int roomId;

    private String title;

    private String teacher;

    private String content;

    private String studyImage;
}
