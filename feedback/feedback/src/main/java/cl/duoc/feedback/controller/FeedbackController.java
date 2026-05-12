package cl.duoc.feedback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.feedback.Service.feedbackService;
import cl.duoc.feedback.model.Feedback;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

     private final feedbackService FeedbackService;

    public FeedbackController(feedbackService feedbackService) {
        this.FeedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<Feedback> create(
            @RequestBody Feedback request
    ) {

        Feedback response = FeedbackService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Feedback>> getByProduct(
            @PathVariable Long productId
    ) {

        return ResponseEntity.ok(
                FeedbackService.getByProduct(productId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feedback>> getByUser(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                FeedbackService.getByUser(userId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        FeedbackService.delete(id);

        return ResponseEntity.noContent().build();
    }
}       