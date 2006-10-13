package BotEnvironment.SearchBot;

public class InvalidHorizontalMapSizeException extends Exception
{
    public InvalidHorizontalMapSizeException () {

        super();
    }

    public String getMessage() {

        return "Invalid horizontal map size.";
    }
}