package com.jsp.ferro.alloy.util;

import java.io.StringWriter;
import java.util.List;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class MailUtil {
	
	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	HttpServletRequest request;
	BodyPart messageBodyPart;
	
	public void sendMailTOSms(Object Object, String subject, final String fromEmail, String toEmail,
			String ccUser[],  String body) {

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			Multipart multipart = new MimeMultipart("related");
			messageBodyPart = new MimeBodyPart();

			StringWriter out = new StringWriter();
//			Template template = LoadVelocityTemplate.getTemplate(templateName);
//
//			VelocityContext context = new VelocityContext();
//			context.put("date", new DateTool());
//			context.put("listObject", listObject);
//			context.put("lstobjComment", lstobjComment);

			//template.merge(context, out);
			//System.out.println("====>"+out.toString());
			messageBodyPart.setContent(body, "text/html; charset=utf-8");
			messageBodyPart.setHeader("Content-Transfer-Encoding", "8bit");

			multipart.addBodyPart(messageBodyPart);

			helper.setSubject(subject);
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);
//			if (ccUser[0] != "")
//				helper.setCc(ccUser);
			mimeMessage.setContent(multipart);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
