package com.example.librarybooks.service.serviceImpl;

import com.example.librarybooks.dao.BooksRepository;
import com.example.librarybooks.dto.BookDto;
import com.example.librarybooks.mapper.BookMapper;
import com.example.librarybooks.service.IBooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BooksService implements IBooksService {
    private final BooksRepository booksRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {
        var books = booksRepository.findAll();
        return bookMapper.toListBookDto(books);
    }

    @Override
    public BookDto getBook(long id) {
        var book = booksRepository.getById(id).
                orElseThrow(() -> new NoSuchElementException("Книга не найдена, id: " + id));

        return bookMapper.modelToDto(book);
    }

    @Override
    public void addBook(BookDto bookDto) {
        booksRepository.save(bookMapper.dtoToModel(bookDto));
    }

    @Override
    public void updateBook(BookDto bookDto) {
        booksRepository.save(bookMapper.dtoToModel(bookDto));
    }

    @Override
    public void deleteBook(long id) {
        booksRepository.deleteById(id);
    }
}
