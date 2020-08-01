package com.example.androidtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtask.API.ApiConfigurations;
import com.example.androidtask.Adapter.ListAdapter;
import com.example.androidtask.Databse.DBHandler;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvNoData;
    ProgressDialog progressDoalog;
    DBHandler db;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<ReportResponse> dataArrayList;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvList);
        tvNoData = (TextView) findViewById(R.id.tvNoData);
        db = new DBHandler(this);
//        layoutManager=new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        loadList();
    }

    private void loadList() {
        try {
            ViewReportApi mOrdersApi = ApiConfigurations.getInstance().getApiBuilder().create(ViewReportApi.class);

            Call<ViewReportApi.viewReport> call = mOrdersApi.viewReport();

            call.enqueue(new Callback<ViewReportApi.viewReport>() {
                @Override
                public void onResponse(Call<ViewReportApi.viewReport> call, Response<ViewReportApi.viewReport> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (response.body() != null) {
                                dataArrayList = new ArrayList<>();

                                String in =response.body().getGetInspectionsResult();
                                JSONArray arr;
                                try {
                                    arr  = new JSONArray(in);
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject jObj = arr.getJSONObject(i);
                                        ReportResponse  respon = new ReportResponse();
                                        respon.setHdnScheduleCode(response.body().getGetInspectionsResult());
                                        respon.setHdnScheduleCode(jObj.getString("hdn_ScheduleCode"));
                                        respon.setLTEINSPLabel1( jObj.getString("LTE_INSP_Label1"));
                                        respon.setLTEINSPLabel3(jObj.getString("LTE_INSP_Label3"));
                                        respon.setLTEINSPLabel5(jObj.getString("LTE_INSP_Label5"));

                                        respon.setHdnLattitude(jObj.getString("hdn_Lattitude"));
                                        respon.setHdnLongitude(jObj.getString("hdn_Longitude"));
//                                        db.addAddress(new PlaceModel(Integer.parseInt(response.body().getRecordCount()),
//                                                "", ""));

                                        dataArrayList.add(respon);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                loadAdapter(dataArrayList);

                            } else {
                                Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            private void loadAdapter( ArrayList<ReportResponse> data) {
                if (data != null) {

//                    System.out.println("DATTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" +data);
                    ListAdapter adapter = new ListAdapter(MainActivity.this, data);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setAdapter(adapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    tvNoData.setText("No data Found");
                    tvNoData.setVisibility(View.GONE);
                }
            }

                @Override
                public void onFailure(Call<ViewReportApi.viewReport> call, Throwable t) {
//                CustomProgressDialog.getInstance().dismiss();
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IllegalStateException | JsonSyntaxException exception) {
            Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();

        }
    }
}



