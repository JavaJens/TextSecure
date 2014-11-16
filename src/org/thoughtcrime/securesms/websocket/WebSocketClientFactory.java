package org.thoughtcrime.securesms.websocket;

import android.content.Context;
import android.os.PowerManager;

import com.codebutler.android_websockets.WebSocketClient;

import org.apache.http.message.BasicNameValuePair;
import org.thoughtcrime.securesms.Release;
import org.thoughtcrime.securesms.push.TextSecurePushTrustStore;
import org.thoughtcrime.securesms.util.TextSecurePreferences;

import java.net.URI;
import java.util.List;

public class WebSocketClientFactory {

  public static WebSocketClient create(String number, String password, WebSocketClient.Listener listener, List<BasicNameValuePair> extraHeaders, PowerManager.WakeLock wakelock, Context context) {
    return new WebSocketClient(URI.create(Release.WS_URL + "?login=" + number + "&password=" + password),
                               listener, null, wakelock, new TextSecurePushTrustStore(context));
  }
}
