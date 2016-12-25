package org.thoughtcrime.securesms.contacts;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.thoughtcrime.securesms.crypto.MasterSecret;
import org.thoughtcrime.securesms.util.DirectoryHelper;

import java.io.IOException;

public class RefreshContactDirectoryTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "RefreshDirectoryTask";

    private final Context context;
    private final MasterSecret masterSecret;

    public RefreshContactDirectoryTask(Context context, MasterSecret masterSecret) {
        this.context = context;
        this.masterSecret = masterSecret;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            DirectoryHelper.refreshDirectory(context, masterSecret);
        } catch (IOException e) {
            Log.w(TAG, e);
        }

        return null;
    }
}
