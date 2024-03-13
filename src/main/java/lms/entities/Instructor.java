package lms.entities;

import jakarta.persistence.*;
import lms.entities.enums.Role;
import lms.entities.enums.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Company> companies;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.REMOVE)
    private List<Course> courses;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private User user;


    public void addCompany(Company company){
        if (this.companies == null) this.companies = new ArrayList<>();
        this.companies.add(company);
    }
    public void addCourse(Course course){
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }
}
