package com.teikokudesign.okada1986.ap2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        setScreenMain();
    }

    private void setScreenMain(){

        final TextView textview1 = (TextView) findViewById(R.id.textview1);
        final TextView textview2 = (TextView) findViewById(R.id.textview2);
        final TextView textview3 = (TextView) findViewById(R.id.textview3);
        final TextView textview4 = (TextView) findViewById(R.id.textview4);
        final TextView textview5 = (TextView) findViewById(R.id.textview5);
        final TextView textview6 = (TextView) findViewById(R.id.textview6);
        final TextView textview7 = (TextView) findViewById(R.id.textview7);

        Button showResultButton = (Button)findViewById(R.id.send_button);

        textview1.setText("Welcome to the Witch's House");
        textview2.setText("Please push ”See my future” of the bottom then you will be able to see your fortune-telling result");
        textview3.setText("");
        textview4.setText("");
        textview5.setText("");
        textview6.setText("");
        textview7.setText("");
        showResultButton.setText("See my future");
        //イベント登録
        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Change text
                textview1.setText("*** Today's your fortune. ***");
                textview2.setText("Economic fortune: "+ getRandomStars());
                textview3.setText("Good health: "+ getRandomStars());
                textview4.setText("Better luck: "+ getRandomStars());
                textview5.setText("The words of the witch.");
                textview7.setText("There shall not be found among you anyone who burns his son or his daughter as an offering, anyone who practices divination or tells fortunes or interprets omens, or a sorcerer. by Deuteronomy 18:10");

                //Text size
                textview2.setTextSize(25);
                textview3.setTextSize(25);
                textview4.setTextSize(25);
                textview6.setTextSize(18);

                try
                {

                    //Read CSV data.
                    AssetManager assetManager = getResources().getAssets();
                    InputStream inputStream = assetManager.open("data.csv");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferReader = new BufferedReader(inputStreamReader);

                    String line;
                    String messageText="";
                    while ((line = bufferReader.readLine()) != null) {
                        //カンマ区切りで１つづつ配列に入れる
                        String[] RowData = line.split(",");
                        messageText=messageText+getRandomWords(RowData)+" ";
                    }
                    textview6.setText(messageText);

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    //call then get Random stars. It means your luck level.
    public String getRandomStars(){
        Random rnd = new Random();
        int ran = rnd.nextInt(5);
        String stars;
        switch (ran) {
            case 0:  stars = "*";
                break;
            case 1:  stars = "**";
                break;
            case 2:  stars = "***";
                break;
            case 3:  stars = "****";
                break;
            case 4:  stars = "*****";
                break;
            default: stars = "Invalid number";
                break;
        }
        return stars;
    }
    public String getRandomWords(String args[]){
        Random rnd = new Random();
        int ran = rnd.nextInt(args.length);

        return args[ran];

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
