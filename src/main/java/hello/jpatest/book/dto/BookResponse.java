package hello.jpatest.book.dto;

import lombok.Getter;

@Getter
public class BookResponse {

    private final Long id;
    private final String title;
    private final String author;

    public BookResponse(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
