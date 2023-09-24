package com.kadirgurturk.demo.buisness.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${VERIFY_EMAIL_HOST}")
    private String host;
    @Value("${EMAIL_USERANAME}")
    private String fromEmail;

    private final TemplateEngine templateEngine; // we'll use this class when we try to connect to out html template.

    private final JavaMailSender emailSender;


    @Async
    public void sendHtmlMail(String firstName, String to)
    {
        try{
            Context context = new Context(); // we need use the thymleaf for to connect our html template which we send with email
            context.setVariable("name",firstName);
            String text = templateEngine.process("emailTemplate",context); // we connect our context to tamplate, with this way we can use this variables into tamplate
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
            helper.setPriority(1);
            helper.setSubject("New User Account Verification");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text,true); // we change this method

        }catch (Exception exp){
            System.out.println(exp.getMessage());
            throw new RuntimeException(exp);
        }

    }

}
