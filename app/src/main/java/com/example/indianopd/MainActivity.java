package com.example.indianopd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    //Variable Declaration
    Button sendbtn,
            recvieBtn;
    LinearLayout otpNoLayout,
            mobileNoLayout;
    TextView reSendOtp,
            changeMobileNo;
    EditText enterOtp,
            enterMobileNo;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Animation Declare
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.from_left);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.from_right);

        //Variable Initialize
        //1 - Layout Functionality
        mobileNoLayout = findViewById(R.id.phonecell);
        mobileNoLayout.setVisibility(View.VISIBLE);
        otpNoLayout = findViewById(R.id.otpenter);
        otpNoLayout.setVisibility(View.INVISIBLE);

        //2 - Edit Field Functionality
        enterMobileNo = findViewById(R.id.SendOtp);
        enterOtp = findViewById(R.id.reciveOtp);

        //3 - Button Functionality
        sendbtn = findViewById(R.id.send);
        sendbtn.setOnClickListener(v ->
        {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked)
            {
                if (v.getId() == sendbtn.getId())
                {
                    otpNoLayout.startAnimation(anim);
                    otpNoLayout.setVisibility(View.VISIBLE);
                    mobileNoLayout.setVisibility(View.GONE);

                    Toast.makeText(MainActivity.this, "OTP SEND", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mobileNoLayout.setVisibility(View.VISIBLE);
                    otpNoLayout.setVisibility(View.GONE);
                }
            }
            //finish();
        });

        recvieBtn = findViewById(R.id.recive);
        recvieBtn.setOnClickListener(v ->
        {
            //Redirect to Another Page
            if(enterOtp != null)
            {
                Toast.makeText(MainActivity.this, "Success: Home Page", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
            }
        });

        //5 - Text Field Functionality
        reSendOtp = findViewById(R.id.resendotp_text);
        reSendOtp.setOnClickListener(v -> Toast.makeText(MainActivity.this, "OTP Resent", Toast.LENGTH_SHORT).show());

        changeMobileNo = findViewById(R.id.chgmobile_text);
        changeMobileNo.setOnClickListener(v ->
        {
            mobileNoLayout.startAnimation(anim1);
            mobileNoLayout.setVisibility(View.VISIBLE);
            otpNoLayout.setVisibility(View.GONE);
        });

    }

    //Checking If Fields Are Dummy Validate or not if not checking.
    private boolean CheckAllFields()
    {
        if (enterMobileNo.length() == 0)
        {
            enterMobileNo.setError("This field is required");
            return false;
        }
        if(enterMobileNo.length() != 10)
        {
            enterMobileNo.setError("Wrong No and Length must be 10");
            return false;
        }
        return true;
    }

    //Menu Adding Functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}