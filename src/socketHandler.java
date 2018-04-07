import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class socketHandler extends Thread {
	
	Socket incoming;
	ArrayList<arrays>  arr_array;
	private String clientSentence = "";
	String nickname;
	int team = 1;
	public static ArrayList<String> nicknames = new ArrayList<String>();
	
	socketHandler(Socket _in , ArrayList<arrays>  arr_array )
	{
		this.incoming=_in;
		this.arr_array = arr_array;
		
		
	}

	
	public void run()
	{
		 
	   boolean flag3;
	   boolean flag4;

		try
		{
	    
           BufferedReader inFromClient = 
              new BufferedReader(new
              InputStreamReader(incoming.getInputStream())); 
           
           DataOutputStream  outToClient = 
        	         new DataOutputStream(incoming.getOutputStream());
           
           do {
        	flag3 = true;
        	flag4 = true;
        	   
   			nickname = inFromClient.readLine();
   		
   			
   		//	System.out.println("data from client: " + nickname);
   			//System.out.println(flag3 + " " + flag4);

   			
            if(nicknames.size() == 0) {
      		   //System.out.println("nick size() == 0 accepted");
         	   nicknames.add(nickname);
               in("1ly2",incoming , arr_array ,team, nickname);
         	   outToClient.writeBytes("1" + '\n');
         	   flag3 = false;
            }
            else {
         	   for(String n: nicknames) {
         		   if(nickname.toLowerCase().equals(n.toLowerCase())) {
         	           outToClient.writeBytes("0" + '\n');
         	           flag4 = false;
         		   } 
         	   }
         	   if(flag4) {
         		  // System.out.println("nick accepted");
             	   nicknames.add(nickname);
                   in("1ly2",incoming , arr_array ,team, nickname);
             	   outToClient.writeBytes("1" + '\n');
             	   flag3 = false;
         	   }
     
             }

        	   
           }
           while(flag3);
           
           
           
           
           
		while(true) {
			
			
	           clientSentence = inFromClient.readLine(); // Receive from client
	           
	           System.out.println(incoming + " (" + nickname + "): " + clientSentence);
	           
	          
	           if(clientSentence.toLowerCase().equals("bye"))// if the client send bye 
	           {	
	        	   // we close the connection
	        	   System.out.println(incoming + " (" + nickname + ") is now disconnected");
	        	   	
	        	   
	        	   int i = 0;
	        	   
	        	   for(String n: nicknames) {        		  	        		   
	        		   if(n.equals(nickname)) {
	        			 nicknames.remove(i);
	        			 break;
	        		   }
	        		   i++;
	        	   }
	        	   
	        	   


	        	   
	        	   incoming.close();

	        	   
	        	   break;
	        	   /*
	        	   
	        	   in("GtYn5Xxar1Et" , incoming , arr_array , team, nickname);
	        	  
	       		try {
	    			Thread.sleep(5000);
	    		} catch (InterruptedException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}

	        	   */
	           }
	           
	          in(clientSentence , incoming , arr_array , team, nickname);// save the client Sentence
	           
	           clientSentence="";
	          
	          
	        }

		
		}
		catch(IOException e)
		{
			
		}

	}

	public static synchronized  void in(String clientSentence , Socket incoming , ArrayList<arrays>  arr_array , int team, String nickname ){
		
		
		arr_array.add(new arrays(clientSentence , incoming , team, nickname)); // all the users can see it because it is static
       
	}
	
}
