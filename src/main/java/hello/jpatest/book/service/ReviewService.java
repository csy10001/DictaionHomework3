package hello.jpatest.book.service;

import hello.jpatest.book.dto.ReviewRequest;
import hello.jpatest.book.dto.ReviewResponse;
import hello.jpatest.book.entity.Book;
import hello.jpatest.book.entity.Review;
import hello.jpatest.book.repository.BookRepository;
import hello.jpatest.book.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책은 없어요")
        );
        Review review = new Review(
                request.getContent(),
                book
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책은 없어요")
        );

        List<Review> books =reviewRepository.findAllByBook(book);
        List<ReviewResponse> dtos = books.stream().map(review -> new ReviewResponse(
                review.getId(),
                review.getContent()
        )).collect(Collectors.toList());

        return dtos;
    }

    @Transactional(readOnly = true)
    public ReviewResponse findById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책의 리뷰는 없어요")
        );
        return new ReviewResponse(
                review.getId(),
                review.getContent()

        );
    }

    @Transactional
    public ReviewResponse update(ReviewRequest request, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalArgumentException("그런 책의 리뷰는 없어요")
        );

        review.setContent(request.getContent());

        return new ReviewResponse(
                review.getId(),
                review.getContent()

        );
    }
}
