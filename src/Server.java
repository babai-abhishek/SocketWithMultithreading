import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {
	public static void main(String args[]) throws IOException{

		//List<Socket> sockets = new ArrayList<Socket>();
		//Vector<Socket> sockets = new Vector<Socket>();
		ConcurrentLinkedQueue<Socket> sockets = new ConcurrentLinkedQueue<Socket>();


		ClientHandler cHandler = new ClientHandler(sockets);
		cHandler.setDaemon(true);
		cHandler.start();

		ServerSocket ss = new ServerSocket(3346);
		boolean flag = true;

		while(flag){

			System.out.println("server ready to listen new connection ...... ");
			Socket s = ss.accept();
			sockets.add(s);

			//cHandler.addNewConnection(s);

		}
		//cHandler.stopHandler();
	}
}

