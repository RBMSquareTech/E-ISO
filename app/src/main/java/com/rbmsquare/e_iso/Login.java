package com.rbmsquare.e_iso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText etmobile, etpassword;
    String mobile, password;
    TextView btn_login;
    Button btn_AsDriver;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etmobile = findViewById(R.id.mobile);
        etpassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_AsDriver = findViewById(R.id.btn_driver_login);


        btn_login.setOnClickListener(this);
        btn_AsDriver.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                if (validate()) {
                    login();
                } break;

            case R.id.btn_driver_login:
                finish();
                startActivity(new Intent(Login.this, Signup.class));
                break;

        }

    }

    private boolean validate() {
        boolean temp = true;

        mobile = etmobile.getText().toString();
        password = etpassword.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(mContext, "mobile is empty", Toast.LENGTH_SHORT).show();
            temp = false;

        } else if (TextUtils.isEmpty(password)) {
            Toast toast = Toast.makeText(mContext, "password is empty", Toast.LENGTH_SHORT);
            toast.show();
            temp = false;
        }
        return temp;
    }

    private void login() {
        String URL = "http://rbmsquare.com/iso/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast toast = Toast.makeText(mContext, "Signed in successfully", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(new Intent(Login.this, dashboard.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "logical error", Toast.LENGTH_SHORT).show();

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile", mobile );
                params.put("password", password);

                return params;
            }
        };

        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);
    }
}
