package cl.duoc.feedback.controller;

import cl.duoc.feedback.dto.FeedbackRequest;
import cl.duoc.feedback.dto.FeedbackResponse;
import cl.duoc.feedback.service.FeedbackService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponse> create(
            @Valid @RequestBody FeedbackRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedbackService.create(request));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<FeedbackResponse>> getByProduct(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(
                feedbackService.getByProduct(productId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackResponse>> getByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                feedbackService.getByUser(userId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}