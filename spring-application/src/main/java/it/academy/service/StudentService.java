package it.academy.service;

import it.academy.controller.dto.StudentDto;

public interface StudentService {

    Integer create(StudentDto studentDto);

    StudentDto read (Integer id);
}
