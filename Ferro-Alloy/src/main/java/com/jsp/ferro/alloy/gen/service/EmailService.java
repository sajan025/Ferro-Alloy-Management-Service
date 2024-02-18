package com.jsp.ferro.alloy.gen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.ferro.alloy.qlty.entity.QualityRequest;
import com.jsp.ferro.alloy.util.MailUtil;

@Service
public class EmailService {
	
	@Autowired
	private MailUtil mailUtil;
	static ResourceBundle bundleMail = ResourceBundle.getBundle("application");

    public void sendEmail(QualityRequest obj,final String toMail) {
    	try {
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.execute(new Runnable() {
				public void run() {
					List<Object> objList = new ArrayList<>();
					String subject ="Quality Request initiated with (grade :"+' '+obj.getExternalGrade()+ ") ";
					String body = "Dear Sir/Mam, Quality request initiated with grade "+obj.getExternalGrade()+" Regards";
					final String fromEmail = bundleMail.getString("app.email.from");
					final String ccUser[] = new String[1];
					ccUser[0] = null;
					objList.add(obj);
					objList.add(bundleMail.getString("app.ip.url"));
					mailUtil.sendMailTOSms(objList, subject,fromEmail, toMail, ccUser, body);

				}
			});
			executorService.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }

}
