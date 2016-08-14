package com.example.sachin.docdoc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sachin.docdoc.Location.MyApp;
import com.example.sachin.docdoc.Networking.DownloadWebpageTask;
import com.example.sachin.docdoc.Networking.Main2Activity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    boolean authentication=true;
    ProgressDialog progressDialog;


    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         _loginButton=(Button)  findViewById(R.id.btn_login);
        _emailText= (EditText) findViewById(R.id.input_phone);
        _signupLink=(TextView) findViewById(R.id.link_signup);
         _passwordText=(EditText) findViewById(R.id.input_password);

        Log.e("flow","in login create");
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        RadioButton rb1=(RadioButton) findViewById(R.id.radio_doctor);
        RadioButton rb2=(RadioButton) findViewById(R.id.radio_patient);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.type="doctor";

            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.type="patient";
            }
        });
    }
    public void onLoginFailed() {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }
    public void onLoginSuccess() {
        progressDialog.dismiss();
        _loginButton.setEnabled(true);
        finish();
        Intent intent;
        ///start main activity
        if(MyApp.type.equals("doctor"))
         intent=new Intent(LoginActivity.this,Main2Activity.class);
        else
         intent=new Intent(LoginActivity.this,Patient.class);
        startActivity(intent);
    }


    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

         progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here and set authenticaion boolean accordingly and then call run()

//
        String Url="http://anirudhgoel.xyz/inout/login.php?mobile="+email+"&password="+password;
//            new Thread(new Runnable() {
//                public void run() {
//                    String Url="http://anirudhgoel.xyz/inout/login.php?mobile="+email+"&password="+password;
//                    String str = loadStringFromNetwork(Url);
//                    if(str.equals("successful"))
//                    {
//                        authentication=true;
//                    }
//                    else if(str.equals("unsuccessful"))
//                    {
//                        authentication=false;
//                    }
//                }
//            }).start();
//        progressDialog.dismiss();

        loadStringFromNetwork(Url);




//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        if(authentication==true)
//                        onLoginSuccess();
//                        else
//                        onLoginFailed();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);


    }
    public void loadStringFromNetwork(String url)
        {
            String resultstatus="unsuccesful" ;
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new DownloadWebpageTask(this).execute(url);
            } else
            {
                Toast.makeText(LoginActivity.this,"No Network Connection",Toast.LENGTH_LONG);
            }


        }


    public boolean validate() {
        boolean valid = true;

        String phone_no = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (phone_no.isEmpty() || !Patterns.PHONE.matcher(phone_no).matches()) {
            _emailText.setError("enter a valid phone No.");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
