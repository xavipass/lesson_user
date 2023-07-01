package com.example.extra.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "studyRoom")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudyRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private String title;

    private String teacher;

    private String content;

    private String studyImage;


}
