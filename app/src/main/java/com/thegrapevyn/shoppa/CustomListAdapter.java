package com.thegrapevyn.shoppa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by TapiwaLouis on 25/1/2017.
 */

public class CustomListAdapter  extends ArrayAdapter<String> {



    private SharedPreferences prefs;
    private String prefName = "MyPref";
    private static final String Total_Price = "fontsize";
    DBAdapter db = new DBAdapter(getContext());

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



    private  MainActivity context;
    private  String[] prod_name;
    private  float [] prod_price;
    private  String [] prod_image;

    ImageView bigdisplay ;




    public CustomListAdapter(MainActivity context, String[] prod_name, float[] prod_price, String[] prod_image) {
        super(context, R.layout.mylist, prod_name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.prod_name=prod_name;
        this.prod_price=prod_price;
        this.prod_image=prod_image;

        //TextView p = (TextView)findViewById(R.id.totalPrice);





    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);
        final TextView total = (TextView)rowView.findViewById(R.id.totalPrice);
        //bigdisplay = (ImageView)rowView.findViewById(R.id.bigDisplay);


        //total.setText("$0.70");
        txtTitle.setText(prod_name[position]);
        extratxt.setText("Price: $" + prod_price[position]);

        Glide.with(context)
                .load(Config.URL+prod_image[position])
                .centerCrop()
                .into(imageView);




        final float[] b = {0};
        final float sum[] ={0};





        final Button btnCart = (Button)rowView.findViewById(R.id.btnAddToCart);


                btnCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.open();
                        long id = db.insertProduct(prod_name[position], String.valueOf(prod_price[position]) ,prod_image[position] );
                        db.close();
                        btnCart.setBackgroundResource(R.drawable.redcart);
                        Toast.makeText(getContext(), "Added to Cart.", Toast.LENGTH_SHORT).show();
                    }
                });




        final Button btnAdd, btnMinus, view_more;
        btnAdd = (Button)rowView.findViewById(R.id.btnAdd);
        btnMinus = (Button) rowView.findViewById(R.id.btnMinus);
        view_more = (Button) rowView.findViewById(R.id.view_more);


        btnCart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                btnAdd.setVisibility(View.VISIBLE);
                btnMinus.setVisibility(View.VISIBLE);
                view_more.setVisibility(View.VISIBLE);



                return true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                String name, price, image1;
                name= prod_name[position];
                price = String.valueOf(prod_price[position]);
                image1 = prod_image[position];

                long id = db.insertProduct(name, price ,image1 );
                db.close();
                //btnCart.setBackgroundResource(R.drawable.redcart);
                Toast.makeText(getContext(), "Added to Cart.", Toast.LENGTH_SHORT).show();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float a = prod_price[position];


                b[0]+=a;

                for (int x=0; x<b.length; x++){
                    sum[0] -= b[x];
                }
                String theprice = String.valueOf(sum[0]);

                Toast.makeText(getContext(), "Price : $"+theprice, Toast.LENGTH_SHORT).show();

            }
        });

        view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent("com.thegrapevyn.Full_details");

            }
        });







        return rowView;
    }

}