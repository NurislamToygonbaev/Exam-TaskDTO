package lms.repository;

import lms.dto.response.AllLessonsResponse;
import lms.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select new lms.dto.response.AllLessonsResponse(l.id, l.lessonName) from Lesson l")
    List<AllLessonsResponse> findAllLesson();

    @Query("select new lms.dto.response.AllLessonsResponse(l.id, l.lessonName) from Lesson l where l.id =:lessonId")
    AllLessonsResponse findLessonById(Long lessonId);
}
