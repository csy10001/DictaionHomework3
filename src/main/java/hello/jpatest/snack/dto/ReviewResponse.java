package hello.jpatest.snack.dto;

import lombok.Getter;

@Getter
public class ReviewResponse {

    private final Long id;
    private final String content;
    private final int score;

    public ReviewResponse(Long id, String content, int score) {
        this.id = id;
        this.content = content;
        this.score = score;
    }
}
