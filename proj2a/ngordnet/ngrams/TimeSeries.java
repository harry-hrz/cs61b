package ngordnet.ngrams;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        ArrayList<Integer> stay = stay(ts, startYear, endYear);
        for (int key : stay) {
            this.put(key, ts.get(key));
        }
    }

    private ArrayList<Integer> stay(TimeSeries ts, int start, int end) {
        Set<Integer> k = ts.keySet();
        ArrayList<Integer> in = new ArrayList<>(ts.size());
        for (int key : k) {
            if (key >= start && key <= end) {
                in.add(key);
            }
        }
        return in;
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        Set<Integer> all_keys = this.keySet();
        return new ArrayList<>(all_keys);
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        ArrayList<Double> values = new ArrayList<>();
        for (double val : this.values()) {
            values.add(val);
        }
        return values;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries ts_new = (TimeSeries) this.clone();
        double t;
        ArrayList<Integer> add = new ArrayList<>();
        for (int key_this : ts_new.keySet()) {
            for (int key_ts : ts.keySet()) {
                if (key_this == key_ts) {
                    t = ts_new.get(key_this) + ts.get(key_ts);
                    ts_new.put(key_this, t);
                }
                if (!ts_new.containsKey(key_ts)) {
                    add.add(key_ts);
                }
            }
        }
        for (int add_key : add) {
            ts_new.put(add_key, ts.get(add_key));
        }
        return ts_new;
    }

     /** Returns the quotient of the value for each year this TimeSeries divided by the
      *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
      *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
      *  Should return a new TimeSeries (does not modify this TimeSeries). */
     public TimeSeries dividedBy(TimeSeries ts) {
         if (missing(ts)) throw new IllegalArgumentException();
         TimeSeries ts_new = (TimeSeries) this.clone();
         ts_new.replaceAll((k, v) -> this.get(k) / ts.get(k));
         return ts_new;
    }
    private boolean missing(TimeSeries ts) {
         return !ts.keySet().containsAll(this.keySet());
    }
}
