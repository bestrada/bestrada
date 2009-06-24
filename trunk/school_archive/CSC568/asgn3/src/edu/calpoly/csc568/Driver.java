package edu.calpoly.csc568;

public class Driver
{
   public static void main(String[] args) throws Exception
   {
      Driver driver = new Driver();
      driver.run();
   }
   
   public synchronized void run() throws Exception
   {
      ClockTimer timer = new ClockTimer();
      TwelveHourClock standard = new TwelveHourClock();
      MilitaryClock military = new MilitaryClock();
      
      timer.addObserver(standard);
      timer.addObserver(military);
      
      while(true)
      {
         timer.tick();
         this.wait(1000);
      }
   }
}
