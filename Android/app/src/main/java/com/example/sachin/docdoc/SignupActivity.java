package com.example.sachin.docdoc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sachin.docdoc.Networking.DownloadWebpageTask;
import com.example.sachin.docdoc.Networking.OtpCheck;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    Button click;
    EditText otp;
    EditText name;
    EditText phno;
    EditText password;
    String namei;
    String phone;
    String otpvalue;
    String passwordi;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         click=(Button) findViewById(R.id.btn_signup);

        name=(EditText) findViewById(R.id.name);
        phno=(EditText) findViewById(R.id.phone);
        password=(EditText) findViewById(R.id.inputpassword);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();

            }
        });




    }

    private void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onLoginFailed();
            return;
        }


        click.setEnabled(false);

        progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

         phone = phno.getText().toString();
         passwordi = password.getText().toString();
         namei=name.getText().toString();

        // TODO: Implement your own authentication logic here and set authenticaion boolean accordingly and then call run()

//
        String Url="http://anirudhgoel.xyz/inout/signup.php?name="+namei+"&mobile="+phone+"&password="+passwordi;

        loadStringFromNetwork(Url);


    }
    public void onLoginSuccess()
    {
        progressDialog.dismiss();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter OTP");
        builder.setMessage("Please Enter OTP for Verification");
        final EditText et=new EditText(SignupActivity.this);
        builder.setView(et);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 otpvalue=et.getText().toString();
                new OtpCheck(SignupActivity.this).execute("http://anirudhgoel.xyz/inout/otp_check.php?name="+namei+"&mobile="+phone+"&password="+passwordi+"&otp="+otpvalue);
            }
        });
        builder.show();
        //show dialog box
    }

    public void onLoginFailed() {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), "Wrong OTP", Toast.LENGTH_LONG).show();

        click.setEnabled(true);
    }

    public void loadStringFromNetwork(String url)
    {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask(this).execute(url);
        } else
        {
            Toast.makeText(SignupActivity.this,"No Network Connection",Toast.LENGTH_LONG);
        }


    }

    public boolean validate() {
        boolean valid = true;

        String phone_no = phno.getText().toString();
        String passwordi= password.getText().toString();


        if (phone_no.isEmpty() || !Patterns.PHONE.matcher(phone_no).matches()) {
            phno.setError("enter a valid phone No.");
            valid = false;
        } else {
            phno.setError(null);
        }

        if (passwordi.isEmpty() || passwordi.length() < 4 || passwordi.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
