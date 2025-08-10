package hello.jpatest.book.controller;

import hello.jpatest.book.dto.BookRequest;
import hello.jpatest.book.dto.BookResponse;
import hello.jpatest.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookResponse> saveBook(
            @RequestBody BookRequest request
    ) {
        return ResponseEntity.ok(bookService.save(request));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBook(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.update(id, request));
    }
}
