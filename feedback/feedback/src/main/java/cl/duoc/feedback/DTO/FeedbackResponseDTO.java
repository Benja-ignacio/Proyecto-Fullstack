package cl.duoc.feedback.DTO;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Builder;

@Builder
@Data
public class FeedbackResponseDTO {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer rating;
    private String title;
    private String comment;
    private LocalDateTime createdAt;
}
