package br.com.tecflix_app.service.scheduled;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.tecflix_app.repository.CourseRepository;

@Component
public class CourseReviewScheduledUpdate {
    private final Logger LOGGER = Logger.getLogger(CourseReviewScheduledUpdate.class.getName());
    private final CourseRepository repository;

    @Autowired
    public CourseReviewScheduledUpdate(CourseRepository repository) {
        this.repository = repository;
    }

    // @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(fixedRate = 10000) // test
    @Transactional(rollbackFor = Exception.class)
    public void courseReviewsUpdate() {
        LOGGER.info("Updating courses reviews");
        repository.updateCourseReviews();
    }
}
