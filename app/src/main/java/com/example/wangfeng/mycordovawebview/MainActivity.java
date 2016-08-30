package com.example.wangfeng.mycordovawebview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.LOG;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;


public class MainActivity extends ActionBarActivity {
    private SystemWebView systemWebView;
    private CordovaWebView cordovaWebView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LOG.setLogLevel(LOG.DEBUG);//设置日志级别
        systemWebView = (SystemWebView)findViewById(R.id.cordova_webview);
        webView = (WebView)findViewById(R.id.normal_webview);
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);//这里会解析res/xml/config.xml配置文件
        cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(systemWebView));//创建一个cordovawebview
        cordovaWebView.init(new CordovaInterfaceImpl(this), parser.getPluginEntries(), parser.getPreferences());//初始化
        systemWebView.loadUrl("http://www.baidu.com");
        //webView.loadUrl("http://www.baidu.com");
        // systemWebView.loadUrl("http://10.1.1.203/test.html");
    }

    @Override
    protected void onResume (){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.cordovaWebView != null) {
            cordovaWebView.handleDestroy();
        }
    }
}
