package com.redis.demo.repository;

import com.redis.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tharindu Eranga
 * @date Fri 09 Jul 2021
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

}