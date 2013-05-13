package com.broodplank.broodparts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import com.stericson.RootTools.CommandCapture;
import com.stericson.RootTools.RootTools;



public class CLEARRAMCACHE extends mainActivity {

	public static final String GRPMEM = "/proc/meminfo";
	public static final String GETMEM = Environment.getExternalStorageDirectory().getPath()+"/memfree";
	File file=new File(Environment.getExternalStorageDirectory(), "memfree");
    private static final String TAG = "broodParts Lite";

	
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(null);
	        	        
	        
	        CLEARRAM();

		}
		
		
	
public void CLEARRAM() {
	

	
	 CommandCapture command = new CommandCapture(0, "busybox cat /proc/meminfo | grep MemFree: > "+GETMEM); {
 		try {
// 	
 			RootTools.getShell(true).add(command).waitForFinish();
// 		
//
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
	 

	 MemoryInfo mi = new MemoryInfo();
	 ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	 activityManager.getMemoryInfo(mi);
	 long availableMegs = mi.availMem / 1024 / 1024;
	 
     String availableMem = readOneLine(GETMEM).replace("MemFree:", "").replace(" ", "").replace("kB", " kB");

	 
     Toast.makeText(getApplicationContext(), 
		     "RAM Info (before dropping caches)\n\nRAM Cache Size:\n"+ availableMem+"\n\nMB Ram Free:\n"+availableMegs+" MB", Toast.LENGTH_LONG).show();     
	

 
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
    		
//        
   	 CommandCapture command2 = new CommandCapture(0, "busybox cat /proc/meminfo | grep MemFree: > "+GETMEM); {
  		try {
 // 	
  			RootTools.getShell(true).add(command2).waitForFinish();
//  		
 //
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
 	 
 	 
    
   	         		 
   	 
   	 MemoryInfo mi1 = new MemoryInfo();
	 ActivityManager activityManager1 = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
	 activityManager1.getMemoryInfo(mi1);
	 long availableMegs1 = mi1.availMem / 1024 / 1024;
	 
     String availableMem1 = readOneLine(GETMEM).replace("MemFree:", "").replace(" ", "").replace("kB", " kB");

	 
     Toast.makeText(getApplicationContext(), 
		     "RAM Info (after dropping caches)\n\nRAM Cache Size:\n"+ availableMem1+"\n\nMB Ram Free:\n"+availableMegs1+" MB", Toast.LENGTH_LONG).show();     
 
   	 
 	 
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
    		 
     }

