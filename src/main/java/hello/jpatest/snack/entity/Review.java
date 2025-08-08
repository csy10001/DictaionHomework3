package hello.jpatest.snack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snack_id", nullable = false)
    private Snack snack;

    public Review(String content, int score, Snack snack) {
        this.content = content;
        this.score = score;
        this.snack = snack;
    }
}
