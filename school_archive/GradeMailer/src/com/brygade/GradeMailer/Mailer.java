package com.brygade.GradeMailer;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPSSLTransport;

public class Mailer
{ 
   public static void main(String[] args) throws Exception
   {
      
   }
   
   public void postMail(String recipient, String subject, String message, String from)
   throws MessagingException
   {
      Properties props = new Properties();
      props.put("mail.smtp.host", "smtp.charter.net");
      props.put("mail.smtp.port", "25");
      InternetAddress sender = new InternetAddress(from);
      
      Session session = Session.getDefaultInstance(props, null);
      session.setDebug(false);
      
      Message msg = new MimeMessage(session);
      msg.setFrom(sender);
      msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      msg.setRecipient(Message.RecipientType.BCC, sender);
      msg.setSubject(subject);
      msg.setContent(message, "text/plain");
      
      SMTPSSLTransport.send(msg);
   }
}
