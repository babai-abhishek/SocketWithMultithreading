import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;

public class ClientHandler extends Thread {

    Vector<Socket> sockets;
    ConcurrentLinkedQueue<Socket> socs;
    boolean flag;

    public ClientHandler(ConcurrentLinkedQueue<Socket> s) {
        this.socs = s;
        sockets = new Vector<Socket>();
        flag = true;

    }

    private void receiveSockets() {
        Socket sc;
        while ((sc = socs.poll()) != null) {
            System.out.println("hello...");
            sockets.addElement(sc);

        }
    }

    @Override
    public void run() {

        flag = true;
        while (flag) {

            receiveSockets();

            int count = 1;
            Iterator iterator = sockets.iterator();
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
                    // TODO Auto-generated catch block
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