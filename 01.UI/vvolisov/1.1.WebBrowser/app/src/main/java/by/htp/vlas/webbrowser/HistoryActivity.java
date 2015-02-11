package by.htp.vlas.webbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by VlasEL on 08.02.2015 16:11
 */
public class HistoryActivity extends Activity{

//    @InjectView(R.id.history_title)
//    TextView mTitle;

//    @InjectView(R.id.history_url)
//    TextView mUrl;

    public final static String HISTORY_ACTIVITY_URL_REQUEST_KEY = "URL_REQUEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
//        ButterKnife.inject(this);

        HistoryStorage historyStorage = (HistoryStorage) getIntent().getSerializableExtra(HistoryStorage.class.getSimpleName());

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new HistoryAdapter(historyStorage.getHistoryClone()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                HistoryItem historyItem = (HistoryItem) parent.getItemAtPosition(position);
                intent.putExtra(HISTORY_ACTIVITY_URL_REQUEST_KEY, historyItem.getUrl());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


}
