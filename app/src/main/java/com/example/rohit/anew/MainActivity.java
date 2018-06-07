package com.example.rohit.anew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    String[] urlArray,titleArray,dArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient=new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://188.166.50.216:9000/upload/FindAll")
                .get()
                //.addHeader("Content-Type", "application/json")
                //.addHeader("Authorization", "Your Token")
                //.addHeader("cache-control", "no-cache")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.d("causeOfErrorIs",""+e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                String s=response.body().string();
                Log.d("responseIs",s);

                try
                {
                    JSONObject jsonObject=new JSONObject(s);
                    Log.d("jsonObject",""+jsonObject);

                    JSONArray jsonArray=jsonObject.getJSONArray("doc");
                    Log.d("jsonArray",""+jsonArray);
                    Log.d("length",""+jsonArray.length());

                    urlArray=new String[jsonArray.length()];
                    titleArray=new String[jsonArray.length()];
                    dArray=new String[jsonArray.length()];

                    for (int j=0;j<jsonArray.length();j++)
                    {
                        jsonObject=jsonArray.getJSONObject(j);
                        urlArray[j]=jsonObject.getString("promoUrl");//code
                        titleArray[j]=jsonObject.getString("name");
                        dArray[j]=jsonObject.getString("longDescription");
                        Log.d("videoPlayer",urlArray[j]);
                        Log.d("videoPlayer",titleArray[j]);
                        Log.d("videoPlayer",dArray[j]);
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
