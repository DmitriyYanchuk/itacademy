package it.academy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends BasePersonEntity {

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "teacher_id = " + this.getId()
                + ", first_name = " + this.getFirstName()
                + ", last_name = " + this.getLastName();
    }
}
