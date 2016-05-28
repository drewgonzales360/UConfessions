package com.example.drew.uconfessions;



import com.parse.ParseObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;

import java.util.Random;


public class Post extends ActionBarActivity implements OnItemSelectedListener{

    private int schoolId;
    private Spinner schoolChoice;
    ParseObject confession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff3b5998));

        confession = new ParseObject("Confessions");
        EditText editText = (EditText) findViewById(R.id.confession);
        editText.setHint(randomHint());

        //This is the information for the spinner that chooses which school the confession is posted.
        schoolChoice = (Spinner) findViewById(R.id.schoolSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.schoolspinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolChoice.setAdapter(adapter);
        schoolChoice.setOnItemSelectedListener(this);

        Button confessButton = (Button) findViewById(R.id.confess);
        confessButton.setOnClickListener(new View.OnClickListener() {


            //Gets confession, then sends it to parse. Currently working(2.7.15)
            @Override
            public void onClick(View backToMainActivity) {
                EditText confessionString = (EditText) findViewById(R.id.confession);
                confession.put("post", confessionString.getText().toString());
                confession.put("schoolID", schoolId);
                confession.put("likes", 0);
                confession.saveInBackground();
                Toast.makeText(getApplicationContext(),"Sent.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(backToMainActivity.getContext(), MainActivity.class));
                return;
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        switch(schoolChoice.getSelectedItemPosition()){
            case 0:          //Berkeley
                schoolId = 0;
                break;
            case 1:           //Davis
                schoolId = 1;
                break;
            case 2:           //Irvine
                schoolId = 2;
                break;
            case 3:            //Los Angeles
                schoolId = 3;
                break;
            case 4:            //Merced
                schoolId = 4;
                break;
            case 5:            //Riverside
                schoolId = 5;
                break;
            case 6:            //San Diego
                schoolId = 6;
                break;
            case 7:            //Santa Barbara
                schoolId = 7;
                break;
            case 8:            //SantaCruz
                schoolId = 8;
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(),"Post not sent.", Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * This method is called to generate a random prompt for the edittext where users write their
     * posts.
     *
     * @return an int that represents the string for the prompts
     */
    private int randomHint(){
        Random hintInt = new Random();
        switch (hintInt.nextInt(10)){
            case 0:
                return R.string.prompt;
            case 1:
                return R.string.prompt1;
            case 2:
                return R.string.prompt2;
            case 3:
                return R.string.prompt3;
            case 4:
                return R.string.prompt4;
            case 5:
                return R.string.prompt5;
            case 6:
                return R.string.prompt6;
            case 7:
                return R.string.prompt7;
            case 8:
                return R.string.prompt8;
            case 9:
                return R.string.prompt9;
        }
        return R.string.prompt;
    }

}
