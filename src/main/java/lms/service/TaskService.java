package lms.service;

import lms.dto.request.EditTaskRequest;
import lms.dto.request.SaveTaskRequest;
import lms.dto.response.ALlTasksResponse;
import lms.dto.response.FIndTaskResponse;
import lms.dto.response.SimpleResponse;

import java.util.List;

public interface TaskService {
    List<ALlTasksResponse> findAll(Long lessonId);

    SimpleResponse saveTask(Long lessonId, SaveTaskRequest saveTaskRequest);

    FIndTaskResponse findById(Long taskId);

    SimpleResponse updateTask(Long taskId, EditTaskRequest editTaskRequest);

    SimpleResponse deleteTask(Long taskId);
}
