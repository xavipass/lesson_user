package com.example.extra.domain.service;

import com.example.extra.domain.dto.StudyRoomDto;
import com.example.extra.domain.entity.PageResponse;
import com.example.extra.domain.entity.StudyRoom;
import com.example.extra.domain.entity.StudyRoomPageResponse;
import com.example.extra.domain.repository.StudyRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class StudyRoomService {

    private final StudyRoomRepository studyRoomRepository;

    public void registerStudyRoom(StudyRoomDto studyRoomDto) throws Exception {

        if (studyRoomRepository.findByTitle(studyRoomDto.getTitle()).isPresent()){
            throw new Exception("해당 방이 검색 되었습니다");
        }

        if (studyRoomRepository.findByTeacher(studyRoomDto.getTeacher()).isPresent()){
            throw new Exception("해당 선생님이 검색 되었습니다");
        }

        StudyRoom studyRoom = StudyRoom.builder()
                .title(studyRoomDto.getTitle())
                .studyImage(studyRoomDto.getTitle())
                .teacher(studyRoomDto.getTeacher())
                .build();

        studyRoomRepository.save(studyRoom);
    }

    public List<StudyRoomDto> getAllStudyRoom() {
        List<StudyRoom> studyRoom = studyRoomRepository.findAll();

        return studyRoom.stream().map(p -> mapToStudyRoomDto(p)).collect(Collectors.toList());
    }


    public StudyRoomDto getByRoomId(int roomId) {
        StudyRoom studyRoom = studyRoomRepository.findByRoomId(roomId).orElseThrow(() ->
                new UsernameNotFoundException("강의실을 찾을 수 없습니다"));
        return mapToStudyRoomDto(studyRoom);
    }
    
    public PageResponse searchAllPaging(int pageNo, int pageSize, String sortBy) {

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<StudyRoom> studyPages = studyRoomRepository.findAll(pageable);

        // .map()을 더 추가해서 바로 Page<TodoResponse> 값으로 시작할 수 있어!
        // Page<TodoResponse> todoDtoPage = todoRepository.findAll(pageable).map(this::mapToDto);

        List<StudyRoom> listStudyRoom = studyPages.getContent();

        List<StudyRoomPageResponse> content = listStudyRoom.stream().map(StudyRoom -> mapToDto(StudyRoom)).collect(Collectors.toList());

        return PageResponse.builder()
                .content(content)	// todoDtoPage.getContent()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(studyPages.getTotalElements())
                .totalPages(studyPages.getTotalPages())
                .last(studyPages.isLast())
                .build();

    }

    private StudyRoomDto mapToStudyRoomDto(StudyRoom studyRoom){
        StudyRoomDto studyRoomDto = new StudyRoomDto();
        studyRoomDto.setRoomId(studyRoom.getRoomId());
        studyRoomDto.setTitle(studyRoom.getTitle());
        studyRoomDto.setStudyImage(studyRoom.getStudyImage());
        studyRoomDto.setTeacher(studyRoom.getTeacher());
        return studyRoomDto;
    }

    private StudyRoomPageResponse mapToDto(StudyRoom studyRoom) {

        return StudyRoomPageResponse.builder()
                .roomId(studyRoom.getRoomId())
                .title(studyRoom.getTitle())
                .content(studyRoom.getContent())
                .teacher(studyRoom.getTeacher())
                .build();
    }
}