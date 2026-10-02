package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;

import jakarta.validation.Valid;

@RestController
public class HomeController {
    private final StudentService studentService;

    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<String> greet () {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, Welcome to my Spring Boot Application!");
    }
    @GetMapping("/about")
    public ResponseEntity<String> about() {
        return ResponseEntity.ok("Here is my About !");
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> students() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@Valid @PathVariable Integer id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(201).body(createdStudent);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @PathVariable Integer id, @Valid @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(id, updatedStudent);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@Valid @PathVariable Integer id) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
