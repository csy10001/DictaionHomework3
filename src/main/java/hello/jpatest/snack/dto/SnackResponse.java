package hello.jpatest.snack.dto;

import lombok.Getter;

@Getter
public class SnackResponse {
    private final Long id;
    private final String name;
    private final int price;

    public SnackResponse(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
