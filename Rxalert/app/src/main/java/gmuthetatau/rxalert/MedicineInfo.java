package gmuthetatau.rxalert;


import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zachary on 2/19/17.
 *
 */

/**
 * Allows you to interface with the json containing the medicine info
 * In order to read it, call read.
 * In order to change values, you must read it, then change the value, then write it again
 * The static method changeValue may help with that
 *
 * Everything is done with a HashMap<String, String>
 */
public class MedicineInfo
{
    private String filename;
    private Context c;


    public MedicineInfo(Context c, String filename)
    {
        this.c = c;
        this.filename = filename;
    }
    public void write(HashMap<String, String> map)
    {
        FileOutputStream outputStream;
        try {
            outputStream = c.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write((new JSONObject(map)).toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String> read() throws JSONException
    {
        HashMap<String, String> map = new HashMap<>();

        String jsonstring = readFromFile();
        JSONObject jObject = new JSONObject(jsonstring);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);
        }
        return map;
    }
    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = c.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("JSON Reading", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("JSON Reading", "Can not read file: " + e.toString());
        }

        return ret;
    }
    public static HashMap<String, String> changeValue(HashMap<String, String> map, String key, String newval)
    {
        if (map.containsKey(key))
        {
            map.put(key, newval);
            return map;
        }
        else
            return map;

    }
}
