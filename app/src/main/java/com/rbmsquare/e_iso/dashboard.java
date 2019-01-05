package com.rbmsquare.e_iso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rbmsquare.e_iso.Adapter.OrderAdapter;
import com.rbmsquare.e_iso.Model.OrderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    LinearLayoutManager linearLayoutManager;
    Context context = this;
    List<OrderModel> orderModels;
    ProgressBar progressBar;
    boolean is_Scrolling = false;
    int currentItem, scrolledItem, totalItem;
//    ArrayList<OrderModel> orderModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getOrder();
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progressBar);
        orderModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        linearLayoutManager = new LinearLayoutManager(this);



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    is_Scrolling = true;

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                currentItem = linearLayoutManager.getChildCount();
                scrolledItem = linearLayoutManager.findFirstVisibleItemPosition();
                totalItem = linearLayoutManager.getItemCount();

                Toast.makeText(dashboard.this, "total item = "+totalItem, Toast.LENGTH_SHORT).show();
                Toast.makeText(dashboard.this, "Scrolled item = "+scrolledItem, Toast.LENGTH_SHORT).show();
                Toast.makeText(dashboard.this, "current item = "+currentItem, Toast.LENGTH_SHORT).show();

                if (is_Scrolling && (currentItem+scrolledItem==totalItem)){
                    Toast.makeText(dashboard.this, "make order", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void getOrder(){

        String url = "http://rbmsquare.com/iso/getOrder.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt("status")==1){

                        JSONArray jsonArray = jsonObject.getJSONArray("result");
                        for (int i = 0; i< jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);


                            String name = object.optString("NAME");
                            String date = object.optString("DATE");
                            String pending = object.optString("IS_APPROVED");
                            String delete = object.getString("STATUS");

                            orderModels.add(new OrderModel(name, date, pending, delete));
                        }

                        orderAdapter = new OrderAdapter(orderModels, context);
                        recyclerView.setAdapter(orderAdapter);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


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
                param.put("ID", "1");

                return param;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
