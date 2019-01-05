package com.rbmsquare.e_iso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText etmobile, etpassword, etname, etemail;
    String mobile, password, name, email;
    TextView btn_login;
    Button btn_AsDriver;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etname = findViewById(R.id.name);
        etemail = findViewById(R.id.email);
        etmobile = findViewById(R.id.mobile);
        etpassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_AsDriver = findViewById(R.id.btn_driver_login);

        btn_login.setOnClickListener(this);
        btn_AsDriver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_login:
                if (validate()){
                    signup();
                }break;

            case R.id.btn_driver_login:
                startActivity(new Intent(Signup.this, Login.class));
                finish(); break;
        }
    }

    private boolean validate(){
        boolean temp = true;
        name=etname.getText().toString();
        email=etemail.getText().toString();
        mobile=etmobile.getText().toString();
        password=etpassword.getText().toString();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email)) {
            Toast.makeText(mContext, "Please fill the empty fields", Toast.LENGTH_SHORT).show();
            temp = false;
        }

        return temp;
    }

    private void signup(){
        String url = "http://rbmsquare.com/iso/signup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(mContext, "hey signup successfullly", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Signup.this, dashboard.class));
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<String, String>();
                param.put("name", name);
                param.put("email", email);
                param.put("mobile", mobile);
                param.put("password", password);

                return param;
            }
        };

        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }
}
