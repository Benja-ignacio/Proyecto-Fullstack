package cl.duoc.notificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.notificaciones.model.Notification;
import cl.duoc.notificaciones.repository.notificationRepository;

@Service
public class notificationService {

    @Autowired
    private notificationRepository notificationRepository;


    public List<Notification> findAll(){
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id){
        return notificationRepository.findById(id);
    }

    public List<Notification> findByUserId(Long userId){
        return notificationRepository.findByUserId(userId);
    }

    public Notification markAsRead(Long id){
        Optional<Notification> optional = notificationRepository.findById(id);

        if (optional.isPresent()){
            Notification notification = optional.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        }        
        return null;

    }

    public void deleteById(Long id){
        notificationRepository.deleteById(id);
    }
    

}
