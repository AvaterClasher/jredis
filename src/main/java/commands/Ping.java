package commands;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Ping implements Command {

    @Override
    public String execute(List<String> input, ConcurrentHashMap<String, String> cache) {
        return "+PONG\r\n";
    }

}