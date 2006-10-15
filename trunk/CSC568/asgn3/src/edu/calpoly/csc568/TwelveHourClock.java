package edu.calpoly.csc568;

import java.util.Observable;
import java.util.Observer;

public class TwelveHourClock implements Observer
{
   /**
    * Discover the time from an observable ClockTimer object, then print it out 
    * onto the console in a standard, 12-hour AM/PM fashion.
    * 
    * @param o a ClockTimer object
    * @param arg ignored
    */
   public void update(Observable o, Object arg)
   {
      if (o instanceof ClockTimer)
      {
         ClockTimer clock = (ClockTimer) o;
         int hour = clock.getHour();
         int minute = clock.getMinute();
         int second = clock.getSecond();
         
         String ampm = hour < 12 ? "AM" : "PM";
         switch (hour)
         {
            case 0:  hour = 12;        break;
            case 24: hour = 12;        break;
            default: hour = hour % 12; break;
         }
         System.out.printf("Standard - %2d:%s:%s %s\n", hour, formatNumber(minute), formatNumber(second), ampm);
      }
   }
   
   private String formatNumber(int number)
   {
      String result = Integer.toString(number);
      if (number < 10) result = "0" + result;
      
      return result;
   }
}
