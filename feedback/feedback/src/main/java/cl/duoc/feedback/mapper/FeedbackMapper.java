package cl.duoc.feedback.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.feedback.dto.FeedbackResponseDTO;
import cl.duoc.feedback.model.Feedback;

@Component
public class FeedbackMapper {
    // entidad a dto -> obtiene una entity y la transforma a dto
    public FeedbackResponseDTO entityToFeedbackResponseDTO(Feedback feedback) {
        return FeedbackResponseDTO.builder()
                .id(feedback.getId())
                .userId(feedback.getUserId())
                .productId(feedback.getProductId())
                .rating(feedback.getRating())
                .title(feedback.getTitle())
                .comment(feedback.getComment())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
}
