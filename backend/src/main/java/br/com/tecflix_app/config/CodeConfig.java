package br.com.tecflix_app.config;

import br.com.tecflix_app.modules.emailCode.application.gateways.CodeRepositoryGateway;
import br.com.tecflix_app.modules.emailCode.application.gateways.RandomCodeGeneratorGateway;
import br.com.tecflix_app.modules.emailCode.application.usecases.CreateAccountValidationCodeUseCase;
import br.com.tecflix_app.modules.emailCode.application.usecases.CreateAccountValidationCodeUseCaseImpl;
import br.com.tecflix_app.modules.user.application.usecases.FindUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConfig {
  @Bean
  public CreateAccountValidationCodeUseCase createAccountValidationCodeUseCase(
      CodeRepositoryGateway codeRepositoryGateway,
      RandomCodeGeneratorGateway randomCodeGeneratorGateway,
      FindUserByIdUseCase findUserByIdUseCase) {
    return new CreateAccountValidationCodeUseCaseImpl(
        codeRepositoryGateway, randomCodeGeneratorGateway, findUserByIdUseCase);
  }
}
