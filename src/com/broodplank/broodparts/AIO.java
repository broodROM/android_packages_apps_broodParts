package com.broodplank.broodparts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;




public class AIO extends mainActivity {


    private static final String TAG = "CPUSettings";

		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	       
	        
	        AIONOW();
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
	
public void AIONOW() {
	
	 {
	    	 
		 
		 AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
		    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

		    View layout = inflater.inflate(R.layout.custom_fullimage_dialog,
		            (ViewGroup) findViewById(R.id.layout_root));
		    ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
		   // image.setImageDrawable(android.R.drawable.consuela);
		    imageDialog.setView(layout);
		    imageDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

		        public void onClick(DialogInterface dialog, int which) {
		            dialog.dismiss();
		        }

		    });


		    imageDialog.create();
		    imageDialog.show();    

	    		   CommandCapture command = new CommandCapture(0,  "rm -Rf /cache/*", "rm -Rf /data/dalvik-cache"); {
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
	    		 		
	    				   CommandCapture command1 = new CommandCapture(0, "rm -f /mnt/sdcard/vacuumlog", "cd /system/etc/broodrom", "sh vacuum > /mnt/sdcard/vacuumlog"); {
	   	    		 		try {
	   	    		 	
	   	    		 			RootTools.getShell(true).add(command1).waitForFinish();
	   	    		 			
	   	    		 		
	   	    		 			
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
	    	
	   	    		 	CommandCapture command11 = new CommandCapture(0, "reboot"); {
	   	    		 		try {
	   	    		 	
	   	    		 			RootTools.getShell(true).add(command11).waitForFinish();
	   	    		 			
	   	    		 		
	   	    		 			
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


 		
     }
	 

	 
}}}}}
 	 
    	
	


