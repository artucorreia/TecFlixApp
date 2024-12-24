package br.com.tecflix_app.projection;

import java.time.LocalDateTime;

public interface ReviewProjection {
    Long getId();
    Double getScore();
    String getComment();
    LocalDateTime getCreatedAt();
    UserBasicProjection getUser();
}
