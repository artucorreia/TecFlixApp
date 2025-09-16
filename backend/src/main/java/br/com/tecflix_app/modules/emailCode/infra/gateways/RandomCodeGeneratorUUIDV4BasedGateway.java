package br.com.tecflix_app.modules.emailCode.infra.gateways;

import br.com.tecflix_app.modules.emailCode.application.gateways.RandomCodeGeneratorGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomCodeGeneratorUUIDV4BasedGateway implements RandomCodeGeneratorGateway {
  @Override
  public String generate() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
