package by.htp.vlas.webbrowser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VlasEL on 08.02.2015 11:42
 */
public class HistoryStorage {

    private static List<String> history = new ArrayList<>();

    public void addUrlInHistory (String pUrl) {
        history.add(pUrl);
    }

    public static List<String> getHistory() {
        return history;
    }

    public static void setHistory(List<String> history) {
        HistoryStorage.history = history;
    }
}
