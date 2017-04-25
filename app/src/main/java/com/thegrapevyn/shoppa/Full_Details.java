package com.thegrapevyn.shoppa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Full_Details extends AppCompatActivity {
    TextView name, price, category, details;
    ImageView image1, image2, image3;

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


    private class BackThings extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            image1 =(ImageView)findViewById(R.id.image1);

            image1.setImageResource(R.drawable.image_loading2);
        }


        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap bitmap = DownloadImage(url[0]);
            return bitmap;
            //return null;
        }

        private Bitmap DownloadImage(String s) {
            Bitmap bitmap = null;
            InputStream in = null;
            try {
                in = OpenHttpConnection(s);
                bitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e1) {
                Toast.makeText(Full_Details.this, e1.getLocalizedMessage(),
                        Toast.LENGTH_LONG).show();
                e1.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            ImageView img = (ImageView) findViewById(R.id.image1);
            img.setImageBitmap(bitmap);
        }


    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__details);



        name = (TextView)findViewById(R.id.tv_name);
        price = (TextView) findViewById(R.id.tv_price);
        category = (TextView)findViewById(R.id.tv_category);
        details = (TextView) findViewById(R.id.tv_description);
        image1 = (ImageView)findViewById(R.id.image1);


        String product_name = "";
        String product_category = "";
        String product_price = "";
        String product_detail = "";
        String product_image1= "";
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            product_name = extras.getString("pname");
            product_category = extras.getString("prod_cat");
            product_price = extras.getString("pprice");
            product_detail = extras.getString("prod_deets");
            product_image1 = extras.getString("pimage1");




            name.setText(product_name.toString());
            category.setText(product_category.toString());
            price.setText("$ "+product_price.toString());
            details.setText(product_detail.toString());





            new BackThings().execute(Config.URL+product_image1);




        }






    }
}
