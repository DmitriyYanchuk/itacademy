package it.academy.controller;


import it.academy.controller.dto.StudentDto;
import it.academy.service.StudentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public StudentDto getStudent(
            @PathVariable(name = "id")
            @Min(value = 1, message = "Id value must be more then 0")
            final Integer id) {
        LOGGER.info("getStudent() - id [{}]", id);
        return studentService.read(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    public Integer createStudent(
            @RequestBody
            @Valid
            StudentDto studentDto
    ) {
        LOGGER.info("createStudent - student[{}]", studentDto.toString());
        return studentService.create(studentDto);
    }
}
