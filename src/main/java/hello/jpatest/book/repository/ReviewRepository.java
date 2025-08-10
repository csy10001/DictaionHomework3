package hello.jpatest.book.repository;

import hello.jpatest.book.entity.Book;
import hello.jpatest.book.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBook(Book book);

    Optional<Review> findById(Long reviewId);
}
