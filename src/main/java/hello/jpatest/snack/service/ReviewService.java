package hello.jpatest.snack.service;

import hello.jpatest.snack.dto.ReviewRequest;
import hello.jpatest.snack.dto.ReviewResponse;
import hello.jpatest.snack.entity.Review;
import hello.jpatest.snack.entity.Snack;
import hello.jpatest.snack.repository.ReviewRepository;
import hello.jpatest.snack.repository.SnackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final SnackRepository snackRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long snackId) {
        Snack snack = snackRepository.findById(snackId).orElseThrow(
                ()-> new IllegalArgumentException("그런 과자는 없어요")
        );
        Review review = new Review(
                request.getContent(),
                request.getScore(),
                snack
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent(),
                savedReview.getScore()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long snackId) {
        Snack snack = snackRepository.findById(snackId).orElseThrow(
                ()-> new IllegalArgumentException("이런 id의 과자는 없어요")
        );

        List<Review> snacks =reviewRepository.findAllBySnack(snack);
        List<ReviewResponse> dtos = new ArrayList<>();
        for (Review review : snacks) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent(),
                            review.getScore()
                    )
            );
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ReviewResponse findById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalArgumentException("해당 id의 리뷰가 없어요")
        );
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getScore()
        );
    }

    @Transactional
    public ReviewResponse update(ReviewRequest request, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 과자는 없어요")
        );

        review.setContent(request.getContent());
        review.setScore(request.getScore());

        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getScore()
        );
    }
}
