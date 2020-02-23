package edu.escuelaing.arep.reto2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.google.gson.Gson;

public class webServer {

    
    static int PORT;
    static db db;
    /**
     * This is the main method of the app. This method receives initially the clients petitions and manages 
     * them through threads
     */
    public static void main(String[] args) throws IOException {
        PORT = getPort();
        //Gson gson = new Gson();
        System.out.println("puerto "+PORT);

        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(PORT);
        System.out.println("Abierto");
        Socket clientSocket = null;
        boolean conectado = true;
        db = new db();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        while (conectado) {
            try{
                
            
                clientSocket = serverSocket.accept();
                System.out.println("client socket is cloed : "+clientSocket.isClosed());
                if(clientSocket!=null){
                System.out.println("Conectado");
                System.out.println("------------------Crear thread-----------------------");
                Thread t1 = new Thread(new worker(clientSocket));
                executor.execute(t1);
                System.out.println("------------------Creao thread-----------------------");
                
                
                System.out.println("------------------Inicio Thread-----------------------");
                
                }
                    
                
                
                System.out.println("client socket is closed 2 : "+clientSocket.isClosed());
            }
            catch(Exception e){System.out.println("error en webServer "+e);
            conectado=false;
                
                    

    
            
        }
        serverSocket.close();
        
    }

        

    }
    /**
     * This method return the port where the app works
     * @return
     */

    static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
        }

}
