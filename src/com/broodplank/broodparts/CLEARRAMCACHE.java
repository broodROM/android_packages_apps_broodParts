package com.broodplank.broodparts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;




public class CLEARRAMCACHE extends mainActivity {

	public static final String GRPMEM = Environment.getExternalStorageDirectory().getPath()+"/memfree";
    private static final String TAG = "CPUSettings";

	

		
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
//	        setContentView(R.layout.clearram);
	        
	        
	        
	        CLEARRAM();

	
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
	
public void CLEARRAM() {
	
	
	 CommandCapture command = new CommandCapture(0, "rm /sdcard/memfree","busybox free | grep Mem: > /sdcard/memfree"); {
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
		       readOneLine(GRPMEM).replaceFirst("Mem:        ", "Total/Used/Free/Shared/Buffers:\n").replace("       ", " / ").replace("        ", " / ").replace("          ", " / ").replace("            ", " / "), Toast.LENGTH_LONG).show();
	 

 
        CommandCapture command1 = new CommandCapture(0, "echo 3 > /proc/sys/vm/drop_caches", "busybox sleep 1", "echo 0 > /proc/sys/vm/drop_caches"); {
    		try {
    	
    			RootTools.getShell(true).add(command1).waitForFinish();
    		

    		} catch (InterruptedException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		} catch (IOException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		} catch (TimeoutException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
        }
    		
        
   	 CommandCapture command2 = new CommandCapture(0, "rm /sdcard/memfree","busybox free | grep Mem:  > /sdcard/memfree"); {
  		try {
  	
  			RootTools.getShell(true).add(command2).waitForFinish();
  		

  		} catch (InterruptedException e3) {
  			// TODO Auto-generated catch block
  			e3.printStackTrace();
  		} catch (IOException e3) {
  			// TODO Auto-generated catch block
  			e3.printStackTrace();
  		} catch (TimeoutException e3) {
  			// TODO Auto-generated catch block
  			e3.printStackTrace();
  		}
      }
 	 
      Toast.makeText(getApplicationContext(), 
    		  readOneLine(GRPMEM).replaceFirst("Mem:", "Total/Used/Free/Shared/Buffers:\n").replace("       ", " / ").replace("        ", " / ").replace("          ", " / ").replace("            ", " / "), Toast.LENGTH_LONG).show();
 	 
    	
	}
	

    		 
     }

