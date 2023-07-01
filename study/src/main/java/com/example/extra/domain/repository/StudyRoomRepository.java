package com.example.extra.domain.repository;

import com.example.extra.domain.entity.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyRoomRepository extends JpaRepository <StudyRoom,Long> {

    Optional<StudyRoom> findByRoomId(int roomId);

    Optional<StudyRoom> findByTitle(String title);

    Optional<StudyRoom> findByTeacher(String teacher);

}
