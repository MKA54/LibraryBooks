package com.example.librarybooks.service;

import com.example.librarybooks.dto.BookDto;

import java.util.List;

public interface IBooksService {
    List<BookDto> getAllBooks();

    BookDto getBook(long id);

    void addBook(BookDto bookDto);

    void updateBook(BookDto bookDto);

    void deleteBook(long id);
}
