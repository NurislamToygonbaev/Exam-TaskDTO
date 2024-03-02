package lms.repository;

import lms.dto.response.ALlTasksResponse;
import lms.dto.response.FIndTaskResponse;
import lms.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select new lms.dto.response.ALlTasksResponse(t.id, t.taskName, t.taskText, t.deadLine) from Task t")
    List<ALlTasksResponse> findTasks();
    @Query("select new lms.dto.response.FIndTaskResponse(t.id, t.taskName, t.taskText, t.deadLine) from Task t where t.id =:taskId")
    FIndTaskResponse findTaskById(Long taskId);
}
