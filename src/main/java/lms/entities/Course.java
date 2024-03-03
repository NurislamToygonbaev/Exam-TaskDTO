package lms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private LocalDate dateOfStart;
    private String description;

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.DETACH)
    private List<Group> groups;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Instructor instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Lesson> lessons;


    public void addGroup(Group group){
        if (this.groups == null) this.groups = new ArrayList<>();
        this.groups.add(group);
    }

    public void addLesson(Lesson lesson){
        if (this.lessons == null) this.lessons = new ArrayList<>();
        this.lessons.add(lesson);
    }

}
