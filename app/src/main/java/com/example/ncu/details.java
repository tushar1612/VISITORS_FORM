package com.example.ncu;


import android.content.Intent;
import android.opengl.EGL14;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class details extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        e1=findViewById(R.id.name1);
        e2=findViewById(R.id.email);
        e3=findViewById(R.id.phone);
        e4=findViewById(R.id.address);
        e5=findViewById(R.id.occupation);
        e6=findViewById(R.id.remarks);
        submit=findViewById(R.id.btn_submitDetails);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String nm=e1.getText().toString();
                final String mail=e2.getText().toString();
                final String phone=e3.getText().toString();
                final String address=e4.getText().toString();
                final String occu=e5.getText().toString();
                final String rmrk=e6.getText().toString();



                RequestQueue requestQueue= Volley.newRequestQueue(details.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://occurrent-propose.000webhostapp.com/register.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(details.this, "successfully submitted", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(details.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        super.getParams();

                        Map<String,String> params=new HashMap<>();
                        params.put("user_name", nm);
                        params.put("user_email", mail);
                        params.put("user_phone", phone);
                        params.put("user_address", address);
                        params.put("user_occupation", occu);
                        params.put("user_remarks",rmrk );
                        return params;
                    }
                };


                requestQueue.add(stringRequest);
                Intent intent1 = new Intent(getApplicationContext(),last.class);
                startActivity(intent1);


            }
        });

    }
}
