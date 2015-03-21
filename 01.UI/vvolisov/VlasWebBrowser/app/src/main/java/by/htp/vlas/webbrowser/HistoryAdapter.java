package by.htp.vlas.webbrowser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VlasEL on 08.02.2015 16:17
 */
public class HistoryAdapter extends BaseAdapter {

    private List<HistoryItem> mHistory;

    public HistoryAdapter(List<HistoryItem> history) {
        mHistory = history;
    }

    @Override
    public int getCount() {
        return mHistory.size();
    }

    @Override
    public HistoryItem getItem(int position) {
        return mHistory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_history_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.historyTitle = (TextView) convertView.findViewById(R.id.history_title);
            viewHolder.historyUrl = (TextView) convertView.findViewById(R.id.history_url);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.historyTitle.setText(getItem(position).getTitle());
        viewHolder.historyUrl.setText(getItem(position).getUrl());

        return convertView;
    }

    static class ViewHolder {
        TextView historyTitle;
        TextView historyUrl;
    }


}
