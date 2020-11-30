package com.library.services;

import com.library.dto.BookDto;
import com.library.exception.BookIsNotPresentException;
import com.library.mapper.BookMapper;
import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(book -> bookMapper.map(book))
                             .collect(Collectors.toList());
    }

    public Optional<BookDto> findById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(book -> bookMapper.map(book));
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    public void addBook(BookDto bookDto) {
        Book book = bookMapper.map(bookDto);
        book.setTitle(book.getTitle().trim());
        bookRepository.save(book);
    }

    public void update(int id, BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (!optionalBook.isPresent()) {
            throw new BookIsNotPresentException();
        }

        Book dbBook = optionalBook.get();


        dbBook.setTitle(bookDto.getTitle()
                               .trim());
        dbBook.setStock(bookDto.getStock());
        dbBook.setAuthor(bookDto.getAuthor());
        dbBook.setBookImageUrl(bookDto.getBookImageUrl());
        dbBook.setBookCategory(bookDto.getBookCategory());

        if (dbBook.getFictional() != null && bookDto.getNonfictional() != null) {
            dbBook.setFictional(null);
            dbBook.setNonfictional(bookDto.getNonfictional());
        } else if (dbBook.getNonfictional() != null && bookDto.getFictional() != null) {
            dbBook.setFictional(bookDto.getFictional());
            dbBook.setNonfictional(null);
        }

        if (dbBook.getFictional() != null && bookDto.getFictional() != null) {
            dbBook.setFictional(bookDto.getFictional());
        }

        if (dbBook.getNonfictional() != null && bookDto.getNonfictional() != null) {
            dbBook.setNonfictional(bookDto.getNonfictional());
        }

        bookRepository.save(dbBook);
    }
}
