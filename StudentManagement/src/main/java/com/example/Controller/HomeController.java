package com.example.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Student;
import com.example.Service.StudentService;

@RestController
public class HomeController {

    private final StudentService studentService;

    public HomeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to Student Management System";
    }

    // GET all students
    @GetMapping("/students/")
    public List<Student> getAllStudents() {
        return studentService.allStudents();
    }

    // GET student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    // POST - add student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // DELETE student
    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return "Student deleted successfully";
    }

    // PUT - update student
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Integer id,@RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }
}