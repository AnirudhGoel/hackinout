package com.example.sachin.docdoc.Networking;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sachin.docdoc.LoginActivity;
import com.example.sachin.docdoc.SignupActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sachin on 13/8/16.
 */
public class DownloadWebpageTask extends AsyncTask<String,Void,String> {
    public LoginActivity activity;
    public SignupActivity signupActivity;
    public  DownloadWebpageTask(LoginActivity act)
    {

        activity=act;
    }
    public  DownloadWebpageTask(SignupActivity acti)
    {
        signupActivity=acti;
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            return downloadUrl(strings[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Log.e("error",s);
        boolean authentication=false;
        if(s.charAt(0)=='s' || s.charAt(1)=='O')
                    {
                        authentication=true;
                    }
                    else if(s.charAt(0)=='u' || s.charAt(1)=='A')
                    {
                        authentication=false;
                    }
        if(authentication)
        {
            if(activity!=null)
                activity.onLoginSuccess();
            else
                signupActivity.onLoginSuccess();
        }

         else
        {
            if (activity!=null)
                activity.onLoginFailed();
            else
                signupActivity.onLoginFailed();


        }
        super.onPostExecute(s);
    }
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("response", "The response is: " + response);
        is =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
