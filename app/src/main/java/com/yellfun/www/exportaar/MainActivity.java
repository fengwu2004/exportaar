package com.yellfun.www.exportaar;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

  private ViewGroup rootView;

  private X5WebView mapview;

  private Button backbutton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    rootView = (ViewGroup)getWindow().getDecorView().getRootView();
  }

  private void createMap() {

    final Context that = this;

    runOnUiThread(new Runnable() {
      @Override
      public void run() {

        mapview = new X5WebView(that);

        mapview.getSettings().setJavaScriptEnabled(true);

        mapview.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {

          @Override
          public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView var1, String var2) {

            mapview.loadUrl(var2);

            return true;
          }
        });

        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mapview.setLayoutParams(params);

        mapview.loadUrl("file:///android_asset/webapp/index.html");
//        mapview.loadUrl("https://wx.indoorun.com/indoorun/test/demo/webapp/index.html?android=1&uuid=sdfds");

        if (mapview != null) {

          rootView.addView(mapview, -1);

          backbutton = new Button(that);

          backbutton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

          backbutton.setBackgroundResource(R.mipmap.fanhui);

          rootView.addView(backbutton);

          ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams)backbutton.getLayoutParams();

          param.leftMargin = 200;

          param.topMargin = 200;

          backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              mapview.setVisibility(View.INVISIBLE);

              backbutton.setVisibility(View.INVISIBLE);
            }
          });
        }
      }
    });
  }

  public void showMap(boolean show){

    Log.i("参数", String.valueOf(show));

    if (!show) {

      if (mapview != null) {

        runOnUiThread(new Runnable() {

          @Override
          public void run() {

            mapview.setVisibility(View.INVISIBLE);

            backbutton.setVisibility(View.INVISIBLE);
          }
        });
      }

      return;
    }

    if (mapview != null) {

      runOnUiThread(new Runnable() {

        @Override
        public void run() {

          mapview.setVisibility(View.VISIBLE);

          backbutton.setVisibility(View.VISIBLE);
        }
      });

      return;
    }

    createMap();
  }
}
