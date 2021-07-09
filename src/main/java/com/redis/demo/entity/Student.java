package com.redis.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

/**
 * @author Tharindu Eranga
 * @date Fri 09 Jul 2021
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@RedisHash("Student")
public class Student {
    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;
}
