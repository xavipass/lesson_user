package com.example.extra.domain.dto;

import com.example.extra.domain.entity.StudyRoom;
import com.example.extra.domain.entity.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyRoomDto {
    private int roomId;

    private String title;

    private String StudyImage;

    private String teacher;

    public static StudyRoomDto toStudyRoomDto(StudyRoom studyRoom){
        StudyRoomDto studyRoomDto = new StudyRoomDto();
        studyRoomDto.setTitle(studyRoom.getTitle());
        studyRoomDto.setStudyImage(studyRoomDto.getStudyImage());
        studyRoomDto.setTeacher(studyRoomDto.getTeacher());
        return studyRoomDto;
    }
}
