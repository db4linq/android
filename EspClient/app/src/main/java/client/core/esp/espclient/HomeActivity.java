package client.core.esp.espclient;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends DashboardActivity {

    private ProgressDialog progressDialog;
    private static String URL = "http://192.168.1.34";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onLightFeature (View v){
        int id = v.getId ();
        progressDialog = ProgressDialog.show(this, "Loading", "Please wait....", true);

        switch (id) {
            case R.id.home_btn_feature1 :
                new RequestTask().execute(URL + "/write/4/1");
                break;
            case R.id.home_btn_feature2 :
                new RequestTask().execute(URL + "/write/4/0");
                break;
            case R.id.home_btn_feature3 :
                new RequestTask().execute(URL + "/write/5/1");
                break;
            case R.id.home_btn_feature4 :
                new RequestTask().execute(URL + "/write/5/0");
                break;
            case R.id.home_btn_feature5 :
                new RequestTask().execute(URL + "/write/6/1");
                break;
            case R.id.home_btn_feature6 :
                new RequestTask().execute(URL + "/write/6/0");
                break;
            case R.id.home_btn_feature7 :
                new RequestTask().execute(URL + "/write/7/1");
                break;
            case R.id.home_btn_feature8 :
                new RequestTask().execute(URL + "/write/7/0");
                break;
        }
    }

    private class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet get = new HttpGet(params[0]);

            try{
                HttpResponse response = httpclient.execute(get);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                String responseString = out.toString();
                Log.d("Response", responseString);
                out.close();
            }catch (ClientProtocolException e) {
                //TODO Handle problems..
                Log.e("ClientProtocolException", e.getMessage());
            } catch (IOException e) {
                //TODO Handle problems..
                Log.e("IOException", e.getMessage());
            }
            progressDialog.dismiss();
            return "Ok";
        }
    }

}
