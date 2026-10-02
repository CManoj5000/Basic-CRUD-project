package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.StudentDTO;
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
                    student.getPhone_no()
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
            student.getPhone_no()
    );
}

    public Student createStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Optional<Student> optionalStudent = repo.findById(id);
        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setBranch(updatedStudent.getBranch());
            existingStudent.setPhone_no(updatedStudent.getPhone_no());
            return repo.save(existingStudent);
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