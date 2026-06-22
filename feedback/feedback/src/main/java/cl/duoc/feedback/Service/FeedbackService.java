package cl.duoc.feedback.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.feedback.dto.FeedbackRequestDTO;
import cl.duoc.feedback.dto.FeedbackResponseDTO;
import cl.duoc.feedback.dto.UpdateFeedbackRequestDTO;
import cl.duoc.feedback.exception.custom.FeedbackAlreadyExistsException;
import cl.duoc.feedback.exception.custom.FeedbackNotFoundException;
import cl.duoc.feedback.mapper.FeedbackMapper;
import cl.duoc.feedback.model.Feedback;
import cl.duoc.feedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper mapper;

    // crear nuevo feedback
    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO request) {

        if (feedbackRepository.existsByUserIdAndProductId(request.getUserId(), request.getProductId())) {
            throw new FeedbackAlreadyExistsException("Ya comentaste este producto, solo puedes editarlo"); // crear excepcion personalizada
        }

        Feedback feedback = new Feedback();
        feedback.setUserId(request.getUserId());
        feedback.setProductId(request.getProductId());
        feedback.setRating(request.getRating());
        feedback.setTitle(request.getTitle());
        feedback.setComment(request.getComment());

        feedbackRepository.save(feedback);

        return mapper.entityToFeedbackResponseDTO(feedback);
    
    }

    // buscar feedbacks por productId
    public List<FeedbackResponseDTO> getByProduct(Long productId) {        
        // NOTA, VALIDAR SI EL PRODUCTO EXISTE UNA VEZ SE INTEGREN LOS MICROSERVICIOS
        List<Feedback> feedback = feedbackRepository.findByProductId(productId);
    
        return feedback.stream()
                .map(mapper::entityToFeedbackResponseDTO)
                .toList();
        
    }

    // obtener feedbacks de un usuario
    public List<FeedbackResponseDTO> getByUser(Long userId) {
        // NOTA, VALIDAR SI EL USUARIO EXISTE UNA VEZ SE INTEGREN LOS MICROSERVICIOS
       List<Feedback> feedback = feedbackRepository.findByUserId(userId);

       return feedback.stream()
                .map(mapper::entityToFeedbackResponseDTO)
                .toList();
    }

    // eliminar un feedback por su id
    public void delete(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
        .orElseThrow(() -> new FeedbackNotFoundException("Error: Feedback no encontrado")); // crear excepcion personalizada

        feedbackRepository.delete(feedback);
    }

    // obtener feedback por id 
    public FeedbackResponseDTO getById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                            .orElseThrow(() -> new FeedbackNotFoundException("Error: Feedback no encontrado"));

        return mapper.entityToFeedbackResponseDTO(feedback);
    } 


    // actualizar feedback
    public FeedbackResponseDTO update(Long id, UpdateFeedbackRequestDTO request) {
        Feedback feedback = feedbackRepository.findById(id)
                            .orElseThrow(() -> new FeedbackNotFoundException("Error: Feedback no encontrado"));
                            
        feedback.setRating(request.getRating());
        feedback.setTitle(request.getTitle());
        feedback.setComment(request.getComment());

        feedbackRepository.save(feedback);

        return mapper.entityToFeedbackResponseDTO(feedback);
    }
}