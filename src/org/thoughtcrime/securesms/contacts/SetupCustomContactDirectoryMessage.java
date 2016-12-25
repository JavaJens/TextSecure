package org.thoughtcrime.securesms.contacts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import org.thoughtcrime.securesms.PassphraseRequiredActionBarActivity;
import org.thoughtcrime.securesms.R;
import org.thoughtcrime.securesms.service.KeyCachingService;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

public class SetupCustomContactDirectoryMessage extends DialogFragment {
    private static final String TAG = "SetupCustomContactDirectoryMessage";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.setup_dialog_custom_contact_directory_title)
                .setMessage(R.string.setup_dialog_custom_contact_directory_message)
                .setPositiveButton(R.string.setup_dialog_custom_contact_directory_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextSecurePreferences.setCustomContactDirectoryEnabled(getActivity(), true);
                        saveShownAndSyncContacts();
                    }
                })
                .setNegativeButton(R.string.setup_dialog_custom_contact_directory_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextSecurePreferences.setCustomContactDirectoryEnabled(getActivity(), false);
                        saveShownAndSyncContacts();
                    }
                })
                .create();
    }

    private void saveShownAndSyncContacts() {
        TextSecurePreferences.setHasAskedCustomContactDirectory(getActivity(), true);
        new RefreshContactDirectoryTask(getActivity(), KeyCachingService.getMasterSecret(getActivity())).execute();
    }

    public void show(FragmentManager fragmentManager) {
        if(fragmentManager.findFragmentByTag(TAG) == null) {
            show(fragmentManager, TAG);
        }
    }

    public static void showIfNecessary(PassphraseRequiredActionBarActivity activity) {
       if(!TextSecurePreferences.hasAskedCustomContactDirectory(activity)) {
            new SetupCustomContactDirectoryMessage().show(activity.getSupportFragmentManager());
       }
    }
}
