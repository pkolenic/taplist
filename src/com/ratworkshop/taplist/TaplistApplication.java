package com.ratworkshop.taplist;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.push.CustomPushNotificationBuilder;
import com.urbanairship.push.PushManager;

import android.app.Application;
import android.util.Log;

public class TaplistApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();

        AirshipConfigOptions options = AirshipConfigOptions.loadDefaultOptions(this);

        UAirship.takeOff(this, options);
        Logger.logLevel = Log.VERBOSE;

        //use CustomPushNotificationBuilder to specify a custom layout
        CustomPushNotificationBuilder nb = new CustomPushNotificationBuilder();

        nb.statusBarIconDrawableId = R.drawable.notification_small;//custom status bar icon

        nb.layout = R.layout.notification;
        nb.layoutIconDrawableId = R.drawable.notification;//custom layout icon
        nb.layoutIconId = R.id.notification_icon;
        nb.layoutSubjectId = R.id.notification_subject;
        nb.layoutMessageId = R.id.notification_message;

        // customize the sound played when a push is received
        //nb.soundUri = Uri.parse("android.resource://"+this.getPackageName()+"/" +R.raw.cat);

        PushManager.shared().setNotificationBuilder(nb);
        PushManager.shared().setIntentReceiver(IntentReceiver.class);
    }
}
