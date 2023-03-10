package com.footstep.domain.mail.service;

import com.footstep.domain.base.BaseException;
import com.footstep.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@Transactional
@Service
@PropertySource("classpath:mail/email.properties")
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailForBlock(String to) throws BaseException, MessagingException , UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        String text = "";
        text += "<div style='margin:20px;'>";
        text += "<h1> 당신의 발자취 계정 정지안내입니다.</h1>";
        text += "<br>";
        text += "<p> 신고 접수가 3회 누적되어 계정이 한 달간 정지되었습니다. </p>";
        text += "<br>";
        text += "<p> <b>해당 계정의 게시글은 모두 삭제 처리 예정입니다.</b> </p>";
        text += "<br>";
        text += "<p> 문의할 내용이 있다면 <b>footstepdangbal@gmail.com</b>으로 문의부탁드립니다.</p>";
        text += "<p> 감사합니다.</p>";

        messageHelper.setFrom("footstepdangbal@gmail.com", "당신의 발자취");
        messageHelper.setTo(to);
        messageHelper.setSubject("당신의 발자취 계정 정지 안내드립니다.");
        messageHelper.setText(text, true);

        javaMailSender.send(message);
    }

    public void sendMailForReport(String to, String nickname, String title, String reason) throws BaseException, MessagingException , UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        String text = "";
        if (title.equals("")) {
            text += "<div style='margin:20px;'>";
            text += "<h1> 당신의 발자취 신고 안내입니다.</h1>";
            text += "<br>";
            text += "<p> <b>" + nickname + "</b>님은 신고당하셨습니다. </p>";
            text += "<h2> 신고 사유 </h2>";
            text += "<p>" + reason + "</p>";
            text += "<br>";
            text += "<p> 다른 사람에게 피해를 주는 말이나 도배는 삼가해주시기 바랍니다.</p>";
            text += "<br>";
            text += "<p> 문의할 내용이 있다면 <b>footstepdangbal@gmail.com</b>으로 문의부탁드립니다.</p>";
            text += "<p> 감사합니다. </p>";
        } else {
            text += "<div style='margin:20px;'>";
            text += "<h1> 당신의 발자취 신고 안내입니다.</h1>";
            text += "<br>";
            text += "<p> <b>" + nickname + "</b>님께서 작성하신 <b>'" + title  + "'</b>이 신고 당하였습니다.</p>";
            text += "<h2> 신고 사유 </h2>";
            text += "<p>" + reason + "</p>";
            text += "<br>";
            text += "<p> 다른 사람에게 피해를 주는 말이나 도배는 삼가해주시기 바랍니다.</p>";
            text += "<br>";
            text += "<p> 문의할 내용이 있다면 <b>footstepdangbal@gmail.com</b>으로 문의부탁드립니다.</p>";
            text += "<p> 감사합니다. </p>";
        }

        messageHelper.setFrom("footstepdangbal@gmail.com", "당신의 발자취");
        messageHelper.setTo(to);
        messageHelper.setSubject("당신의 발자취 신고 안내드립니다.");
        messageHelper.setText(text, true);

        javaMailSender.send(message);
    }
}
