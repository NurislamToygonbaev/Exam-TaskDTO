package lms.api;

import lms.dto.request.EditLessonRequest;
import lms.dto.request.SaveLessonRequest;
import lms.dto.response.AllLessonsResponse;
import lms.dto.response.SimpleResponse;
import lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonApi {
    private final LessonService lessonService;
    @GetMapping
    public List<AllLessonsResponse> findAll(){
        return lessonService.findAll();
    }

    @PostMapping("/save/{courseId}")
    public SimpleResponse saveLesson(@RequestBody SaveLessonRequest saveLessonRequest,
                                     @PathVariable Long courseId){
        return lessonService.saveLesson(courseId, saveLessonRequest);
    }

    @GetMapping("/findById/{lessonId}")
    public AllLessonsResponse findById(@PathVariable Long lessonId){
        return lessonService.findById(lessonId);
    }

    @PutMapping("/update/{lessonId}")
    public SimpleResponse updateLesson(@PathVariable Long lessonId,
                                       @RequestBody EditLessonRequest editLessonRequest){
        return lessonService.updateLesson(lessonId, editLessonRequest);
    }

    @DeleteMapping("/delete/{lessonId}")
    public SimpleResponse deleteById(@PathVariable Long lessonId){
        return lessonService.deleteById(lessonId);
    }
}
