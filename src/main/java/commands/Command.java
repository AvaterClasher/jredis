package commands;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface Command {
    String execute(List<String> input, ConcurrentHashMap<String, String> cache);
}