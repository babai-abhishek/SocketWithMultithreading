import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(7001);
		System.out.println("Server ready to listen ");
		
		Socket socket = null;
		boolean flag = true;
		
		while(flag){
			socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			
			System.out.println(dataInputStream.readUTF());
		}
		
		
		socket.close();
		serverSocket.close();
		
	}
}

