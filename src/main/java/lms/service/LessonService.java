package lms.service;

import lms.dto.request.EditLessonRequest;
import lms.dto.request.SaveLessonRequest;
import lms.dto.response.AllLessonsResponse;
import lms.dto.response.SimpleResponse;

import java.util.List;

public interface LessonService {
    List<AllLessonsResponse> findAll(Long courseId);

    SimpleResponse saveLesson(Long courseId, SaveLessonRequest saveLessonRequest);

    AllLessonsResponse findById(Long lessonId);

    SimpleResponse updateLesson(Long lessonId, EditLessonRequest editLessonRequest);

    SimpleResponse deleteById(Long lessonId);
}
