package lms.api;

import lms.dto.request.EditTaskRequest;
import lms.dto.request.SaveTaskRequest;
import lms.dto.response.ALlTasksResponse;
import lms.dto.response.FIndTaskResponse;
import lms.dto.response.SimpleResponse;
import lms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskApi {
    private final TaskService taskService;

    @Secured({"ADMIN", "INSTRUCTOR"})
    @GetMapping("/{lessonId}")
    public List<ALlTasksResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @PostMapping("/save/{lessonId}")
    public SimpleResponse saveTask(@PathVariable Long lessonId,
                                   @RequestBody SaveTaskRequest saveTaskRequest){
        return taskService.saveTask(lessonId, saveTaskRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR", "STUDENT"})
    @GetMapping("/find/{taskId}")
    public FIndTaskResponse findById(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @PutMapping("/update/{taskId}")
    public SimpleResponse updateTask(@PathVariable Long taskId,
                                     @RequestBody EditTaskRequest editTaskRequest){
        return taskService.updateTask(taskId, editTaskRequest);
    }

    @Secured({"ADMIN", "INSTRUCTOR"})
    @DeleteMapping("/delete/{taskId}")
    public SimpleResponse deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }
}
