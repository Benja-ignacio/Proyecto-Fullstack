package cl.duoc.feedback.Service;

import java.util.List;

import cl.duoc.feedback.model.Feedback;
public interface feedbackService {
    
    Feedback create(Feedback request);

    List<Feedback> getByProduct(Long productId);

    List<Feedback> getByUser(Long userId);

    void delete(Long id);

}