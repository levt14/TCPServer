import java.io.*; 
import java.net.*; 
import java.util.ArrayList;

class TCPServer { 
    
	private static ArrayList<arrays> arr_array = new ArrayList<arrays>();  // the key for the vault
	
	
	public static void main(String argv[]) throws Exception 
    		{ 
	 
	  ServerSocket s = null;

	 
		try {
		    s = new ServerSocket(10000);
		    System.out.println("The server started to run.");
		
		} catch(IOException e) {
		    System.out.println(e);
		    System.exit(1);
		}

		while (true) {
		    Socket incoming = null;
		    
		    try {
			incoming = s.accept();
			
		    } catch(IOException e) {
			System.out.println(e);
			continue;
		    }

		    System.out.println(incoming + " is now connected");
		       		    
		    new socketHandler(incoming , arr_array ).start();// the first thread for receive from clients
		    new socetHandeler_send(incoming ,arr_array).start();// the second thread for sending to 
	        											//the clients
		  
		}
    } 
} 
