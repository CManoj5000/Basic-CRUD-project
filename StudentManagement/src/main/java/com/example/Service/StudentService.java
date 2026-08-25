package com.example.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Entity.Student;
import com.example.Repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Add student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    // Update student
    public Student updateStudentById(int id, Student updatedStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());

        return studentRepository.save(existingStudent);
    }
}