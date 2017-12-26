package com.hengyuan.hicash.domain.service.notic;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.hengyuan.hicash.exception.SendMailException;

public class EmailSendClient {
	
	private static final Properties props = new Properties();
	private static InternetAddress mailFromAddress;

	private static Logger logger = Logger.getLogger(EmailSendClient.class);

	static {
		try {
			props.load(new ClassPathResource("properties/mail.properties")
					.getInputStream());
			mailFromAddress = new InternetAddress(props.getProperty("address"),
					props.getProperty("display.name"));
		} catch (IOException e) {
			logger.fatal("邮件服务初始化失败", e);
		}
	}
	
	public synchronized static void sendMail(String emailAddress, String title, String content,
			File[] files) throws SendMailException {

		// 创建邮件会话
		Session session = Session.getDefaultInstance(props,
				new Authenticator() { // 验账账户
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(props
								.getProperty("username"), props
								.getProperty("password"));
					}
				});
		
		if (emailAddress == null) {
			logger.error("邮箱为空，不调用发送");
			return;
		}

		try {
			// 定义邮件信息
			MimeMessage message = new MimeMessage(session);
			message.setFrom(mailFromAddress);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailAddress));
			// 添加邮件发送时间
			message.setSentDate(Calendar.getInstance().getTime());
			// 要编码，否则中文会出乱码
			message.setSubject(MimeUtility.encodeText(title,
					props.getProperty("encoding"), "B"));
			MimeMultipart mmp = new MimeMultipart();
			MimeBodyPart text = new MimeBodyPart();
			// "text/plain"是文本型，没有样式，
			// "text/html"是html样式，可以解析html标签
			text.setContent(content,
					"text/html;charset=" + props.getProperty("encoding"));
			mmp.addBodyPart(text); // 加入邮件正文

			// 处理附件，可以添加多个附件
			if (files != null) {
				for (File file : files) {
					MimeBodyPart mbpFile = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(file);
					mbpFile.setDataHandler(new DataHandler(fds));
					mbpFile.setFileName(MimeUtility.encodeText(fds.getName(),
							props.getProperty("encoding"), "B"));
					mmp.addBodyPart(mbpFile);
				}
			}
			message.setContent(mmp);
			// 发送消息
			Transport.send(message);
		} catch (Exception e) {
			throw new SendMailException("邮件发送失败", e);
		}
	}
}
