package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.emailCode.application.gateways.CodeRepositoryGateway;
import br.com.tecflix_app.modules.emailCode.application.gateways.RandomCodeGeneratorGateway;
import br.com.tecflix_app.modules.emailCode.application.usecases.*;
import br.com.tecflix_app.modules.user.application.gateways.UserRepositoryGateway;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConfig {

  @Bean
  public ValidateAccountCodeUseCase validateAccountCodeUseCase(
      CodeRepositoryGateway codeRepositoryGateway,
      FindUserByIdUseCase findUserByIdUseCase,
      UserRepositoryGateway userRepositoryGateway) {
    return new ValidateAccountCodeUseCaseImpl(
        codeRepositoryGateway, findUserByIdUseCase, userRepositoryGateway);
  }

  @Bean
  public CreateCodeUseCase createAccountValidationCodeUseCase(
      CodeRepositoryGateway codeRepositoryGateway,
      RandomCodeGeneratorGateway randomCodeGeneratorGateway,
      FindUserByIdUseCase findUserByIdUseCase) {
    return new CreateCodeUseCaseImpl(
        codeRepositoryGateway, randomCodeGeneratorGateway, findUserByIdUseCase);
  }
}
