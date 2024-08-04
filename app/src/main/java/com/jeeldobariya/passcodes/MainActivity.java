package com.jeeldobariya.passcodes;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.jeeldobariya.common.Logging;
import com.jeeldobariya.common.UrlOpener;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
	Logging.log("App Started!!");
  }

  public void handleRepoBtnClick(View view) {
    new UrlOpener(this, "https://github.com/JeelDobariya38/PassCodes/releases");
  }

  public void handleLicenseBtnClick(View view) {
    new UrlOpener(this, "https://github.com/JeelDobariya38/PassCodes/blob/main/LICENSE.txt");
  }

  public void handleQuitBtnClick(View view) {
    finishAndRemoveTask();
  }
}
