package by.itacademy.persons;

import by.itacademy.base.BasePerson;
import by.itacademy.education.Assessment;
import by.itacademy.education.Attend;
import by.itacademy.education.Subject;
import by.itacademy.place.School;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student extends BasePerson {

    @OneToMany(mappedBy = "student")
    private List<Assessment> assessments;

    @OneToMany(mappedBy = "student")
    private List<Attend> attends;

    @ManyToMany(mappedBy = "students")
    private List<Group> groups;

    @ManyToMany(mappedBy = "students")
    private List<Parent> parent;

    @ManyToMany(mappedBy = "students")
    private List<School> schools;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    public Student(
            final Integer id,
            final String firstName,
            final String lastName,
            final List<Assessment> assessments,
            final List<Attend> attends,
            final List<Group> groups,
            final List<Parent> parent,
            final List<School> schools,
            final List<Subject> subjects
    ) {
        super(id, firstName, lastName);
        this.assessments = assessments;
        this.attends = attends;
        this.groups = groups;
        this.parent = parent;
        this.schools = schools;
        this.subjects = subjects;
    }

    public final List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public final List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(final List<Attend> attends) {
        this.attends = attends;
    }

    public final List<Group> getGroups() {
        return groups;
    }

    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    public final List<Parent> getParent() {
        return parent;
    }

    public void setParent(final List<Parent> parent) {
        this.parent = parent;
    }

    public final List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    public final List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(final List<Subject> subjects) {
        this.subjects = subjects;
    }
}
