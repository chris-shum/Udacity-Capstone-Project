package app.com.loaded.android.loaded.ui;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.com.loaded.android.loaded.R;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            DateFormat day = new SimpleDateFormat("EEEE");
            Date dateobj = new Date();
            String currentDay = day.format(dateobj);

            DateFormat time = new SimpleDateFormat("HH:mm:ss");
            String currentTime = time.format(dateobj);

            String number = "Womp";

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + "908-264-7222"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, callIntent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            views.setTextViewText(R.id.textView, number);


            try {
                Date currentTimeDate = time.parse(currentTime);
                Date opening = time.parse("11:00:00");
                Date closing = time.parse("21:00:00");
                Date openingSunday = time.parse("13:00:00");
                Date closingSunday = time.parse("18:00:00");

                if (currentDay.equals("Sunday")) {
                    if(currentTimeDate.after(openingSunday)&&currentTimeDate.before(closingSunday)){
                        number = "open";
                    }else{
                        number = "closed";
                    }
                } else {
                    if(currentTimeDate.after(opening)&&currentTimeDate.before(closing)){
                        number = "open";
                    }else{
                        number = "closed";
                    }
                }
                views.setTextViewText(R.id.textView, "Loaded is " +number);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Get the layout for the App Widget and attach an on-click listener to the button

            // Tell the AppWidgetManager to perform an update on the current App Widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}