package cl.duoc.feedback.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.feedback.model.Feedback;
import cl.duoc.feedback.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements feedbackService {

    private final FeedbackRepository repository;

    public FeedbackServiceImpl(FeedbackRepository repository) {
        this.repository = repository;
    }

    public Feedback create(Feedback request) {

        boolean exists = repository.existsByUserIdAndProductId(
                request.getUserId(),
                request.getProductId()
        );

        if (exists) {
            throw new RuntimeException("El usuario ya comentó este producto");
        }

        return repository.save(request);
    }

    public List<Feedback> getByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<Feedback> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}