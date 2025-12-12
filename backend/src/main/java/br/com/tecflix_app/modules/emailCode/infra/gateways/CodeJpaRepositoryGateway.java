package br.com.tecflix_app.modules.emailCode.infra.gateways;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.application.gateways.CodeRepositoryGateway;
import br.com.tecflix_app.modules.emailCode.infra.gateways.mapper.CodeGatewaysMapper;
import br.com.tecflix_app.modules.emailCode.infra.persistence.EmailCodeEntity;
import br.com.tecflix_app.modules.emailCode.infra.persistence.EmailCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CodeJpaRepositoryGateway implements CodeRepositoryGateway {
  private final EmailCodeRepository emailCodeRepository;
  private final CodeGatewaysMapper codeGatewaysMapper;

  @Override
  public Optional<EmailCode> findByCodeAndUserId(String code, UUID userId) {
    Optional<EmailCodeEntity> emailCodeEntityOptional =
        emailCodeRepository.findByCodeAndUserId(code, userId);
    return emailCodeEntityOptional.map(codeGatewaysMapper::map);
  }

  @Override
  public EmailCode save(EmailCode code) {
    EmailCodeEntity emailCodeEntity = codeGatewaysMapper.map(code);
    EmailCodeEntity savedEmailCodeEntity = emailCodeRepository.save(emailCodeEntity);
    return codeGatewaysMapper.map(savedEmailCodeEntity);
  }

  @Override
  public void deleteByUserId(UUID userId) {
    emailCodeRepository.deleteByUserId(userId);
  }
}
