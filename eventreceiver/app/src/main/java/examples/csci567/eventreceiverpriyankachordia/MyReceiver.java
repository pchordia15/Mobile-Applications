package examples.csci567.eventreceiverpriyankachordia;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;
        import android.support.v4.app.NotificationCompat;
        import android.app.TaskStackBuilder;
        import android.app.PendingIntent;
        import android.app.NotificationManager;
import android.app.Notification;

public class MyReceiver extends BroadcastReceiver {

    //To provide the notification for an event when an event is received.
    public void onReceive(Context notification, Intent intent) {


        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(notification)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentText("The system date is changed.")
           // The default system sound will be played.
                        .setDefaults(Notification.DEFAULT_SOUND);
        Intent resultIntent = new Intent(notification, MainActivity.class);

        TaskStackBuilder st = TaskStackBuilder.create(notification);
        st.addParentStack(MainActivity.class);
        st.addNextIntent(resultIntent);
        PendingIntent notificationClickIntent =
                st.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        nBuilder.setContentIntent(notificationClickIntent);
        NotificationManager nm =
                (NotificationManager) notification.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(01, nBuilder.build());

        // Toast used to display a message at the bottom.
        Toast.makeText(notification, "Alert!! Date is changed.", Toast.LENGTH_LONG).show();
    }
}