package com.brygade.GradeMailer;

import java.util.List;

public class Driver
{
   public static void main(String[] args) throws Exception
   {
      String file = "D:/home/bestrada/232/asgn1/grades.csv";
      MessageGenerator gen = new MessageGenerator(file);
      Mailer mailer = new Mailer();
      
      List<MessageBean> messages = gen.generateMessages();
      

      for (MessageBean m: messages)
      {
	 boolean itsGood = false;
	 
         while (!itsGood)
	 {
            try
            {
               System.out.print("Sending mail to: " + m.getTo() + "... ");
      	       mailer.postMail(m.getTo(), m.getSubject(), m.getContent(), m.getFrom());
      	       System.out.println("Success!\n");
      	       itsGood = true;
      	    }
            catch (Exception e)
            {
               System.out.println("Error");
               itsGood = false;
            }
            /* wait 2.5 seconds until we try the next one */
            Thread.sleep(1000);
	 }
      }
      
      System.out.println("\n---All Done---");
   }
}
