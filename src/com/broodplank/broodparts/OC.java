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




public class OC extends mainActivity {

    private static final String TAG = "broodParts";

	
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.clearram);
	        
	        
	        
	        FLASHOC();

	
//	        setContentView(R.layout.activity_main);
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
	
public void FLASHOC() {
	
	
	 CommandCapture command = new CommandCapture(0, "dd if=/system/etc/broodrom/boot_oc.img of=/dev/block/mmcblk0p8"); {
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
     }
	 



 	 
      Toast.makeText(getApplicationContext(), 
    		  "broodKernel v3.3 OC has been flashed, please restart your phone", Toast.LENGTH_LONG).show();
 	 
    	
	}
	

    		 
     }

