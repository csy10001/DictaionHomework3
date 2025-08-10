package hello.jpatest.book.service;

import hello.jpatest.book.dto.BookRequest;
import hello.jpatest.book.dto.BookResponse;
import hello.jpatest.book.entity.Book;
import hello.jpatest.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse save(BookRequest request) {
        Book book = new Book(request.getTitle(), request.getAuthor());
        Book savedBook = bookRepository.save(book);
        return new BookResponse(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getAuthor()
        );
    }

    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor()
                )).toList();
    }

    @Transactional
    public BookResponse findById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책은 없어요")
        );
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );
    }

    @Transactional
    public BookResponse update(Long bookId, BookRequest request) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책은 없어요")
        );
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());

        return  new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );
    }
}
