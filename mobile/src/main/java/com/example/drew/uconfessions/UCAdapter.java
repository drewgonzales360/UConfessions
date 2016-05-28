package com.example.drew.uconfessions;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
* Created by drew on 2/13/15.
*/

public class UCAdapter extends ParseQueryAdapter {


    public UCAdapter(Context context, final int currentFragment) {
        // Use the QueryFactory to construct a PQA that will only show
        // Calls ParseQuery Super constructor
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Confessions");
                query.whereEqualTo("schoolID", currentFragment);
                query.orderByDescending("createdAt");
                query.setLimit(40);
                return query;
            }
        });
    }


    /**
     * getItemView constructs each little view in the long list of views that the user sees.
     * In the view,  the post, the number of likes, and the time it was posted ago is drawn to the view.
     *
     * @param object parse object that will be returned from the server
     * @param indiConfess the view of each little post in the long listview of posts
     * @param parent the viewgroup parent
     * @return the view when it is done being built
     */
    @Override
    public View getItemView(final ParseObject object, View indiConfess, final ViewGroup parent){
        if (indiConfess == null) {
            indiConfess = View.inflate(getContext(), R.layout.indi_confess, null);
        }
        super.getItemView(object, indiConfess, parent);

        /**
         * This function will set an listener on the post. When users tap the post once, they will
         * go a view that view that allows them to comment. For now it doesn't do anything.
         */
        indiConfess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                }
            }
        );

        final View indiConfessRefresh = indiConfess;
        // Like button for liking each post.
        ImageButton likeButton = (ImageButton) indiConfess.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try {
                      object.put("likes", (int) object.get("likes") + 1);
                      object.saveInBackground();
                  } catch (NullPointerException npl) {
                      object.put("likes", 1);
                      object.saveInBackground();
                  }
                  refreshLikes(indiConfessRefresh, R.id.likes, object);
              }
        });

        // Add the text
        TextView titleTextView = (TextView) indiConfess.findViewById(R.id.text);
        titleTextView.setText(object.getString("post"));


        // Add the likes
        refreshLikes(indiConfess, R.id.likes, object);



        // TODO: Clean this code up.
        // make this a function
        // Add a reminder of how long this item has been outstanding
        TextView timestampView = (TextView) indiConfess.findViewById(R.id.timestamp);
        DateTime dateCreated = new DateTime(object.getCreatedAt());
        DateTime now = new DateTime();
        Interval interval = new Interval(dateCreated, now);
        int hours = new Integer(interval.toPeriod().getHours());
        int minutes = new Integer(interval.toPeriod().getMinutes());
        if(hours > 0){
            timestampView.setText(hours + " hrs and " + minutes + " min ago");
        } else {
            timestampView.setText(minutes + " mins ago");
        }
        return indiConfess;
    }

    private void refreshLikes(View v, int likesView, ParseObject object){
        TextView likes = (TextView) v.findViewById(likesView);
        try{
            likes.setText(object.get("likes").toString() + "♥");
        } catch (NullPointerException npl){
            likes.setText("0" + "♥");
        }
    }
}

