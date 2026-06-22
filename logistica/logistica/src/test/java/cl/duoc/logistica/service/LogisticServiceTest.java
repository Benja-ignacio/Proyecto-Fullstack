// package cl.duoc.logistica.service;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;

// import cl.duoc.logistica.client.NotificationClient;
// import cl.duoc.logistica.client.OrderClient;
// import cl.duoc.logistica.client.PaymentClient;
// import cl.duoc.logistica.client.UserClient;
// import cl.duoc.logistica.dto.responses.LogisticResponseDTO;
// import cl.duoc.logistica.enums.Status;
// import cl.duoc.logistica.mapper.LogisticMapper;
// import cl.duoc.logistica.model.Logistic;
// import cl.duoc.logistica.repository.LogisticRepository;

// import static org.assertj.core.api.Assertions.assertThat;


// public class LogisticServiceTest {
//     @Test
//         void testGetAllLogistic() {
//         LogisticRepository logisticRepository = Mockito.mock(LogisticRepository.class);
//         LogisticMapper mapper = Mockito.mock(LogisticMapper.class); // ← faltaba este
//         OrderClient orderClient = Mockito.mock(OrderClient.class);
//         UserClient userClient = Mockito.mock(UserClient.class);
//         NotificationClient notificationClient = Mockito.mock(NotificationClient.class);
//         PaymentClient paymentClient = Mockito.mock(PaymentClient.class);

//         LogisticService logisticService = new LogisticService(logisticRepository, mapper, orderClient, userClient, notificationClient, paymentClient);

//         Logistic logistic = new Logistic(1L, 1L, 1L, BigDecimal.valueOf(4), BigDecimal.valueOf(4999), Status.WAITING_PAYMENT, LocalDateTime.now().plusDays(7), null, null, null);
//         Mockito.when(logisticRepository.findAll()).thenReturn(List.of(logistic));

//         List<LogisticResponseDTO> result = logisticService.getAll();

//         assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
//     }
// }
