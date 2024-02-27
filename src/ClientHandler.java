import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true) {

            InputStream inputStream;
            OutputStream oStrem;
            try {
                inputStream = socket.getInputStream();
                oStrem = socket.getOutputStream();

                DataInputStream dataInputStream;
                DataOutputStream dataOutputStream;

                dataInputStream = new DataInputStream(inputStream);
                dataOutputStream = new DataOutputStream(oStrem);

                if (dataInputStream.available() > 0) {

                    int rcvFrmClient = dataInputStream.readInt();
                    System.out.println("request from client : " + rcvFrmClient);
                    dataOutputStream.writeInt(rcvFrmClient + 5);
                }
                /*dataInputStream.close();
                dataOutputStream.close();*/

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}