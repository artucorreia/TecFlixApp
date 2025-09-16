package br.com.tecflix_app.modules.emailCode.infra.gateways.mapper;

import br.com.tecflix_app.modules.emailCode.application.domain.entity.EmailCode;
import br.com.tecflix_app.modules.emailCode.infra.persistence.EmailCodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CodeGatewaysMapper {
  EmailCode map(EmailCodeEntity emailCodeEntity);

  EmailCodeEntity map(EmailCode emailCode);
}
