package com.brygade.GradeMailer;

public class MessageBean
{
   private String to;
   private String from;
   private String subject;
   private String content;
   public String getContent()
   {
      return content;
   }
   public void setContent(String content)
   {
      this.content = content;
   }
   public String getFrom()
   {
      return from;
   }
   public void setFrom(String from)
   {
      this.from = from;
   }
   public String getSubject()
   {
      return subject;
   }
   public void setSubject(String subject)
   {
      this.subject = subject;
   }
   public String getTo()
   {
      return to;
   }
   public void setTo(String to)
   {
      this.to = to;
   }
   
   
}
