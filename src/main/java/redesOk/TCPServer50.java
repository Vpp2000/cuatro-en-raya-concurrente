package redesOk;




import java.io.BufferedReader;


import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer50 {
    private String message;
    boolean terminado=false;
    int jugada = 0;
    int nrcli = 0,max_clients=2;
    int modalidad=4;
    int[] alturas = {9,9,9,9,9,9,9,9,9,9};
    String[][] juego = new String[10][10];
    public static final int SERVERPORT = 4444;
    private OnMessageReceived messageListener = null;
    private boolean running = false;
    TCPServerThread50[] sendclis = new TCPServerThread50[10];

    PrintWriter mOut;
    BufferedReader in;
    
    ServerSocket serverSocket;

    
    //el constructor pide una interface OnMessageReceived
    public TCPServer50(OnMessageReceived messageListener) {
        this.messageListener = messageListener;
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
            	juego[i][j]=".";
            }
        }
    }
    
    public OnMessageReceived getMessageListener(){
        return this.messageListener;
    }
    
    public void sendMessageTCPServer(String message){
    	jugada=(jugada+1);
    	System.out.println("Jugada "+jugada+" Clientes:"+nrcli);
    	int columna = Integer.parseInt(message);
    	System.out.println("Columna "+columna);
    	//String.valueOf((char)(nrcli+65))
        
    	juego[alturas[columna-1]][columna-1] = String.valueOf((char)((jugada-1)%nrcli+65));
        
        //System.out.println("Fila: "+alturas[columna-1]+" Columna: "+(columna-1));
        //System.out.println(Comprobacion_Juego.check_vertical_down(this.juego, 7, 0 ));
        //System.out.println(Comprobacion_Juego.check_vertical_down(this.juego, alturas[columna-1],columna-1));

        
        String rpta ="";
        rpta = rpta + "\r\nJugador "+jugada+"\n";
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                System.out.print(juego[i][j]);
                rpta = rpta + juego[i][j];
                
            }
            rpta = rpta + "\r\n";
            System.out.println("");
            
        }
        if(Comprobacion_Juego.check_winner(juego,alturas[columna-1],columna-1,modalidad)) {
        	System.out.println("Juego terminado");
        	rpta = rpta + "\nGame Over, gano jugador "+this.jugada;
        	this.terminado=true;
        }
        alturas[columna-1]--;
        jugada = jugada%nrcli;  // MODIFICAR SI TE VAS A LA VERGA
    	for (int i = 1; i <= nrcli; i++) {
    		
            sendclis[i].sendMessage(rpta);
            //System.out.println("ENVIANDO A JUGADOR " + (i));
        }
    	/*if(this.terminado){
        	for(TCPServerThread50 hilo_client: sendclis) {
        		hilo_client.stopClient();
        	}
    	}*/
    }
    

    
    public void run(){
        running = true;
        try{
            System.out.println("TCP Server"+"S : Connecting...");
            serverSocket = new ServerSocket(SERVERPORT);
            
            while(running){
           
            	Socket client = serverSocket.accept();
                System.out.println("TCP Server"+"S: Receiving...");
                nrcli++;
                System.out.println("Engendrado " + nrcli);
                sendclis[nrcli] = new TCPServerThread50(client,this,nrcli,sendclis);
                Thread t = new Thread(sendclis[nrcli]);
                t.start();
                System.out.println("Nuevo conectado:"+ nrcli+" jugadores conectados");
               
            }
            
        }catch( Exception e){
            System.out.println("Error"+e.getMessage());
        }finally{

        }
    }
    public  TCPServerThread50[] getClients(){
        return sendclis;
    } 

    public interface OnMessageReceived {
        public void messageReceived(String message);
    }
}
