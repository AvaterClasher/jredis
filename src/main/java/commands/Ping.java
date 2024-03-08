package commands;
public class Ping implements Command {

    @Override
    public String execute(String input) {
        return "+PONG\r\n";
    }

}