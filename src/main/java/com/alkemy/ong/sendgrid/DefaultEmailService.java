package com.alkemy.ong.sendgrid;

import com.alkemy.ong.domain.mail.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DefaultEmailService implements EmailService {

    private String emailSender = "welcome.ong@hotmail.com";
    private String idTemplate = "d-8f7dc99cab5b485aab929917d61f81c0";

    @Override
    public void sendWelcomeEmail(String addresseeEmail) {
        Mail mail = buildMail(addresseeEmail);
        mail = buildWelcomeEmail(mail);
        this.send(mail);
    }

    private Response send(Mail mail) {

        com.sendgrid.SendGrid sg = new com.sendgrid.SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        Response response = null;

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sg.api(request);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    private Mail buildMail(String addresseeEmail) {
        Email from = new Email(emailSender);
        Email to = new Email(addresseeEmail);
        Mail mail = new Mail();
        mail.setFrom(from);
        Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.addPersonalization(personalization);
        return mail;
    }

    private Mail buildWelcomeEmail(Mail mail) {
        mail.setTemplateId(idTemplate);
        Personalization personalization = mail.getPersonalization().get(0);
        personalization.addDynamicTemplateData("name", "alk ong");
        mail.addPersonalization(personalization);
        return mail;
    }
}