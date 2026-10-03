package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.DTO.StudentRequestDTO;
import com.example.demo.Entity.Student;
import com.example.demo.Exceptions.StudentNotFoundException;
import com.example.demo.Repository.Repo;

@Service
public class StudentService {
    
    private final Repo repo;

    public StudentService(Repo repo) {
        this.repo = repo;
    }

    public List<StudentDTO> getAllStudents() {
    return repo.findAll().stream()
            .map(student -> new StudentDTO(
                    student.getId(),
                    student.getName(),
                    student.getBranch(),
                    student.getPhone_no(),
                    student.getEmail()
            ))
            .toList();
}

    public StudentDTO getStudentById(int id) {
    Student student = repo.findById(id)
            .orElseThrow(() ->
                    new StudentNotFoundException("Student not found with id: " + id));

    return new StudentDTO(
            student.getId(),
            student.getName(),
            student.getBranch(),
            student.getPhone_no(),
            student.getEmail()
    );
}

    public StudentDTO createStudent(StudentRequestDTO student) {
        Student newStudent = new Student(null, student.getName(), student.getPhone_no(), null, student.getEmail(), student.getBranch());
        Student savedStudent = repo.save(newStudent);
        return new StudentDTO(savedStudent.getId(), savedStudent.getName(), savedStudent.getBranch(), savedStudent.getPhone_no(), savedStudent.getEmail());
    }

    public StudentDTO updateStudent(int id, StudentRequestDTO updatedStudent) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setBranch(updatedStudent.getBranch());
            existingStudent.setPhone_no(updatedStudent.getPhone_no());
            Student savedStudent = repo.save(existingStudent);
            return new StudentDTO(savedStudent.getId(), savedStudent.getName(), savedStudent.getBranch(), savedStudent.getPhone_no(), savedStudent.getEmail());
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}