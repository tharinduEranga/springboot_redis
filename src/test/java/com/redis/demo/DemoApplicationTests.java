package com.redis.demo;

import com.redis.demo.config.RedisConfig;
import com.redis.demo.entity.Student;
import com.redis.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.NotThrownAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@Slf4j
@Import(RedisConfig.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    private static final String ST_ID = "Eng2015001";

    @Test
    void contextLoads() {
        assertThat(studentRepository).isNotNull();
    }

    @Test
    void saveStudent() {
        Student student = new Student(
                ST_ID, "John Doe", Student.Gender.MALE, 1);
        Student engStudent = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        Student medStudent = new Student(
                "Med2015001", "Gareth Houston", Student.Gender.MALE, 2);
        student = studentRepository.save(student);
        engStudent = studentRepository.save(engStudent);
        medStudent = studentRepository.save(medStudent);

        assertThat(student.getId()).isNotNull();
        assertThat(engStudent.getId()).isNotNull();
        assertThat(medStudent.getId()).isNotNull();
    }

    @Test
    void getStudent() {
        Optional<Student> retrievedStudent =
                studentRepository.findById(ST_ID);
        assertThat(retrievedStudent).isPresent();
        Student student = retrievedStudent.get();
        log.info("Retrieved student: {}", student);
    }

    @Test
    void updateStudent() {
        String newName = "Richard Watson";
        Optional<Student> retrievedStudent =
                studentRepository.findById(ST_ID);
        assertThat(retrievedStudent).isPresent();
        Student student = retrievedStudent.get();
        student.setName(newName);
        student = studentRepository.save(student);
        assertThat(newName).isEqualTo(student.getName());
    }

    @Test
    void deleteStudent() {
        studentRepository.deleteById(ST_ID);
        NotThrownAssert noException = assertThatNoException();
        log.info("No exception: {}", noException);
    }

    @Test
    void getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll().forEach(students::add);
        log.info("Students: {}", students);
        assertThat(students).isNotEmpty();
    }

}
