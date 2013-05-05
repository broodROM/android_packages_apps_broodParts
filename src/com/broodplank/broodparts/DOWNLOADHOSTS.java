package com.broodplank.broodparts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;

public class DOWNLOADHOSTS extends mainActivity {


    private static final String TAG = "CPUSettings";

		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

		  	 
	       
	        
	        DOWNHOSTS();
	        
	    	

		}
		
		

public static String readOneLine(String fname) {
    BufferedReader br;
    String line = null;

    try {
        br = new BufferedReader(new FileReader(fname), 512);
        try {
            line = br.readLine();
        } finally {
            br.close();
        }
    } catch (Exception e) {
        Log.e(TAG, "IO Exception when reading /sys/ file", e);
    }
    return line;
}
	
public void DOWNHOSTS() {

	 Toast.makeText(getApplicationContext(), 
	    		 "Downloading and applying hosts file... Please wait", Toast.LENGTH_SHORT).show();
	 	 
	   CommandCapture command = new CommandCapture(0, "busybox wget http://winhelp2002.mvps.org/hosts.txt -O /system/etc/hosts", "cd /system/etc", "chmod 644 hosts");
	 		try {
	 	
	 			RootTools.getShell(true).add(command).waitForFinish();
	 			
	 		
	 			
	 		} catch (InterruptedException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		} catch (IOException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		} catch (TimeoutException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}

	 		 Toast.makeText(getApplicationContext(), 
	  	    		 "Done!", Toast.LENGTH_SHORT).show();

	    	{
	    	 
			 	    	
			{
				
			
			 	    		
   }}};}

 
    	
	


