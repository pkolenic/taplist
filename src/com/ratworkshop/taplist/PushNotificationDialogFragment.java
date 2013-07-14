package com.ratworkshop.taplist;

import com.urbanairship.push.PushManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

public class PushNotificationDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        builder.setMessage(R.string.notification_dialog_prompt)
        .setPositiveButton(R.string.notification_dialog_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	PushManager.enablePush();
            	setDisplayed();
            }
        })
        .setNegativeButton(R.string.notification_dialog_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
				PushManager.disablePush();
				setDisplayed();
            }
        });
        // Create the AlertDialog object and return it
        
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
    
    public void setDisplayed() {
		String preferenceFile = getActivity().getString(R.string.taplist_preference);
		SharedPreferences sharedPref = getActivity().getSharedPreferences(preferenceFile, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean(getActivity().getString(R.string.PUSH_NOTIFICATION_SHOWN), true);
		editor.commit();
    }
}
