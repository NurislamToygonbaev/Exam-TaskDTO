package lms.service.impl;

import lms.dto.request.EditTaskRequest;
import lms.dto.request.SaveTaskRequest;
import lms.dto.response.ALlTasksResponse;
import lms.dto.response.FIndTaskResponse;
import lms.dto.response.SimpleResponse;
import lms.entities.Lesson;
import lms.entities.Task;
import lms.repository.LessonRepository;
import lms.repository.TaskRepository;
import lms.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepo;
    private final LessonRepository lessonRepo;

    private Task checkId(Long taskId){
        return taskRepo.findById(taskId)
                .orElseThrow(() ->
                        new NoSuchElementException("Task with id: "+taskId+" not found"));
    }
    @Override
    public List<ALlTasksResponse> findAll() {
        return taskRepo.findTasks();
    }

    @Override
    public SimpleResponse saveTask(Long lessonId, SaveTaskRequest saveTaskRequest) {
        try {
            Lesson lesson = lessonRepo.findById(lessonId)
                    .orElseThrow(() ->
                            new NoSuchElementException("Lesson with id: " + lessonId + " not found"));
            Task task = new Task();
            task.setTaskName(saveTaskRequest.taskName());
            task.setTaskText(saveTaskRequest.taskText());
            task.setDeadLine(saveTaskRequest.deadLine());
            lesson.addTask(task);
            task.setLesson(lesson);
            taskRepo.save(task);
            return new SimpleResponse(HttpStatus.OK, "successfully saved");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public FIndTaskResponse findById(Long taskId) {
        checkId(taskId);
        return taskRepo.findTaskById(taskId);
    }

    @Override
    public SimpleResponse updateTask(Long taskId, EditTaskRequest editTaskRequest) {
        try {
            Task task = checkId(taskId);
            task.setTaskText(editTaskRequest.taskText());
            task.setTaskName(editTaskRequest.taskName());
            task.setDeadLine(editTaskRequest.deadLine());
            taskRepo.save(task);
            return new SimpleResponse(HttpStatus.OK, "successfully updated");
        }catch (Exception e){
            return SimpleResponse
                    .builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteTask(Long taskId) {
       try {
           Task task = checkId(taskId);
           taskRepo.delete(task);
           return new SimpleResponse(HttpStatus.OK, "successfully deleted");
       }catch (Exception e){
           return SimpleResponse
                   .builder()
                   .httpStatus(HttpStatus.NOT_FOUND)
                   .message(e.getMessage())
                   .build();
       }
    }
}
