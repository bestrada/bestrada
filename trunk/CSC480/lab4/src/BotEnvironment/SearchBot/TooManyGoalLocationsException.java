package BotEnvironment.SearchBot;

public class TooManyGoalLocationsException extends Exception
{
    public TooManyGoalLocationsException () {

        super();
    }

    public String getMessage() {

        return "The map cannot contain more than one goal location.";
    }
}