package com.example.sachin.docdoc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.sachin.docdoc.Networking.DownloadWebpageTask;
import com.example.sachin.docdoc.Networking.PatientNo;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class Patient extends AppCompatActivity {
     TickerView tickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
         tickerView = (TickerView) findViewById(R.id.tickerView);
        tickerView.setCharacterList(TickerUtils.getDefaultNumberList());
        tickerView.setAnimationDuration(500);
        tickerView.setAnimationInterpolator(new OvershootInterpolator());
        tickerView.setGravity(Gravity.START);
        Button but=(Button) findViewById(R.id.btn_log);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadnetwork();
            }
        });
        }
public void loadnetwork()
    {
        String url="http://anirudhgoel.xyz/inout/crowd.php";
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new PatientNo(Patient.this).execute(url);
        } else
        {
            Toast.makeText(Patient.this,"No Network Connection",Toast.LENGTH_LONG);
        }
    }

    public void count(String no)
    {
       // Log.e("value",no);

        String val=""+no.charAt(0);
       tickerView.setText(val);

    }

}
