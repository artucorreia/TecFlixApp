package br.com.tecflix_app.modules.emailCode.application.usecases;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeRepositoryGateway;
import br.com.tecflix_app.modules.emailCode.application.gateways.RandomCodeGeneratorGateway;
import br.com.tecflix_app.modules.user.application.domain.entity.User;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.logging.Logger;

public class CreateCodeUseCaseImpl implements CreateCodeUseCase {
  private final Logger LOGGER =
      Logger.getLogger(CreateCodeUseCaseImpl.class.getName());
  private final CodeRepositoryGateway codeRepositoryGateway;
  private final RandomCodeGeneratorGateway randomCodeGeneratorGateway;
  private final FindUserByIdUseCase findUserByIdUseCase;

  public CreateCodeUseCaseImpl(
      CodeRepositoryGateway codeRepositoryGateway,
      RandomCodeGeneratorGateway randomCodeGeneratorGateway,
      FindUserByIdUseCase findUserByIdUseCase) {
    this.codeRepositoryGateway = codeRepositoryGateway;
    this.randomCodeGeneratorGateway = randomCodeGeneratorGateway;
    this.findUserByIdUseCase = findUserByIdUseCase;
  }

  @Override
  public EmailCode execute(UUID userId) {
    LOGGER.info("Creating a validation code");
    User user = findUserByIdUseCase.execute(userId);

    codeRepositoryGateway.deleteByUserId(userId);

    EmailCode emailCode = new EmailCode();
    emailCode.setUser(user);
    emailCode.setCode(randomCodeGeneratorGateway.generate());
    emailCode.setCreatedAt(LocalDateTime.now());
    emailCode.setDeleted(false);

    return codeRepositoryGateway.save(emailCode);
  }
}
