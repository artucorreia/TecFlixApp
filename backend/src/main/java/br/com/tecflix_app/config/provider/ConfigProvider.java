package br.com.tecflix_app.config.provider;

import java.time.Duration;

public interface ConfigProvider {
  String getApiKey();

  Duration getRefreshTokenDuration();

  Duration getTokenDuration();

  String getTokenIssuer();

  String getTokenSecret();

  String getFrontEndUrl();

  Duration getEmailCodeValidationTime();
}
