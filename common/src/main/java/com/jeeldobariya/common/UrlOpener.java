package com.jeeldobariya.common;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class UrlOpener {
  public UrlOpener(Activity activity, String link) {
    try {
      Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
      activity.startActivity(website);
    } catch (ActivityNotFoundException e) {
      Toast.makeText(
              activity,
              "No application can handle this request." + " Please install a webbrowser",
              Toast.LENGTH_LONG)
          .show();
      e.printStackTrace();
    }
  }
}
