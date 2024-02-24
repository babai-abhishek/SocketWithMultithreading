import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {
	public static void main(String args[]) throws IOException{
		ConcurrentLinkedQueue<Socket> socketConcurrentLinkedQueue = new ConcurrentLinkedQueue<Socket>();


		ClientHandler cHandler = new ClientHandler(socketConcurrentLinkedQueue);
		cHandler.setDaemon(true);
		cHandler.start();

		ServerSocket serverSocket = new ServerSocket(3346);
		boolean flag = true;

		while(flag){

			System.out.println("server ready to listen new connection ...... ");
			Socket socket = serverSocket.accept();
			socketConcurrentLinkedQueue.add(socket);

			//cHandler.addNewConnection(socket);

		}
		//cHandler.stopHandler();
	}
}

