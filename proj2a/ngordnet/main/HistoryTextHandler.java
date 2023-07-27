package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap m;
    public HistoryTextHandler(NGramMap ngm) {
        this.m = ngm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String result = "";

        for (String word : words) {
            TimeSeries ts = this.m.weightHistory(word, startYear, endYear);
            result += word + ": " + ts.toString() + "\n";
        }

        return result;
    }
}
