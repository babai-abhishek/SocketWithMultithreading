import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException{
	//	ConcurrentLinkedQueue<Socket> socketConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();

		ServerSocket serverSocket = new ServerSocket(3346);
		boolean flag = true;

		while(flag){

			System.out.println("server ready to listen new connection ...... ");
			Socket socket = serverSocket.accept();

			ClientHandler cHandler = new ClientHandler(socket);
			cHandler.setDaemon(true);
			cHandler.start();

			//cHandler.addNewConnection(socket);

		}
		//cHandler.stopHandler();
	}
}

