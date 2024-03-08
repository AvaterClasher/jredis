package commands;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Echo implements Command {

    @Override
    public String execute(List<String> input, ConcurrentHashMap<String, String> cache) {
        return "+" + input.get(3) + "\r\n";
    }

}