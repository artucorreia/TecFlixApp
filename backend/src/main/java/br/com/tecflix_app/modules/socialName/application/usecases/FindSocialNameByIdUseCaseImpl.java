package br.com.tecflix_app.modules.socialName.application.usecases;

import br.com.tecflix_app.modules.shared.exception.general.ResourceNotFoundException;
import br.com.tecflix_app.modules.socialName.application.domain.entity.SocialName;
import br.com.tecflix_app.modules.socialName.application.gateways.SocialNameRepositoryGateway;

import java.util.logging.Logger;

public class FindSocialNameByIdUseCaseImpl implements FindSocialNameByIdUseCase {
  private final Logger LOGGER = Logger.getLogger(FindSocialNameByIdUseCaseImpl.class.getName());
  private final SocialNameRepositoryGateway socialNameRepositoryGateway;

  public FindSocialNameByIdUseCaseImpl(SocialNameRepositoryGateway socialNameRepositoryGateway) {
    this.socialNameRepositoryGateway = socialNameRepositoryGateway;
  }

  @Override
  public SocialName execute(Long id) {
    LOGGER.info("Finding social name by id: " + id);
    return socialNameRepositoryGateway
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    "Nenhum nome de rede social encontrado para este id: " + id));
  }
}
