// Server - Main.java
package mainFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 12345; // 포트 번호 지정

    public Main() {
    }

    public void initialize() {
        System.out.println("Server 대기중."); // 서버 대기 중 메시지
    }

    private void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept(); // 클라이언트 연결 대기
                System.out.println("클라이언트가 연결되었습니다.");
                // 새로운 스레드에서 클라이언트 요청 처리 가능
                // new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
        main.run();
    }
}
