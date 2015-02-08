package by.htp.vlas.webbrowser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VlasEL on 08.02.2015 11:42
 */
public final class HistoryStorage {

    public final static String HISTORY_URL_KEY = "url";
    public final static String HISTORY_TITLE_KEY = "title";

    private static List<Map<String, String>> history = new ArrayList<>();

    public static void addInHistory(String pUrl, String pTitle) {
        Map<String, String> historyItem = new HashMap<>(1);
        historyItem.put(HISTORY_URL_KEY, pUrl);
        historyItem.put(HISTORY_TITLE_KEY, pTitle);
        history.add(historyItem);
    }

    public static List<Map<String, String>> getHistory() {
        return history;
    }

    public static void setHistory(List<Map<String, String>> history) {
        HistoryStorage.history = history;
    }
}
