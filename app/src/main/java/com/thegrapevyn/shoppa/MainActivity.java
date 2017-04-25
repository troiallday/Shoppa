package com.thegrapevyn.shoppa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends CustomMenuActivity {
    String category_id = "";

    //Button btnCart;




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
                final String[] one_product_line = all_products.split("<br>");

                int x,y;
                final String prod_names[]=new String[one_product_line.length];
                final float prod_prices[]=new float[one_product_line.length];
                final String prod_images[]=new String[one_product_line.length];
                final String prod_category[]=new String[one_product_line.length];
                final String prod_details[]=new String[one_product_line.length];

                for(x=0; x<one_product_line.length; x++) {


                    String one_products_string = one_product_line[x];
                    String[] one_products_details = one_products_string.split("%%");
                    prod_names[x] = one_products_details[0];
                    prod_prices[x] = Float.valueOf(one_products_details[1]);
                    prod_images[x] = one_products_details[2];
                    prod_category[x] = one_products_details[3];
                    prod_details[x] = one_products_details[4];

                }


                    CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, prod_names, prod_prices, prod_images);
                    ListView list = (ListView) findViewById(android.R.id.list);
                    list.setAdapter(adapter);

                final int finalX = x;

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String prod_name = prod_names[i];
                            //String prod_price = prod_prices[i];
                            String prod_image1 = prod_images[i];
                            String prod_cat = prod_category[i];
                            String prod_deets = prod_details[i];

                            Intent ii = new Intent("com.thegrapevyn.Full_Details");
                            Bundle extras = new Bundle();
                            extras.putString("pname", prod_name);
                           // extras.putString("pprice", prod_price);
                            extras.putString("pimage1", prod_image1);
                            extras.putString("prod_cat", prod_cat);
                            extras.putString("prod_deets", prod_deets);

                            ii.putExtras(extras);
                            startActivity(ii);

                        }
                    });


            }


        }
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bigdisplay = (ImageView) findViewById(R.id.bigDisplay);
        TextView total = (TextView) findViewById(R.id.totalPrice);
        Button view_more = (Button) findViewById(R.id.view_more);


        String category_id = "";
        String cat_image1 = "";
        String cat_cover = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String category_label = extras.getString("category_name");
            category_id = extras.getString("cid");
            cat_image1 = extras.getString("cat_image1");
            cat_cover = extras.getString("cat_cover");

            TextView category_title = (TextView) findViewById(R.id.category_title);
            category_title.setText(category_label);
            //Glide.with(MainActivity.this)
            //.load("http://192.168.1.2/troi_one"+cat_image1)
            //.into(bigdisplay);


        }


        new BackgroundThings().execute(Config.URL +"/get_products" + category_id + ".php");
        //new BackgroundThings().execute("http://192.168.1.2/troi_one/get.php?id="+category_id);

        ListView list = (ListView) findViewById(android.R.id.list);

        Glide.with(MainActivity.this)
                .load(Config.URL + cat_cover)
                .into(bigdisplay);

        //Glide.with(MainActivity.this)
                //.load("http://192.168.1.2/troi_one" +cat_cover)
                //.into();

        /*
        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("com.thegrapevyn.Full_Details");
                startActivity(i);
            }
        });
        */



    }
}

