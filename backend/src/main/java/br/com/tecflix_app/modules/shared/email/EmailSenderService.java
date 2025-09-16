package br.com.tecflix_app.modules.shared.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class EmailSenderService {
  private final JavaMailSender javaMailSender;

  public void sendEmailCode(
      @NotNull String subject,
      @NotNull String fromAddress,
      @NotNull String emailTemplatePath,
      @NotNull String email,
      @NotNull String name,
      @NotNull String templateMessage,
      @NotNull String buttonText,
      @NotNull String url,
      @NotNull String helperText)
      throws MessagingException, IOException {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo("<" + email + ">");
    helper.setFrom(fromAddress);
    helper.setSubject(subject);

    String template = getTemplate(emailTemplatePath);
    template = template.replace("${name}", name);
    template = template.replace("${message}", templateMessage);
    template = template.replace("${button_text}", buttonText);

    template = template.replace("${url}", url);
    template = template.replace("${currentYear}", String.valueOf(LocalDateTime.now().getYear()));

    helper.setText(helperText, template);

    javaMailSender.send(message);
  }

  private String getTemplate(String path) throws IOException {
    ClassPathResource resource = new ClassPathResource(path);
    return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
  }
}
