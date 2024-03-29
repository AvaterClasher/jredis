import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import commands.Echo;
import commands.Ping;
import constants.Constants;
import commands.Get;
import commands.SetKey;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private ConcurrentHashMap<String, String> cache;

    public ClientHandler(Socket socket, ConcurrentHashMap<String, String> cache) {
        this.clientSocket = socket;
        this.cache = cache;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("command received " + input);
                if (input.startsWith("*")) {
                    int numberOfItems = Integer.parseInt(input.substring(1));
                    List<String> commands = new ArrayList<>(numberOfItems * 2); // size of command
                    for (int i = 0; i < numberOfItems * 2; i++) {
                        commands.add(in.readLine());
                    }

                    switch (commands.get(1).toLowerCase()) {
                        case Constants.PING:
                            sendResponse(new Ping().execute(commands, cache));
                            break;
                        case Constants.ECHO:
                            sendResponse(new Echo().execute(commands, cache));
                            break;
                        case Constants.GET:
                            sendResponse(new Get().execute(commands, cache));
                            break;
                        case Constants.SET:
                            sendResponse(new SetKey().execute(commands, cache));
                            break;
                        default:
                            sendResponse("invalid commands");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendResponse(String response) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.print(response);
            out.flush();
        } catch (IOException ex) {
            throw new RuntimeException("caught error while sending data to clients");
        }
    }

}