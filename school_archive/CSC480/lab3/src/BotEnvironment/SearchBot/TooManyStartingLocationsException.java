package BotEnvironment.SearchBot;

public class TooManyStartingLocationsException extends Exception
{
    public TooManyStartingLocationsException () {

        super();
    }

    public String getMessage() {

        return "The map cannot contain more than one starting location.";
    }
}