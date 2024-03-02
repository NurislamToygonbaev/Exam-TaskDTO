package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", allocationSize = 1)
    private Long id;
    private String lessonName;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "lesson")
    private List<Task> tasks;

    public void addTask(Task task){
        if (this.tasks == null) this.tasks = new ArrayList<>();
        this.tasks.add(task);
    }
}
