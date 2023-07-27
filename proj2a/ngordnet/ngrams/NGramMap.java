package ngordnet.ngrams;

import java.util.Collection;
import java.util.*;
import edu.princeton.cs.algs4.In;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    private TreeMap<String, TimeSeries> wk;
    private TimeSeries count;
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        In in_word = new In(wordsFilename);
        In in_count = new In(countsFilename);

        wk = new TreeMap<>();
        TimeSeries ts = new TimeSeries();
        count = new TimeSeries();

        String word = in_word.readString();
        ts.put(in_word.readInt(), (double) in_word.readInt());
        in_word.readInt();
        while (true) {
            String word_n = in_word.readString();
            if (!Objects.equals(word_n, word)) {
                wk.put(word, ts);
                ts = new TimeSeries();
                word = word_n;
            }
            ts.put(in_word.readInt(), (double) in_word.readInt());
            in_word.readInt();
            if (in_word.isEmpty()) {
                wk.put(word, ts);
                break;
            }
        }

        while (!in_count.isEmpty()) {
            String line = in_count.readLine();
            String[] values = line.split(",");
            count.put(Integer.parseInt(values[0]), Double.parseDouble(values[1]));
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        return (TimeSeries) this.wk.get(word).clone();
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        return new TimeSeries(countHistory(word), startYear, endYear);
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        return (TimeSeries) count.clone();
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        TimeSeries ts = countHistory(word);
        TimeSeries c = totalCountHistory();
        TimeSeries ts_new;
        ts_new = ts.dividedBy(c);
        return ts_new;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        return new TimeSeries(weightHistory(word), startYear, endYear);
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        Iterator<String> iterator = words.iterator();
        String firstWord = iterator.next();
        iterator.remove();
        TimeSeries result = weightHistory(firstWord);
        for (String word : words) {
            TimeSeries ts = weightHistory(word);
            result = result.plus(ts);
        }
        return result;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words,
                              int startYear, int endYear) {
        return new TimeSeries(summedWeightHistory(words), startYear, endYear);
    }


}
