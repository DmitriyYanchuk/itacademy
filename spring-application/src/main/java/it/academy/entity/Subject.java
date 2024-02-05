package it.academy.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Assessment> assessments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(
                    name = "subject_id",
                    referencedColumnName = "id",
                    nullable = false,
                    foreignKey = @ForeignKey(name = "fk__subject_teacher__subject_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_id",
                    referencedColumnName = "id",
                    nullable = false,
                    foreignKey = @ForeignKey(name = "fk__subject_teacher__teacher_id")
            )
    )
    private List<Teacher> teachers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "subject_id = " + this.getId() + ", subject_name = " + this.getName();
    }
}
