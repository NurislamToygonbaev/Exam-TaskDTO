package lms.service.impl;

import lms.dto.request.EditLessonRequest;
import lms.dto.request.SaveLessonRequest;
import lms.dto.response.AllLessonsResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.Course;
import lms.entities.Lesson;
import lms.repository.CourseRepository;
import lms.repository.LessonRepository;
import lms.service.CourseService;
import lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    private Lesson checkId(Long lessonId){
        return lessonRepo.findById(lessonId)
                .orElseThrow(() ->
                        new NoSuchElementException("Lesson with id: "+lessonId+" not found"));
    }
    @Override
    public List<AllLessonsResponse> findAll() {
        return lessonRepo.findAllLesson();
    }

    @Override
    public SimpleResponse saveLesson(Long courseId, SaveLessonRequest saveLessonRequest) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() ->
                        new NoSuchElementException("Course with id: " + courseId + " not found"));
        Lesson lesson = new Lesson();
        lesson.setLessonName(saveLessonRequest.lessonName());
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepo.save(lesson);
        return new SimpleResponse(HttpStatus.OK, "successfully saved");
    }

    @Override
    public AllLessonsResponse findById(Long lessonId) {
        checkId(lessonId);
        return lessonRepo.findLessonById(lessonId);
    }

    @Override
    public SimpleResponse updateLesson(Long lessonId, EditLessonRequest editLessonRequest) {
        Lesson lesson = checkId(lessonId);
        lesson.setLessonName(editLessonRequest.lessonName());
        lessonRepo.save(lesson);
        return new SimpleResponse(HttpStatus.OK, "successfully updated");
    }

    @Override
    public SimpleResponse deleteById(Long lessonId) {
        Lesson lesson = checkId(lessonId);
        lessonRepo.delete(lesson);
        return new SimpleResponse(HttpStatus.OK, "successfully deleted");
    }
}
