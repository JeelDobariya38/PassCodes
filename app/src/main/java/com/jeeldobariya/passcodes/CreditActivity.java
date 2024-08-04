package com.jeeldobariya.passcodes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.jeeldobariya.common.Logging;
import com.jeeldobariya.common.UrlOpener;

public class CreditActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_credit);
    Logging.log("App enter into credits!!");
  }

  public void onClickRepoBtn(View view) {
    new UrlOpener(this, "https://github.com/JeelDobariya38/PassCodes/releases");
  }

  public void onClickLicenseBtn(View view) {
    new UrlOpener(this, "https://github.com/JeelDobariya38/PassCodes/blob/main/LICENSE.txt");
  }

  public void onClickBackBtn(View view) {
    this.finish();
  }
}
