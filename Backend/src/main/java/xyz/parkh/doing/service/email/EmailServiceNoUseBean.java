//package xyz.parkh.doing.service.email;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
////@Service
//public class EmailServiceNoUseBean implements EmailService {
//
//    @Value("${mail.email}")
//    String email;
//
//    @Value("${mail.password}")
//    String password;
//
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername(email);
//        mailSender.setPassword(password);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
//
//    public void sendSimpleMessage(String to, String subject, String text) {
//        JavaMailSender javaMailSender = getJavaMailSender();
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(email);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);
//    }
//}
//
//
