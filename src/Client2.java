import java.io.*;
import java.net.Socket;

public class Client2 {

    public static void main(String args[]) {

        try {
            Socket socket = new Socket("localhost", 3346);

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            int i = 100;
            boolean flag = true;

            while (flag) {
                System.out.println("Client 2 is sending ..... " + i);
                dataOutputStream.writeInt(i);
                i++;

                System.out.println("Server is sending reply the value as : " + dataInputStream.readInt());
				/*dataInputStream.close();
				dataOutputStream.close();*/
                Thread.sleep(5000);
            }

            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

