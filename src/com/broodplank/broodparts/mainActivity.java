package com.broodplank.broodparts;


import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.broodplank.broodparts.R;
import com.stericson.RootTools.*;

@SuppressLint("SdCardPath")
public class mainActivity extends PreferenceActivity implements
		Preference.OnPreferenceChangeListener{
	
	
	public static final int MODE_WORLD_WRITEABLE = 0x00000002;

    public static final String GOV_PREF = "pref_cpu_gov";
    public static final String GOVERNORS_LIST_FILE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors";
    public static final String GOVERNOR = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor";
    public static final String MIN_FREQ_PREF = "pref_freq_min";
    public static final String MAX_FREQ_PREF = "pref_freq_max";
    public static final String IO_SCHEDULER_PREF = "pref_io_sched";
    public static final String READAHEAD_KB_PREF = "pref_read_ahead";  
    public static final String ADBNOTIFY_PREF = "pref_adb_notify";
    public static final String GPSLANG_PREF = "pref_gps_lang";
    public static final String CLEAR_RAM_CACHE = "clear_ram_cache";
    public static final String GOV_TWEAKS_PREF = "pref_gov_tweak";
    public static final String IO_TWEAKS_PREF = "pref_io_tweak";
    public static final String NET_TWEAKS_PREF = "pref_net_tweak";
    public static final String VM_TWEAKS_PREF = "pref_vm_tweak";
    public static final String DENSITY_PREF = "pref_density";
    public static final String HOSTS_DOWNLOAD_PREF = "pref_hosts_download";
    public static final String USED_CPU_FREQUENCIES = "used_cpu_frequencies";
    public static final String FREQ_LIST_FILE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies";
    public static final String FREQ_MAX_FILE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq";
    public static final String FREQ_MIN_FILE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq";
    public static final String IO_SCHEDULER_FILE = "/sys/block/mmcblk0/queue/scheduler";
    public static final String READAHEAD_FILE = "/sys/devices/virtual/bdi/179:0/read_ahead_kb";
    public static final String ADBNOTIFY_FILE = "/mnt/sdcard/adbnotify";
    public static final String DENSITY_FILE = "/mnt/sdcard/lcddensity";
    public static final String LANG_FILE = "/data/property/persist.sys.language";
    public static final String HOSTS_FILE = "/system/etc/hosts";
    public static final String GOVTWEAK_FILE = "/mnt/sdcard/govtweak";
    public static final String IOTWEAK_FILE = "/mnt/sdcard/iotweak";
    public static final String NETTWEAK_FILE = "/mnt/sdcard/nettweak";
    public static final String VMTWEAK_FILE = "/mnt/sdcard/vmtweak";



    private static final String TAG = "broodParts";

    private String mGovernorFormat;
    private String mMinFrequencyFormat;
    private String mMaxFrequencyFormat;
    private String mIOSchedulerFormat;
    private String mReadAheadFormat;

    
    private ListPreference mGovernorPref;
    private ListPreference mMinFrequencyPref;
    private ListPreference mMaxFrequencyPref;
    private ListPreference mIOSchedulerPref;
    private ListPreference mReadAheadPref;
    private ListPreference mADBNotifyPref;
    private ListPreference mGovTweakPref;
    private ListPreference mIOTweakPref;
    private ListPreference mNetTweakPref;
    private ListPreference mVMTweakPref;

    
   
 

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);

        mGovernorFormat = getString(R.string.cpu_governors_summary);
        mMinFrequencyFormat = getString(R.string.cpu_min_freq_summary);
        mMaxFrequencyFormat = getString(R.string.cpu_max_freq_summary);
        mIOSchedulerFormat = getString(R.string.io_set_summary);
        mReadAheadFormat = getString(R.string.readahead_summary);

        
		RootTools.debugMode = true; //ON


        String[] availableGovernors = readOneLine(GOVERNORS_LIST_FILE).split(" ");
        String[] availableSchedulers = readOneLine(IO_SCHEDULER_FILE).replace("[", "").replace("]", "").split(" ");
        
        String[] availableFrequencies = new String[0];
        String availableFrequenciesLine = readOneLine(FREQ_LIST_FILE);
        if (availableFrequenciesLine != null)
             availableFrequencies = availableFrequenciesLine.split(" ");
        String[] frequencies;
        String temp;
        

        frequencies = new String[availableFrequencies.length];
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i] = toMHz(availableFrequencies[i]);
            
     
        }
        

 
        setTitle(R.string.cpu_title);
        addPreferencesFromResource(R.xml.broodparts);
        

        PreferenceScreen PrefScreen = getPreferenceScreen();
        
        temp = readOneLine(GOVERNOR);

        mGovernorPref = (ListPreference) PrefScreen.findPreference(GOV_PREF);
        mGovernorPref.setEntryValues(availableGovernors);
        mGovernorPref.setEntries(availableGovernors);
        mGovernorPref.setValue(temp);
        mGovernorPref.setSummary(String.format(mGovernorFormat, temp));
        mGovernorPref.setOnPreferenceChangeListener(this);

        /* Some systems might not use governors */
        if (temp == null) {
            PrefScreen.removePreference(mGovernorPref);
        }

        temp = readOneLine(FREQ_MIN_FILE);

        mMinFrequencyPref = (ListPreference) PrefScreen.findPreference(MIN_FREQ_PREF);
        mMinFrequencyPref.setEntryValues(availableFrequencies);
        mMinFrequencyPref.setEntries(frequencies);
        mMinFrequencyPref.setValue(temp);
        mMinFrequencyPref.setSummary(String.format(mMinFrequencyFormat, toMHz(temp)));
        mMinFrequencyPref.setOnPreferenceChangeListener(this);

        temp = readOneLine(FREQ_MAX_FILE);

        mMaxFrequencyPref = (ListPreference) PrefScreen.findPreference(MAX_FREQ_PREF);
        mMaxFrequencyPref.setEntryValues(availableFrequencies);
        mMaxFrequencyPref.setEntries(frequencies);
        mMaxFrequencyPref.setValue(temp);
        mMaxFrequencyPref.setSummary(String.format(mMaxFrequencyFormat, toMHz(temp)));
        mMaxFrequencyPref.setOnPreferenceChangeListener(this);
        
        
        temp = readOneLine(IO_SCHEDULER_FILE);
        

        mIOSchedulerPref = (ListPreference) PrefScreen.findPreference(IO_SCHEDULER_PREF);
        mIOSchedulerPref.setEntryValues(availableSchedulers);
        mIOSchedulerPref.setEntries(availableSchedulers);
        mIOSchedulerPref.setValue(temp);
        mIOSchedulerPref.setSummary(String.format(mIOSchedulerFormat, temp));
        mIOSchedulerPref.setOnPreferenceChangeListener(this);
        
        temp = readOneLine(READAHEAD_FILE);
        
        mReadAheadPref = (ListPreference) PrefScreen.findPreference(READAHEAD_KB_PREF);
        mReadAheadPref.setEntryValues(R.array.read_ahead_kb_size);
        mReadAheadPref.setEntries(R.array.read_ahead_kb_size);
        mReadAheadPref.setValue(temp);
        mReadAheadPref.setSummary(String.format(mReadAheadFormat, temp));
        mReadAheadPref.setOnPreferenceChangeListener(this); 
        
        temp = readOneLine(ADBNOTIFY_FILE);
        
        mADBNotifyPref = (ListPreference) PrefScreen.findPreference(ADBNOTIFY_PREF);
        mADBNotifyPref.setEntryValues(R.array.yesno);
        mADBNotifyPref.setEntries(R.array.yesno);
        mADBNotifyPref.setValue(temp);
        mADBNotifyPref.setOnPreferenceChangeListener(this); 
        
        temp = readOneLine(GOVTWEAK_FILE);
        
        mGovTweakPref = (ListPreference) PrefScreen.findPreference(GOV_TWEAKS_PREF);
        mGovTweakPref.setEntryValues(R.array.yesno);
        mGovTweakPref.setEntries(R.array.yesno);
        mGovTweakPref.setValue(temp);
        mGovTweakPref.setOnPreferenceChangeListener(this); 
        
        temp = readOneLine(IOTWEAK_FILE);
        
        mIOTweakPref = (ListPreference) PrefScreen.findPreference(IO_TWEAKS_PREF);
        mIOTweakPref.setEntryValues(R.array.yesno);
        mIOTweakPref.setEntries(R.array.yesno);
        mIOTweakPref.setValue(temp);
        mIOTweakPref.setOnPreferenceChangeListener(this); 
        
     
        temp = readOneLine(NETTWEAK_FILE);
        
        mNetTweakPref = (ListPreference) PrefScreen.findPreference(NET_TWEAKS_PREF);
        mNetTweakPref.setEntryValues(R.array.yesno);
        mNetTweakPref.setEntries(R.array.yesno);
        mNetTweakPref.setValue(temp);
        mNetTweakPref.setOnPreferenceChangeListener(this);
        
        temp = readOneLine(VMTWEAK_FILE);
        
        mVMTweakPref = (ListPreference) PrefScreen.findPreference(VM_TWEAKS_PREF);
        mVMTweakPref.setEntryValues(R.array.yesno);
        mVMTweakPref.setEntries(R.array.yesno);
        mVMTweakPref.setValue(temp);
        mVMTweakPref.setOnPreferenceChangeListener(this); 
        

        
    }
    

    @Override
    public void onResume() {
        String temp;

        super.onResume();

        temp = readOneLine(FREQ_MAX_FILE);
        mMaxFrequencyPref.setValue(temp);
        mMaxFrequencyPref.setSummary(String.format(mMaxFrequencyFormat, toMHz(temp)));

        temp = readOneLine(FREQ_MIN_FILE);
        mMinFrequencyPref.setValue(temp);
        mMinFrequencyPref.setSummary(String.format(mMinFrequencyFormat, toMHz(temp)));

        temp = readOneLine(GOVERNOR);
        mGovernorPref.setSummary(String.format(mGovernorFormat, temp));
        
        temp = readOneLine(IO_SCHEDULER_FILE);
        mIOSchedulerPref.setSummary(String.format(mIOSchedulerFormat, temp));
        
        int bropen, brclose;
        String currentIOScheduler;
        
            bropen = temp.indexOf("[");
            brclose = temp.lastIndexOf("]");
            if (bropen >= 0 && brclose >= 0) {
                currentIOScheduler = temp.substring(bropen + 1, brclose);
                mIOSchedulerPref.setSummary(String.format(mIOSchedulerFormat, currentIOScheduler));
            }
       
        
        temp = readOneLine(READAHEAD_FILE);
        mReadAheadPref.setSummary(String.format(mReadAheadFormat, temp));

        
        
    }
    


    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String fname = "";

        if (newValue != null) {
            if (preference == mGovernorPref) {
                fname = GOVERNOR;
            } else if (preference == mMinFrequencyPref) {
                fname = FREQ_MIN_FILE;
            } else if (preference == mMaxFrequencyPref) {
                fname = FREQ_MAX_FILE;
            } else if (preference == mIOSchedulerPref) {
                mIOSchedulerPref.setSummary(String.format(mIOSchedulerFormat, (String) newValue));
                fname = IO_SCHEDULER_FILE;
            } else if (preference == mReadAheadPref) {
                fname = READAHEAD_FILE;
            } else if (preference == mADBNotifyPref) {
            	fname = ADBNOTIFY_FILE;
            } else if (preference == mGovTweakPref) {
            	fname = GOVTWEAK_FILE;
            } else if (preference == mIOTweakPref) {
            	fname = IOTWEAK_FILE;	
            } else if (preference == mNetTweakPref) {
            	fname = NETTWEAK_FILE;	
            } else if (preference == mVMTweakPref) {
            	fname = VMTWEAK_FILE;	
            	

            }

            if (writeOneLine(fname, (String) newValue)) {
                if (preference == mGovernorPref) {
                    mGovernorPref.setSummary(String.format(mGovernorFormat, (String) newValue));
                } else if (preference == mMinFrequencyPref) {
                    mMinFrequencyPref.setSummary(String.format(mMinFrequencyFormat,
                            toMHz((String) newValue)));
                } else if (preference == mMaxFrequencyPref) {
                    mMaxFrequencyPref.setSummary(String.format(mMaxFrequencyFormat,
                            toMHz((String) newValue)));
                } else if (preference == mIOSchedulerPref) {
                    mIOSchedulerPref.setSummary(String.format(mIOSchedulerFormat, (String) newValue));
                } else if (preference == mReadAheadPref) {
                    mReadAheadPref.setSummary(String.format(mReadAheadFormat, (String) newValue)); 
            
                }
                
               return true;             
            } else {
                return false;
            }

        }
        return false;
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

    public static boolean writeOneLine(String fname, String value) {
    	

		try
		{
			RootTools.getShell(true);
		}
		catch (Exception e) {
	}
		
		CommandCapture command1 = new CommandCapture(0, "busybox mount -o remount rw /system", "busybox chmod 666 /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor", "busybox chmod 666 /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq", "busybox chmod 666 /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq", "busybox chmod 666 /sys/block/mmcblk0/queue/scheduler", "busybox chmod 666 /sys/devices/virtual/bdi/179:0/read_ahead_kb", "busybox chmod 666 /data/property/persist.sys.language");
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
		
    	
        try {
            FileWriter fw = new FileWriter(fname);
            try {
                fw.write(value);
            } finally {
                fw.close();
                CommandCapture command = new CommandCapture(0, "busybox cp -f "+fname+" /system/etc/broodrom/", "busybox chmod 0664 /system/etc/broodrom/*", "busybox chown root:system /system/etc/broodrom/*");
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
        } catch (IOException e) {
            String Error = "Error writing to " + fname + ". Exception: ";
            Log.e(TAG, Error, e);
            return false;
        }
		return true;
    }


	
    private String toMHz(String mhzString) {
        return new StringBuilder().append(Integer.valueOf(mhzString) / 1000).append(" MHz").toString();
    }

		


} 
    
    
