package lms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

    @ManyToMany(mappedBy = "companies", cascade = {DETACH})
    private List<Instructor> instructors;

    @OneToMany(mappedBy = "company", cascade = {REMOVE, PERSIST, MERGE})
    private List<Course> courses;

    public void addCourses(Course course){
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addInstructor(Instructor instructor){
        if (this.instructors == null) this.instructors = new ArrayList<>();
        this.instructors.add(instructor);
    }

}