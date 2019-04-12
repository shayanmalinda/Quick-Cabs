package com.shayanmalinda.quickcabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    String username = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

    }

    public void profile(View v){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://idexserver.tk/b05/shayan/quickcabs/profile/list.php?username=\""+username+"\"";
        final List<HashMap<String, String>> list = new ArrayList<>();
        final HashMap<String, String> map = new HashMap();


        JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                map.put("email", obj.getString("email"));
                                map.put("phone", obj.getString("phone"));
                                list.add(map);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        printToast(map.get("email"),map.get("phone"),username);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();

                    }
                });


        queue.add(request1);




    }

    private void printToast(String email,String phone,String username) {

        Intent intent = new Intent(this,ProfileActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }

    public void hire(View v){
        Intent intent = new Intent(this,HireActivity.class);
        startActivity(intent);
    }

    public void SheduleACab(View v){
        Intent intent = new Intent(this,SheduleActivity.class);
        startActivity(intent);
    }
}
