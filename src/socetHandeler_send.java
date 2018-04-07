import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


public class socetHandeler_send extends Thread {

	String clientSentence; 
    String capitalizedSentence; 
    Socket incoming;
    int place=0;
    boolean flag = false ; 
    ArrayList<arrays>  arr_array;
    int team = 0 ;
    boolean flag2 = true;
    
    socetHandeler_send(Socket incoming , ArrayList<arrays>  arr_array )
    {
   
    	this.incoming = incoming;
    	this.arr_array = arr_array ; 
   
    }
    
    
    public void run(){
	try
	{
   
       DataOutputStream  outToClient = 
         new DataOutputStream(incoming.getOutputStream());
     while(true)
     {
		clientSentence="";
		
         get();
         
         if(flag2 == false) {
      	   clientSentence += '\n'; //MUST BE \N !!!!!!!!!!!!
      	   outToClient.writeBytes(clientSentence );// sending to the client clientSentence string
      	   flag2 = true;
         }
         
        if(clientSentence.toLowerCase().equals("bye")) {
			try {
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	break;
        }
         
        	if(flag)   {
        	   clientSentence += '\n'; //MUST BE \N !!!!!!!!!!!!
        	   outToClient.writeBytes(clientSentence );// sending to the client clientSentence string
        	   flag = false;
        	}
        	  
        	   
	}
       
	}
	catch(IOException e)
	{
		
	}
	
    }

    
    public  void get()// enter in the clientSentence all the words from all the users 
    {				// then we will send it to the client 

    	
    	try{
    	 for(int i=place ; i<arr_array.size(); i++,place++){
    		 
    		 
          if( arr_array.get(i).getNote().equals("GtYn5Xxar1Et") && arr_array.get(i).getIncoming() != incoming)
          {
        	  clientSentence += arr_array.get(i).getNickname() + ": is now disconnected " + " ";
        	  flag2 = false;
        	  
          }
    		 
    		 		 
      	 if( arr_array.get(i).getNote().equals("1ly2") && arr_array.get(i).getIncoming() == incoming)
      	 {
      		 team = arr_array.get(i).getTeam();
      	
      	 }

    		 if(arr_array.get(i).getIncoming() != incoming && arr_array.get(i).getTeam() == team && !arr_array.get(i).getNote().equals("1ly2") && flag2)// we don't want that the client will get his
      	   {									// own words
      		   
      		   clientSentence+=arr_array.get(i).getNickname() + ": " +" "+arr_array.get(i).getNote()+" ";
      		   
      		   
      		   flag=true;// the vault is now full
      	   }

    	 }
    	}catch ( Exception e)
    	{
    		
    	}
    }

}
	
	
