package com.broodplank.broodparts;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;




public class HARDRESET extends mainActivity {


		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	       
	        
	        HARDRESETNOW();
		}
		
		
public void CONSUELA() {
	
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
	
}
		
	
public void HARDRESETNOW() {
	
	
	   final AlertDialog.Builder builder=new AlertDialog.Builder(this);
	      builder.setTitle("Hard Reset");
	      builder.setIcon(android.R.drawable.ic_dialog_alert);
	      builder.setMessage("Press OK to completely wipe all user data.\nOr press Cancel to exit");
	     builder.setPositiveButton("OK", new OnClickListener() {


	    	   @Override
	    	   public void onClick(DialogInterface dialog, int which) {
	    		   
	    	
	   		 CONSUELA();
	    		   

	    		   CommandCapture command = new CommandCapture(0, "rm -Rf /data", "rm -Rf /cache", "reboot"); {
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
	    	
			 	    	
	    		
	    		    };
	    			    	 
	    			    	
	    		
	    		   }});
	     

	     builder.setNegativeButton("Cancel", new OnClickListener() {

	    	   @Override
	    	   public void onClick(DialogInterface dialog, int which) {
	    		   
	    		 
	    	    // TODO Auto-generated method stub

	    	   }
	    	  });

	    builder.show();

 		
     }
	 

	 
}
 	 
    	
	


