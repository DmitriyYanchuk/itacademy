package it.academy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "student")
@Component
public class Student extends BasePersonEntity {

    @OneToMany(mappedBy = "student")
    private List<Assessment> assessments;

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    @Override
    public String toString() {
        return "student_id = " + this.getId()
                + " first_name = " + this.getFirstName()
                + " last_name = " + this.getLastName();
    }
}
