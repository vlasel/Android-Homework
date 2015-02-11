package by.htp.vlas.webbrowser;

import static by.htp.vlas.webbrowser.HistoryActivity.HISTORY_ACTIVITY_URL_REQUEST_KEY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by _guest on 07.02.2015.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.btn_back)
    Button mBtnBack;

    @InjectView(R.id.btn_forward)
    Button mBtnForward;

//    @InjectView(R.id.btn_go)
//    Button mBtnGo;

    @InjectView(R.id.address)
    EditText mAddress;

    @InjectView(R.id.page_data)
    WebView mPage;


    private final String URL_PREFIX_DEF = "http://";
    private final String TEXT_ENCODING_NAME_DEF = "utf-8";
    private final String STATE_ADDRESS_KEY = "address";
    private final String STATE_NAVIGATE_BTNS_KEY = "navigateButtonsState";
    private final int HISTORY_ACTIVITY_URL_REQUEST = "URL_REQUEST".hashCode();

    private HistoryStorage mHistoryStorage = new HistoryStorage();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mBtnBack.setEnabled(false);
        mBtnForward.setEnabled(false);

        webViewInit();

        //TODO onRestoreInstanceState calling later than onCreate, so it's need to restore state in onCreate
    }

    //----------------------- Save-Restore -------------------------------------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPage.saveState(outState);
        outState.putString(STATE_ADDRESS_KEY, mAddress.getText().toString());
//        outState.putBooleanArray(STATE_NAVIGATE_BTNS_KEY, new boolean[]{mBtnBack.isEnabled(), mBtnForward.isEnabled()});
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPage.restoreState(savedInstanceState);
        mAddress.setText(savedInstanceState.getString(STATE_ADDRESS_KEY));
        //TODO if button has id - this state must besaved automatically
//        boolean[] navigateBtnsState = savedInstanceState.getBooleanArray(STATE_NAVIGATE_BTNS_KEY);
//        mBtnBack.setEnabled(navigateBtnsState[0]);
//        mBtnForward.setEnabled(navigateBtnsState[1]);
    }
    //-----------------------/ Save-Restore -------------------------------------------

    @OnClick(R.id.btn_go)
    void btnGoAction() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mAddress.getWindowToken(), 0);

        loadUrl(null);

        //TODO change button Go to GoRefresh - and when address  not changed manually, make this button "refresh"
    }

    @OnClick(R.id.btn_back)
    void goBack() {
        mPage.goBack();
    }

    @OnClick(R.id.btn_forward)
    void goForward() {
        mPage.goForward();
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch (keycode) {
            case KeyEvent.KEYCODE_BACK: {
                if (mPage.canGoBack()) {
                    goBack();
                    return true;
                } else {
                    return super.onKeyDown(keycode, e);
                }
            }
            case KeyEvent.KEYCODE_MENU: {
                historyActivityStart();
                return true;
            }
            default: {
                return super.onKeyDown(keycode, e);
            }
        }
    }

    // ---------------------------- History Activity transfer ----------------------------------
    private void historyActivityStart() {
        Intent intent = new Intent(this, HistoryActivity.class);
        this.startActivityForResult(intent, HISTORY_ACTIVITY_URL_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == HISTORY_ACTIVITY_URL_REQUEST) {
            String url = data.getStringExtra(HISTORY_ACTIVITY_URL_REQUEST_KEY);
            loadUrl(url);
        }
    }
    // -----------------------------/ History Activity transfer ---------------------------------

    private void loadUrl(String pUrl) {
        String urlString = !TextUtils.isEmpty(pUrl) ? pUrl : mAddress.getText().toString();
        if (TextUtils.isEmpty(urlString)) return;

        if (!URLUtil.isValidUrl(urlString)) {
            urlString = URL_PREFIX_DEF + urlString;
            mAddress.setText(urlString);
        }
        mPage.loadUrl(urlString);
    }

    //--------------- web View ------------------------------------------
    private void webViewInit() {
        WebSettings webViewSettings = mPage.getSettings();
        webViewSettings.setDefaultTextEncodingName(TEXT_ENCODING_NAME_DEF);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setBuiltInZoomControls(true);
        webViewSettings.setDisplayZoomControls(false);
        webViewSettings.setUseWideViewPort(true);
        mPage.setInitialScale(1);
        mPage.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mAddress.setText(url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mBtnBack.setEnabled(view.canGoBack());
            mBtnForward.setEnabled(view.canGoForward());
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
            if(!isReload) {
                mHistoryStorage.addInHistory(url, view.getTitle());
                Toast.makeText(MainActivity.this
                        , (url + " " + getString(R.string.msg_history_event))
                        , Toast.LENGTH_SHORT).show();
            }
        }
    }
    //---------------------------/ web View ----------------------------

}
