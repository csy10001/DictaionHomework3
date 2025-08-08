package hello.jpatest.snack.controller;

import hello.jpatest.snack.dto.ReviewRequest;
import hello.jpatest.snack.dto.ReviewResponse;
import hello.jpatest.snack.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/snacks/{snackId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long snackId
    ) {
        return ResponseEntity.ok(reviewService.save(request, snackId));
    }

    @GetMapping("/snacks/{snackId}/reviews")
    public ResponseEntity<Iterable<ReviewResponse>> getAllReviews(
            @PathVariable Long snackId
    ) {
        return ResponseEntity.ok(reviewService.findAll(snackId));
    }

    @GetMapping("/snacks/{snackId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> getReviews(
            @PathVariable Long reviewId
    ) {
        return ResponseEntity.ok(reviewService.findById(reviewId));
    }

    @PutMapping("/snacks/{snackId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest request
    ) {
        return ResponseEntity.ok(reviewService.update(request, reviewId));
    }
}
