package commands;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import constants.Constants;

public class SetKey implements Command {

    @Override
    public String execute(List<String> input, ConcurrentHashMap<String, String> cache) {
        String key = input.get(3);
        String value = input.get(5);
        cache.put(key, value);
        String outputString = "+" + Constants.OUTPUT_OK + "\r\n";
        return outputString;
    }

}
