//Importo i package
import java.net.*;
import java.io.*;

//Classe Server per attivare la Socket
public class TCPParallelServer {
    public void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(7777);

        System.out.println(" Attesa ");
        Socket socket = serverSocket.accept();
        System.out.println("Ricezione una chiamata di apertura da:\n" + socket);

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            
            Model model =(Model) objectInputStream.readObject();
            String operatore=model.getOperatore();


            if(operatore.equals("+")){
                model.setRis(Somma(model.getOperando1(),model.getOperando2()));
            }
            else if(operatore.equals("-")){
                model.setRis(Sottrazione(model.getOperando1(),model.getOperando2()));
            }
            else if(operatore.equals("*")){
                model.setRis(Moltiplicazione(model.getOperando1(),model.getOperando2()));
            }
            else{ 
                model.setRis(Divisione(model.getOperando1(),model.getOperando2()));
            }

            objectOutputStream.writeObject(model);
            System.out.println(model);

            objectOutputStream.close();
            objectInputStream.close();
            System.out.println("Ricezione una chiamata di chiusura da:\n" + socket + "\n");
            socket.close();
        }
        catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        serverSocket.close();
    }

    public Double Somma(Double n1,Double n2){
        return n1+n2;   
    }
    
    public Double Sottrazione(Double n1,Double n2){
        return n1-n2;   
    }
    
    public Double Moltiplicazione(Double n1,Double n2){
        return n1*n2;   
    }
    
    public Double Divisione(Double n1,Double n2){
        return n1/n2;   
    }

    public static void main (String[] args) throws Exception {
        TCPParallelServer tcpServer = new TCPParallelServer();
        tcpServer.start();
    }
}
