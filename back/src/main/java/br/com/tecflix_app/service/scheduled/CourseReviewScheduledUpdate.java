package br.com.tecflix_app.service.scheduled;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.repository.CourseRepository;
import br.com.tecflix_app.repository.EmailCodeRepository;

@Component
public class CourseReviewScheduledUpdate {
    // private final Logger LOGGER = Logger.getLogger(EmailCodeScheduledDeletion.class.getName());
    
    // @Value("${email.code.validation.time}")
    // private Long validationTime; 

    // private final CourseRepository repository;

    // @Autowired
    // public CourseReviewScheduledUpdate(CourseRepository repository) {
    //     this.repository = repository;
    // }

    // @Scheduled(cron = "0 0 12 * * ?")
    // @Transactional(rollbackFor = Exception.class)
    // public void courseReviewsUpdate() {
    //     LOGGER.info("Updating courses reviews");
    //     LocalDateTime time = LocalDateTime.now().minusNanos(validationTime);
    //     repository.deleteAllByCreatedAtBefore(time);
    // }
}
