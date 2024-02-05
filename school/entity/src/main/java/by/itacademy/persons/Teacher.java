package by.itacademy.persons;

import by.itacademy.base.BasePerson;
import by.itacademy.education.Assessment;
import by.itacademy.education.Lesson;
import by.itacademy.education.Subject;
import by.itacademy.place.Auditory;
import by.itacademy.place.School;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends BasePerson {

    @ManyToMany(mappedBy = "teacher")
    private Auditory auditory;

    @OneToMany(mappedBy = "teacher")
    private List<Assessment> assessments;

    @ManyToMany(mappedBy = "teachers")
    private List<Group> groups;

    @ManyToMany(mappedBy = "teacher")
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "teachers")
    private List<School> schools;

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public Teacher(
            final Integer id,
            final String firstName,
            final String lastName,
            final List<Assessment> assessments,
            final List<Group> groups,
            final List<Lesson> lessons,
            final List<School> schools,
            final List<Subject> subjects
    ) {
        super(id, firstName, lastName);
        this.assessments = assessments;
        this.groups = groups;
        this.lessons = lessons;
        this.schools = schools;
        this.subjects = subjects;
    }

    public final List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(final List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public final List<Group> getGroups() {
        return groups;
    }

    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }

    public final List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(final List<Lesson> lessons) {
        this.lessons = lessons;
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
