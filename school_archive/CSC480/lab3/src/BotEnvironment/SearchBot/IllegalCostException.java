package BotEnvironment.SearchBot;

public class IllegalCostException extends Exception
{
    private String message;

    public IllegalCostException (String message) {

        super();
        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}