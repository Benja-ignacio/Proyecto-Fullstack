package cl.duoc.feedback.service;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.feedback.Service.FeedbackService;
import cl.duoc.feedback.dto.FeedbackResponseDTO;
import cl.duoc.feedback.mapper.FeedbackMapper;
import cl.duoc.feedback.model.Feedback;
import cl.duoc.feedback.repository.FeedbackRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class FeedbackServiceTest {
    @Test

        void testGetAllFeedback() {
        FeedbackRepository feedbackRepository = Mockito.mock(FeedbackRepository.class);
        FeedbackMapper mapper = Mockito.mock(FeedbackMapper.class); // ← faltaba este
        FeedbackService feedbackService = new FeedbackService(feedbackRepository, mapper);

        Feedback feedback = new Feedback(1L, 1L, 1L, 4, "Buen producto", "Tiene buen material", LocalDateTime.now(), null);
        Mockito.when(feedbackRepository.findByUserId(1L)).thenReturn(List.of(feedback));

        List<FeedbackResponseDTO> result = feedbackService.getByUser(1L);

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
    }
    
}
