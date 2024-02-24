import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientHandler extends Thread {

    Vector<Socket> socketVector;
    ConcurrentLinkedQueue<Socket> socketConcurrentLinkedQueue;
    boolean flag;

    public ClientHandler(ConcurrentLinkedQueue<Socket> socketConcurrentLinkedQueue) {
        this.socketConcurrentLinkedQueue = socketConcurrentLinkedQueue;
        this.socketVector = new Vector<Socket>();
        flag = true;

    }

    private void receiveSockets() {
        Socket socket;
        while ((socket = socketConcurrentLinkedQueue.poll()) != null) {
            System.out.println("hello...");
            socketVector.addElement(socket);

        }
    }

    @Override
    public void run() {

        flag = true;
        while (flag) {

            receiveSockets();

            int count = 1;
            Iterator iterator = socketVector.iterator();
            while (iterator.hasNext()) {
                Socket soc = (Socket) iterator.next();
                //for(Socket soc : sockets){
                InputStream inputStream;
                OutputStream oStrem;
                try {
                    inputStream = soc.getInputStream();
                    oStrem = soc.getOutputStream();

                    DataInputStream dataInputStream;
                    DataOutputStream dataOutputStream;

                    dataInputStream = new DataInputStream(inputStream);
                    dataOutputStream = new DataOutputStream(oStrem);

                    int rcvFrmClient = -1;

                    if (dataInputStream.available() > 0) {

                        rcvFrmClient = dataInputStream.readInt();
                        System.out.println("request from client " + count + " : " + rcvFrmClient);
                        dataOutputStream.writeInt(rcvFrmClient + 5);
                    }
						/*dataInputStream.close();
						dataOutputStream.close();*/


                } catch (IOException e) {
                    e.printStackTrace();
                }
                count++;
            }

        }
    }

    public void stopHandler() {
        // TODO Auto-generated method stub
        flag = false;


    }
}