import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        NettyService nettyService = new NettyService();
        nettyService.start();
        new Thread(nettyService::start).start();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if ("exit".equals(s)) {
                nettyService.close();
                break;
            }
            nettyService.sendMsg(s);
        }
    }
}
