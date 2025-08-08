package hello.jpatest.snack.dto;

import lombok.Getter;

@Getter
public class ReviewRequest {

    private String content;
    private int score;
}
