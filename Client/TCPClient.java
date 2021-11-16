//Importo i package necessari 
import java.net.*; 
import java.io.*; 
import java.util.Scanner;
import java.time.LocalDateTime;

public class TCPClient { 
    public void start() throws Exception {
        //Connessione della Socket con il Server 
        Socket socket = new Socket("localhost", 7777); 
        Scanner input = new Scanner(System.in);
        String c;
        double s;

        String localDate = LocalDateTime.now().getHour() + ":" +LocalDateTime.now().getMinute();
        System.out.println(localDate);
        PrintWriter writeDataOnTXT = new PrintWriter("data.txt");
        writeDataOnTXT.println(localDate);
        writeDataOnTXT.close();
        Model model=new Model();

        System.out.println("Tipo di operazioni disponibili:\nSomma(+)\nSottrazione(-)\nMoltiplicazione(*)\nDivisione(/)");
        System.out.println("\nInserire tipo di operazione: ");
        do{
            c=input.next();
            if(!c.equals("+") && !c.equals("-") && !c.equals("*") && !c.equals("/")){
                System.out.println("Reinserire tipo di operazione: ");
            }
        }while(!c.equals("+") && !c.equals("-") && !c.equals("*") && !c.equals("/"));
        model.setOperatore(c);
        
        
        System.out.println("\nInserire il primo operando: ");
        do{
            s=input.nextDouble();
            if(s<0 || s>9){
                System.out.println("Reinserire primo operando : ");
            }
        }while(s<0 || s>9);
        model.setOperando1(s);
        
        
        System.out.println("\nInserire il secondo operando: ");
        do{
            s=input.nextDouble();
            if(s<0 || s>9){
                System.out.println("Reinserire secondo operando : ");
            }
        }while(s<0 || s>9);
        model.setOperando2(s);
        
        
        
        //Stream di byte da passare al Socket 
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        
        objectOutputStream.writeObject(model); 
        model =(Model) objectInputStream.readObject();
        System.out.println(model); 

        //Chiusura dello Stream e del Socket 
        objectOutputStream.close(); 
        objectInputStream.close(); 
        socket.close();
        input.close();
    } 
    
    public static void main (String[] args) throws Exception { 
        TCPClient tcpClient = new TCPClient(); 
        tcpClient.start(); 
    } 
} 
