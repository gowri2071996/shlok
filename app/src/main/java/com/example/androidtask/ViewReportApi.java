package com.example.androidtask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ViewReportApi {
   @GET("get/cqRrpIGVVe?indent=2")
   Call<viewReport> viewReport();

    public class viewReport  {
        private String recordCount;

        private String GetInspectionsResult;

        public String getRecordCount ()
        {
            return recordCount;
        }

        public void setRecordCount (String recordCount)
        {
            this.recordCount = recordCount;
        }

        public String getGetInspectionsResult ()
        {
            return GetInspectionsResult;
        }

        public void setGetInspectionsResult (String GetInspectionsResult)
        {
            this.GetInspectionsResult = GetInspectionsResult;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [recordCount = "+recordCount+", GetInspectionsResult = "+GetInspectionsResult+"]";
        }
    }

}
