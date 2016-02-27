package com.wiredelta.sample.companydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.wiredelta.sample.companydetail.model.Company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by arun on 1/26/2016.
 */
public class SplashActivity extends AppCompatActivity {
    Intent i;
    RelativeLayout progressLayout;
    Company company;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressLayout = (RelativeLayout) findViewById(R.id.relativelayout_progress);
        progressLayout.setVisibility(View.GONE);
        getDataFromJson(Constants.URL_);
    }

    public void getDataFromJson(String url) {
        progressLayout.setVisibility(View.VISIBLE);
        JsonArrayRequest compReq = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        extractJson(response);
                        hidePDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, Constants.TAG_CHECK_NET, Toast.LENGTH_LONG).show();
                VolleyLog.d("CHECK", "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        CustomVolleyRequestQueue.getInstance(this).addToRequestQueue(compReq);
    }

    public void extractJson(JSONArray response) {
        if (response != null) {
            ArrayList<HashMap<String, String>> compDetails = new ArrayList<>();
            HashMap<String, String> compList;
            try {
                for (int i = 0; i < response.length(); i++) {
                    compList = new HashMap<>();
                    company = new Company();
                    JSONObject comp = response.getJSONObject(i);

                    compList.put(Constants.TAG_COMP_ID, comp.getString(Constants.TAG_COMP_ID));
                    compList.put(Constants.TAG_COMP_NAME, comp.getString(Constants.TAG_COMP_NAME));
                    compList.put(Constants.TAG_COMP_OWNER, comp.getString(Constants.TAG_COMP_OWNER));
                    compList.put(Constants.TAG_COMP_STARTDATE, comp.getString(Constants.TAG_COMP_STARTDATE));
                    compList.put(Constants.TAG_COMP_DESC, comp.getString(Constants.TAG_COMP_DESC));
                    compList.put(Constants.TAG_COMP_DEPT, comp.getString(Constants.TAG_COMP_DEPT));
                    compDetails.add(compList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra(Constants.INTENT_COMPDETAILS, compDetails);
            startActivity(i);
            overridePendingTransition(R.transition.push_up_in, R.transition.push_up_out);
            finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressLayout.isShown()) {
            progressLayout.setVisibility(View.GONE);
        }
    }
}
