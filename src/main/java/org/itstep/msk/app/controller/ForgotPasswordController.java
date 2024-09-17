package org.itstep.msk.app.controller;

import com.sun.mail.imap.Utility;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.itstep.msk.app.exeption.CustomerException;
import org.itstep.msk.app.service.ServiceCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ServiceCustomer serviceCustomer;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping(value = "/forgot_password", produces = MediaType.TEXT_HTML_VALUE)
    public String getResetPasswordFormRequest() {
        return "forgot_password_form";
    }

    @SneakyThrows
    @PostMapping("/forgot_password")
    public String processFormRequest(HttpServletRequest servletRequest, Model model) {
        try {
            String email = servletRequest.getParameter("email");
            String token = serviceCustomer.updateResetPasswordToken(email);
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest)
                    .replacePath(null)
                    .build()
                    .toUriString();
            var link = baseUrl + "/reset_password?token=" + token;
            sendEmailResetPassword(link, email);
        } catch (CustomerException customerException) {
            model.addAttribute("error", customerException.getMessage());
        }
        return "forgot_password_form";
    }

    private void sendEmailResetPassword(String link, String email) throws MessagingException {
        String toAddress = email;
        String subject = "Here is link to reset password";
        String content = "<p>Hello,<,p>" +
                "<p>You have to requested to reset password.</p>" +
                "<p>Click the" + link + " below to change password</p>"
                + "<a href= \"" + link + "\">Change my password</a>" +
                "<br>";
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage);

        helper.setFrom(System.getenv("${spring.mail.username}"));
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(mimeMailMessage);
    }
}
