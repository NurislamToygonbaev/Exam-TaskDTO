package lms.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    @ManyToMany
    private List<Course> courses;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public void addCourse(Course course){
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addStudent(Student student){
        if (this.students == null) this.students = new ArrayList<>();
        this.students.add(student);
    }
}
