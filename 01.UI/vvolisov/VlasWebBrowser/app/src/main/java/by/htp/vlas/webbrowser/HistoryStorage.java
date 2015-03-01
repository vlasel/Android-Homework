package by.htp.vlas.webbrowser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VlasEL on 08.02.2015 11:42
 */
public final class HistoryStorage {

    private List<HistoryItem> history = new ArrayList<>();

    private HistoryStorage() {
    }

    public void addInHistory(String pUrl, String pTitle) {
        HistoryItem historyItem = new HistoryItem(pUrl, pTitle);
        history.add(historyItem);
    }

    public List<HistoryItem> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    public void setHistory(List<HistoryItem> history) {
        this.history = history;
    }

    private static class HistoryStorageHolder {
        private final static HistoryStorage instance = new HistoryStorage();
    }

    public static HistoryStorage getInstance() {
        return HistoryStorageHolder.instance;
    }

}
