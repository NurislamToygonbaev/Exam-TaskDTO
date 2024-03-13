package lms.entities;

import jakarta.persistence.*;
import lms.entities.enums.Role;
import lms.entities.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", allocationSize = 1)
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private boolean block = true;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Group group;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private User user;
}
