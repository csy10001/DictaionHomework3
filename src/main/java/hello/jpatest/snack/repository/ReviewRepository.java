package hello.jpatest.snack.repository;

import hello.jpatest.snack.entity.Review;
import hello.jpatest.snack.entity.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllBySnack(Snack snack);
}
