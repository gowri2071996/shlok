package com.example.androidtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidtask.MainActivity;
import com.example.androidtask.MapsActivity;
import com.example.androidtask.R;
import com.example.androidtask.ReportResponse;
import com.example.androidtask.SplashActivity;
import com.example.androidtask.ViewReportApi;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    private ArrayList<ReportResponse> dataList;
    private Context context;

    public ListAdapter(Context context, ArrayList<ReportResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tvSchId, tvTitle, tvData, tvchk;
        ConstraintLayout viewList;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvSchId = mView.findViewById(R.id.tvSchId);
            tvTitle = mView.findViewById(R.id.tvTitle);
            tvData = mView.findViewById(R.id.tvData);
            tvchk = mView.findViewById(R.id.tvchk);
            viewList = mView.findViewById(R.id.viewList);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ReportResponse datas = dataList.get(position);

        holder.tvSchId.setText(datas.getHdnScheduleCode());
        holder.tvTitle.setText(datas.getLTEINSPLabel1());
        holder.tvData.setText(datas.getLTEINSPLabel3());
        holder.tvchk.setText(datas.getLTEINSPLabel5());

        holder.viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Latitude", datas.getHdnLattitude());
                bundle.putString("Longitude", datas.getHdnLongitude());
                Intent i = new Intent(context, MapsActivity.class);
                i.putExtras(bundle);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}