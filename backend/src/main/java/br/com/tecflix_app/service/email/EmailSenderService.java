package br.com.tecflix_app.service.email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.exception.email.EmailSendingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
    private final Logger LOGGER = Logger.getLogger(EmailSenderService.class.getName());

    @Value("${website.url}")
    private String WEBSITE_URL;

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailCode(
            String email,
            String name,
            String templateMessage,
            String buttonText,
            String path,
            String helperText) {
        LOGGER.info("Sending email code to user");

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("<" + email + ">");
            helper.setFrom("no-reply@tecflix");
            helper.setSubject("Validação de Email");

            String template = getTemplate("templates/generic-mail-template.html");
            template = template.replace("${name}", name);
            template = template.replace("${message}", templateMessage);
            template = template.replace("${button_text}", buttonText);

            String url = WEBSITE_URL + path;

            template = template.replace("${url}", url);
            template = template.replace("${currentYear}", String.valueOf(LocalDateTime.now().getYear()));

            helper.setText(helperText, template);

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendingException("Ocorreu um erro ao enviar o email");
        }
    }

    private String getTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
