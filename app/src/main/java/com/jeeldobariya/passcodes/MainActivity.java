package com.jeeldobariya.passcodes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.jeeldobariya.common.Logging;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Logging.log("App Started!!");
  }

  public void onClickPasswordBtn(View view) {
    Logging.log("Under development feature!!!");
  }

  public void onClickCreditBtn(View view) {
    Intent credit = new Intent(this, CreditActivity.class);
    startActivity(credit);
  }

  public void onClickQuitBtn(View view) {
    finishAndRemoveTask();
  }
}
