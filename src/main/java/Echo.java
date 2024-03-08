public class Echo implements Command {

    @Override
    public String execute(String input) {
        System.out.println("Echo: " + input);
        return "+" + input + "\r\n";
    }

}