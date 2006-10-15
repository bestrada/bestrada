package edu.calpoly.csc568;

import java.util.Observable;
import java.util.Observer;

public class MilitaryClock implements Observer
{
   /**
    * Discover the time from an observable ClockTimer object, then print it out 
    * onto the console in a 24-hour military clock fashion.
    */
   public void update(Observable o, Object arg)
   {
      if (o instanceof ClockTimer)
      {
         ClockTimer clock = (ClockTimer) o;
         
         int hour = clock.getHour();
         int minute = clock.getMinute();
         int second = clock.getSecond();
         
         System.out.printf("Military - %2d:%s:%s\n", hour, formatNumber(minute), formatNumber(second));
      }
   }
   
   private String formatNumber(int number)
   {
      String result = Integer.toString(number);
      if (number < 10) result = "0" + result;
      
      return result;
   }
}
