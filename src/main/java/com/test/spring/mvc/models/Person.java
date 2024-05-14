package com.test.spring.mvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30")
    @Column(name = "name")
    private String name;
    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name = "age")
    private Integer age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

}
