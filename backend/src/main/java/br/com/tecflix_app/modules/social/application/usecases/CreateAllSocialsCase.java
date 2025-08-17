package br.com.tecflix_app.modules.social.application.usecases;

import br.com.tecflix_app.modules.social.application.domain.entity.Social;
import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.List;

public interface CreateAllSocialsCase {
  void execute(User user, List<Social> socials);
}
