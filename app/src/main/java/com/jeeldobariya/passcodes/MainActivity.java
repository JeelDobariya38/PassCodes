package com.jeeldobariya.passcodes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import com.jeeldobariya.common.Logging;
import com.jeeldobariya.network.ApplicationReleaseStatus;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class MainActivity extends Activity {

  private final ExecutorService executor = Executors.newSingleThreadExecutor();
  private final Handler handler = new Handler(Looper.getMainLooper());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Logging.log("App Started!!");
    checkForUpdate();
  }

  public void checkForUpdate() {
    executor.execute(
        () -> {
          try {
            ApplicationReleaseStatus currstatus = ApplicationReleaseStatus.NOINFO;

            OkHttpClient client = new OkHttpClient();
            String url = "https://api.github.com/repos/JeelDobariya38/PassCodes/releases/latest";

            Request request = new Request.Builder().url(url).build();

            try (Response response = client.newCall(request).execute()) {
              if (response.isSuccessful() && response.body() != null) {
                String jsonResponse = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                String tag = jsonObject.getString("tag_name");
                String name = jsonObject.getString("name");
                String link = jsonObject.getString("html_url");

                boolean result = isUpdate(tag, name);

                if (result) {
                  currstatus = ApplicationReleaseStatus.UPDATEAVAILABLE;
                } else {
                  currstatus = ApplicationReleaseStatus.NOUPDATEAVAILABLE;
                }
              } else if (response.code() == 404) {
                currstatus = ApplicationReleaseStatus.NOSTABLERELEASEAVAILABLE;
              } else {
                currstatus = ApplicationReleaseStatus.NOINFO;
              }
            } catch (Exception e) {
              currstatus = ApplicationReleaseStatus.ERRORINCHECKING;
            }

            final ApplicationReleaseStatus finalstatus = currstatus;

            handler.post(
                () -> {
                  // Pass the status back to the main thread
                  showStatus(finalstatus);
                });
          } catch (Exception e) {
            Logging.log("InterruptedExpection");
          }
        });
  }

  public boolean isUpdate(String tag, String name) {
    String versionName = "v0.0.0-Alpha";
    String versionTag = "v0.0.0";

    if (versionTag == tag && versionName == name) {
      return true;
    }

    return false;
  }

  public void showStatus(ApplicationReleaseStatus status) {
    if (status == ApplicationReleaseStatus.UPDATEAVAILABLE) {
      Toast.makeText(this, "Go to Release", Toast.LENGTH_SHORT).show();
    } else if (status == ApplicationReleaseStatus.NOSTABLERELEASEAVAILABLE) {
      Toast.makeText(this, "Prerealease", Toast.LENGTH_LONG).show();
    } else if (status == ApplicationReleaseStatus.NOUPDATEAVAILABLE) {
      Toast.makeText(this, "All Upto Date", Toast.LENGTH_LONG).show();
    } else {
      Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
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

  @Override
  protected void onDestroy() {
    super.onDestroy();
    executor.shutdown();
  }
}
