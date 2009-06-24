package BotEnvironment.SearchBot;

public class NoStartingLocationException extends Exception
{
    public NoStartingLocationException () {

        super();
    }

    public String getMessage() {

        return "The map must have a starting location.";
    }
}