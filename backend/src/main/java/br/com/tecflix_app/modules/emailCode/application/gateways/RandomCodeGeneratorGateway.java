package br.com.tecflix_app.modules.emailCode.application.gateways;

/**
 * Gateway interface for generating random codes.
 *
 * <p>This interface provides an abstraction for the process of creating a random string, such as a
 * validation code or a password reset token.
 */
public interface RandomCodeGeneratorGateway {

  /**
   * Generates a new random code.
   *
   * @return A randomly generated string code.
   */
  String generate();
}
