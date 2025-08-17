package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

public interface CreateSocialCase {
    void execute(User user, Social social);
}
