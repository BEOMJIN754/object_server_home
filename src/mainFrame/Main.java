// Server - Main.java
package mainFrame;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Main {
	private static final int PORT = 12345; // 포트 번호 지정

	public Main() {
	}

	public void initialize() {
		System.out.println("Server 대기중."); // 서버 대기 중 메시지
	}

	private void run() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				try (Socket clientSocket = serverSocket.accept();
						ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

				} catch (IOException e) {
					e.printStackTrace();
				}
			} // 새로운 스레드에서 클라이언트 요청 처리 가능
				// new ClientHandler(clientSocket).start();
		}

	}

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.initialize();
		main.run();
	}
}
