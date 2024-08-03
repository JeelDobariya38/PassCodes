package com.jeeldobariya.passcodes;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void openBrowser(String link) {
    try {
      Intent website = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
      startActivity(website);
    } catch (ActivityNotFoundException e) {
      Toast.makeText(
              this,
              "No application can handle this request." + " Please install a webbrowser",
              Toast.LENGTH_LONG)
          .show();
      e.printStackTrace();
    }
  }

  public void handleRepoBtnClick(View view) {
    this.openBrowser("https://github.com/JeelDobariya38/PassCodes/releases");
  }

  public void handleLicenseBtnClick(View view) {
    this.openBrowser("https://github.com/JeelDobariya38/PassCodes/blob/main/LICENSE.txt");
  }

  public void handleQuitBtnClick(View view) {
    finishAndRemoveTask();
  }
}
