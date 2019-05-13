package com.example.ormliteandroidsqlcipher.notifyservice;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ormliteandroidsqlcipher.R;
import com.example.ormliteandroidsqlcipher.notifyservice.data.DatabaseHelper;
import com.example.ormliteandroidsqlcipher.notifyservice.data.Thing;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;

import java.sql.SQLException;
import java.util.Random;

/**
 * Service which creates a new Thing entry in the database whenever it is started. An alarm calls it every 20 seconds.
 *
 * @author kevingalligan
 */
public class GetStuffService extends OrmLiteBaseService<DatabaseHelper> {

    private final static String LOG_NAME = GetStuffService.class.getName();
    private static PendingIntent pi;

    public static void setAlarm(Context c, int seconds) {
        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);

        if (pi != null) {
            try {
                mgr.cancel(pi);
            } catch (Exception e) {
                // Heyo
                Log.e(LOG_NAME, "unable to cancel pending intent");
            }
        }

        Intent intent = new Intent(c, GetStuffService.class);
        pi = PendingIntent.getService(c, 0, intent, 0);

        mgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), seconds * 1000, pi);
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        Thing thing = new Thing();
        Random random = new Random();
        thing.setDescription("My thing " + random.nextInt(1000));
        try {
            getHelper().getThingDao().create(thing);
        } catch (SQLException e) {
            throw new RuntimeException("Could not create a new Thing in the database", e);
        }

        Intent intent = new Intent(this, ThingActivity.class);
        intent.putExtra(ThingActivity.KEY_THING_ID, thing.getId());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // The ticker text, this uses a formatted string so our message could be localized
        String tickerText = "New Thing: " + thing.getDescription();

        createNewNotif(this, tickerText, "NotifyService", "Test app for ORMLite", contentIntent, 1234, R.drawable.icon);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNewNotif(Context context, String tickerText, String title, String content,
                                PendingIntent i, int notifId, int icon) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, context.getPackageName());
        builder.setSmallIcon(icon)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(title)
                .setContentIntent(i)
                .setContentText(tickerText);

        Notification notif = builder.build();
        notif.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONLY_ALERT_ONCE;
        notif.defaults = Notification.DEFAULT_VIBRATE;

        /*
         * Note that we use R.layout.incoming_message_panel as the ID for the notification. It could be any integer you
         * want, but we use the convention of using a resource id for a string related to the notification. It will
         * always be a unique number within your application.
         */
        nm.notify(notifId, notif);
    }
}
