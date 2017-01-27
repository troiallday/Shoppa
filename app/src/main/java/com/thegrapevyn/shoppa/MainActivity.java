package com.thegrapevyn.shoppa;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private InputStream OpenHttpConnection(String urlString) throws IOException
    {
        InputStream in = null;
        int response = -1;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");
        }
        return in;
    }


    private String DownloadText(String URL)
    {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e1) {
            Toast.makeText(this, e1.getLocalizedMessage(),
                    Toast.LENGTH_LONG).show();
            e1.printStackTrace();
            return "";
        }
        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer))>0)
            {
//---convert the chars to a String---
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Toast.makeText(this, e.getLocalizedMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();

            return "";
        }
        return str;
    }


    public class BackgroundThings extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            String all_products = DownloadText(url[0]);
            return all_products;
        }

        @Override
        protected void onPostExecute(String all_products) {



            if(!all_products.equals("")){
                String[] one_product_line = all_products.split("<br>");

                int x,y;
                String prod_names[]=new String[one_product_line.length];
                String prod_prices[]=new String[one_product_line.length];
                String prod_images[]=new String[one_product_line.length];

                for(x=0; x<one_product_line.length; x++) {


                    String one_products_string = one_product_line[x];
                    String[] one_products_details = one_products_string.split("%%");
                    prod_names[x] = one_products_details[0];
                    prod_prices[x] = one_products_details[1];
                    prod_images[x] = one_products_details[2];

                }






                    CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, prod_names, prod_prices);
                    ListView list = (ListView) findViewById(android.R.id.list);
                    list.setAdapter(adapter);


            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundThings().execute("http://192.168.1.2/troi_one/get_products2.php");

        ListView list =(ListView)findViewById(android.R.id.list);

    }
}

