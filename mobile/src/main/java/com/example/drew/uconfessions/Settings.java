package com.example.drew.uconfessions;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Settings extends ActionBarActivity {
    private final String about = "This ...";
    private String[] settingsMenuOptions = {"Dark Theme" , about};
    private static ListView settingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xff3b5998));


        SettingsAdapter adapter = new SettingsAdapter(this, settingsMenuOptions);
        ListView listView = (ListView) findViewById(R.id.settings_listview);
        listView.setAdapter(adapter);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
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

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Settings adapter is an inner class that I used to populate the list view. I chose to use an
     * adapter that I wrote so that I could make it look prettier somewhere down the line. I didn't
     * like the idea of the array list because it only gave a string. With this, I can make the list
     * look however I want.
     *
     * 2/12/15
     */
    public class SettingsAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public SettingsAdapter(Context context, String[] values) {
            super(context, R.layout.activity_settings_lv, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_settings_lv, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.settings_menu_option);
            textView.setText(values[position]);
            // Change the icon for Windows and iPhone

            return rowView;
        }
    }
}
