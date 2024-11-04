package com.example.librarybooks.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(min = 1, max = 64)
    @NotNull
    private String title;

    @Column(name = "author")
    @Size(min = 1, max = 64)
    @NotNull
    private String author;

    @Column(name = "published_date")
    @NotNull
    private LocalDate publishedDate;
}
