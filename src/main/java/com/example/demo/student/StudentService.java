package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        //return List.of(new Student(1L,"Harry","harrystyles@gmail.com", LocalDate.of(1992, Month.DECEMBER,31),29));
        return studentRepository.findAll();

    }

    public void addNewStudent(Student s) {
        Optional<Student> studentOptional=studentRepository.findStudentByEmail(s.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email is already present");
        }
        studentRepository.save(s);
    }

    public void deleteStudent(Long studentId) {
        boolean exists= studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(" Student with id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student= studentRepository.findById(studentId).orElseThrow(()-> {
            throw new IllegalStateException("student with id "+studentId+" does not exists");
        });

        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name))
        {
            student.setName(name);
        }
        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
