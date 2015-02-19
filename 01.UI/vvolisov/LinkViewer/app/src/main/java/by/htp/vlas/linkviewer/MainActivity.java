package by.htp.vlas.linkviewer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by VlasEL on 15.02.2015 15:16
 */
public class MainActivity extends Activity {

    @InjectView(R.id.link_address)
    EditText mLinkAddressView;

    private final String URI_SCHEME_HTTP = "http://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setOnKeyListenerToView(mLinkAddressView);
    }

    @OnClick(R.id.btn_go)
    void btnGoAction() {
        loadUrl();
    }

    private void loadUrl() {
        String uriString = mLinkAddressView.getText().toString();
        uriString = validateUriString(uriString);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (TextUtils.isEmpty(uriString)) {
            Toast.makeText(this, getString(R.string.msg_blank_link_address), Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (resolveActivities(intent)) {
            intent.setData(Uri.parse(uriString));
            this.startActivity(intent);
        }
    }

    private String validateUriString(String pUriString) {
        if (!URLUtil.isValidUrl(pUriString)) {
            Uri uri = Uri.parse(pUriString);
            String uriScheme = uri.getScheme();
            if (TextUtils.isEmpty(uriScheme)) {
                pUriString = URI_SCHEME_HTTP + pUriString;
            }
        }
        return pUriString;
    }

    private boolean resolveActivities(Intent pIntent) {
        if (pIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, getString(R.string.msg_not_resolved_activity), Toast.LENGTH_SHORT)
                    .show();
            return false;
        } else {
            return true;
        }
    }

    private void setOnKeyListenerToView(View view) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                        keyCode == EditorInfo.IME_ACTION_DONE ||
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        ) {
                    loadUrl();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

}
