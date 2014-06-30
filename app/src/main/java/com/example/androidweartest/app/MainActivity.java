package com.example.androidweartest.app;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.RemoteInput;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    public static final String RESPUESTA_ELEGIDA = "Respuesta elegida";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void buttonPrueba(View v){

        TextView questionTextView = (TextView) findViewById(R.id.question);
        String question = questionTextView.getText().toString();
        TextView answer1TextView = (TextView) findViewById(R.id.answer1);
        String answer1 = answer1TextView.getText().toString();
        TextView answer2TextView = (TextView) findViewById(R.id.answer2);
        String answer2 = answer2TextView.getText().toString();

        Intent responseIntent = new Intent(getApplicationContext(), ResponseActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, responseIntent, 0);

        String[] choices = new String[]{answer1, answer2};

        RemoteInput input = new RemoteInput.Builder(RESPUESTA_ELEGIDA)
                .setLabel(question).setChoices(choices).build();

        WearableNotifications.Action remoteAction =
                new WearableNotifications.Action.Builder(
                        R.drawable.question_mark, question, pendingIntent)
                        .addRemoteInput(input).build();

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.question_mark)
                        .setContentText(getResources().getString(R.string.question_alarm));

        Notification notification =
                new WearableNotifications.Builder(notificationBuilder)
                        .setHintHideIcon(true)
                        .addAction(remoteAction)
                        .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, notification);
    }

}
