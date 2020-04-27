import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client2 {
	public static void main(String args[]){
		Socket socket = null;
		try {
			socket = new Socket("localhost", 7001);

			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("Greetings from another client");
			
			dataOutputStream.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

