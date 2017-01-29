package com.thegrapevyn.shoppa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by TapiwaLouis on 25/1/2017.
 */

public class CustomListAdapter extends ArrayAdapter<String> {

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }


    private MainActivity context;
    private String[] prod_name;
    private String[] prod_price;
    private String[] prod_image;


    public CustomListAdapter(MainActivity context, String[] prod_name, String[] prod_price, String [] prod_image) {
        super(context, R.layout.mylist, prod_name);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_image = prod_image;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(prod_name[position]);
        extratxt.setText("Price: $" + prod_price[position]);
        Glide.with(context)
                .load(prod_image[position])
                .into(imageView);
        //imageView.setImageBitmap();

        //-------------------------------------------------------------------------

        //Bitmap bitmap = DownloadImage("http://192.168.1.2/troi_one/product_images/image1.jpg");

        //---------------------------------------------------------------------------


        return rowView;
    }
/*
    private Bitmap DownloadImage(String s) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(s);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            Toast.makeText(getContext(), e1.getLocalizedMessage(),
                    Toast.LENGTH_LONG).show();
            e1.printStackTrace();
        }
        return bitmap;
    }

*/
}