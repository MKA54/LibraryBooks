package com.example.librarybooks.service.serviceImpl;

import com.example.librarybooks.dao.BooksRepository;
import com.example.librarybooks.dto.BookDto;
import com.example.librarybooks.mapper.BookMapper;
import com.example.librarybooks.service.IBooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BooksService implements IBooksService {
    private final BooksRepository booksRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAllBooks() {

        var books = booksRepository.findByActiveTrue();
        log.info("Получен список книг, size=" + books.size());
        return bookMapper.toListBookDto(books);
    }

    @Override
    public BookDto getBook(long id) {
        var book = booksRepository.getReferenceByIdAndActiveTrue(id).
                orElseThrow(() -> {
                    log.error("Книга не найдена, id: " + id);
                    return new NoSuchElementException("Книга не найдена, id: " + id);
                });

        log.info("Получена книга, id=" + id);
        return bookMapper.modelToDto(book);
    }

    @Override
    @Transactional
    public void addBook(BookDto bookDto) {
        booksRepository.save(bookMapper.dtoToModel(bookDto));
        log.info("Книга добавлена, book=" + bookDto);
    }

    @Override
    @Transactional
    public void updateBook(BookDto bookDto) {
        booksRepository.save(bookMapper.dtoToModel(bookDto));
        log.info("Книга обновлена, book=" + bookDto);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        booksRepository.deleteById(id);
        log.info("Книга удалена, id=" + id);
    }
}
