package hello.jpatest.book.controller;

import hello.jpatest.book.dto.ReviewRequest;
import hello.jpatest.book.dto.ReviewResponse;
import hello.jpatest.book.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long bookId
    ){
        return ResponseEntity.ok(reviewService.save(request,bookId));
    }

    @GetMapping("/books/{bookId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(
            @PathVariable Long bookId
    ) {
        return ResponseEntity.ok(reviewService.findAll(bookId));
    }

    @GetMapping("/books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> getReviews(
            @PathVariable Long bookId,
            @PathVariable Long reviewId
    ) {
        return ResponseEntity.ok(reviewService.findById(reviewId));
    }

    @PutMapping("/books/{bookId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest request
    ) {
        return ResponseEntity.ok(reviewService.update(request, reviewId));
    }
}
