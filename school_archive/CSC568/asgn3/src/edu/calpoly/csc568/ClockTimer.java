package edu.calpoly.csc568;

import java.util.Calendar;
import java.util.Observable;

public class ClockTimer extends Observable
{
   private Calendar _time;
   
   /**
    * constructs a new Observable ClockTimer object 
    */
   public ClockTimer()
   {
      /* reset the time to right now */
      _time = Calendar.getInstance();
   }
   
   /** 
    * get the corresponding hour of the time when the most recent tick()
    * was invoked
    * @return an hour between 0 and 24
    */
   public int getHour()
   {
      return _time.get(Calendar.HOUR_OF_DAY);
   }
   
   /**
    * get the corresponding minute of the time when the most recent tick()
    * was invoked
    * @return a minute between 0 and 60 
    */
   public int getMinute()
   {
      return _time.get(Calendar.MINUTE);
   }
   
   /**
    * get the corresponding second of the time when the most recent tick()
    * was invoked
    * @return a second between 0 and 60
    */
   public int getSecond()
   {
      return _time.get(Calendar.SECOND);
   }
   
   public void tick()
   {
      /* reset the time to right now */
      _time = Calendar.getInstance();
      super.setChanged();
      
      super.notifyObservers();
   }
}
