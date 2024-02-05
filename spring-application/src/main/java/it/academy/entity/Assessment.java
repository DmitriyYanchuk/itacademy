package it.academy.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;

@Entity
@Table(name = "assessment")
@Component
public class Assessment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "assessment", columnDefinition = "int2")
    private int assessment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk__assessment__student__id")
    )
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "subject_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk__assessment__subject__id")
    )
    private Subject subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssessment() {
        return assessment;
    }

    public void setAssessment(int assessment) {
        this.assessment = assessment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "assessment_id = " + this.getId() +
                " assessment = " + this.getAssessment();
    }
}
