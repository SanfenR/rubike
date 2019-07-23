import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        final NettyClient nettyClient = new NettyClient();
        new Thread(nettyClient::connect).start();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if ("exit".equals(s)) {
                nettyClient.close();
                break;
            }
            nettyClient.sendMsg(s);
        }
    }
}
