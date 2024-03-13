package lms.api;

import lms.dto.request.EditLessonRequest;
import lms.dto.request.SaveLessonRequest;
import lms.dto.response.AllLessonsResponse;
import lms.dto.response.SimpleResponse;
import lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonApi {
    private final LessonService lessonService;
    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/courseId")
    public List<AllLessonsResponse> findAll(Long courseId){
        return lessonService.findAll(courseId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @PostMapping("/save/{courseId}")
    public SimpleResponse saveLesson(@RequestBody SaveLessonRequest saveLessonRequest,
                                     @PathVariable Long courseId){
        return lessonService.saveLesson(courseId, saveLessonRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/findById/{lessonId}")
    public AllLessonsResponse findById(@PathVariable Long lessonId){
        return lessonService.findById(lessonId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @PutMapping("/update/{lessonId}")
    public SimpleResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody EditLessonRequest editLessonRequest){
        return lessonService.updateLesson(lessonId, editLessonRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @DeleteMapping("/delete/{lessonId}")
    public SimpleResponse deleteById(@PathVariable Long lessonId){
        return lessonService.deleteById(lessonId);
    }
}
