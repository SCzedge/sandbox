package com.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

public class SendEmail {
    /*
    private final JavaMailSenderImpl mailSender;
    @Autowired
    public SendEmail(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
*/
    @Autowired
    private JavaMailSenderImpl mailSender;


    public void sending() {
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper;
                helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("homePage <factoryTest@gmail.com>");
                helper.setTo("yjcho@youhost.co.kr");
                helper.setSubject("EmailTest");

                String body = "TEST_BODY";
                helper.setText(body, true);

            }
        };
        mailSender.send(preparator);
    }

}
