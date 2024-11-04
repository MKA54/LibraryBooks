package com.example.librarybooks.controller;

import com.example.librarybooks.dto.BookDto;
import com.example.librarybooks.dto.OnCreate;
import com.example.librarybooks.dto.OnUpdate;
import com.example.librarybooks.service.IBooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minh/rpc/api/v1/books")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Операции с книгами")
public class BooksController {
    private final IBooksService booksService;

    @Operation(summary = "Получить все книги")
    @GetMapping("getBooks")
    public ResponseEntity<?> getBooks() {
        log.info("Вызван метод getBooks");

        var books = booksService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Operation(summary = "Получить книгу по идентификатору")
    @GetMapping("getBook/{id}")
    public ResponseEntity<?> getBook(@PathVariable("id") @Min(0) long id) {
        log.info("Вызван метод getBook, id=" + id);

        var book = booksService.getBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Добавить новую книгу")
    @PostMapping("addBook")
    @Validated(OnCreate.class)
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDto bookDto) {
        log.info("Вызван метод addBook, book=" + bookDto);

        booksService.addBook(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить информацию о книге")
    @PostMapping("updateBook")
    @Validated(OnUpdate.class)
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDto bookDto) {
        log.info("Вызван метод updateBook, book=" + bookDto);

        booksService.updateBook(bookDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удалить книгу по идентификатору")
    @PostMapping("deleteBook/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") @Min(0) long id) {
        log.info("Вызван метод deleteBook, id=" + id);

        booksService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
