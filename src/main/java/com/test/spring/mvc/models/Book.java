package com.test.spring.mvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title should not be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 2, message = "Min name 2 characters")
    @Column(name = "author")
    private String author;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
}
