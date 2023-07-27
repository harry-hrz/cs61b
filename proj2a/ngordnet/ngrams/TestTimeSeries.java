package ngordnet.ngrams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TestTimeSeries {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertEquals(expectedYears, totalPopulation.years());

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), totalPopulation.data().get(i), 1E-10);
        }
    }

    @Test
    public void testRemove() {
        TimeSeries test = new TimeSeries();
        test.put(1991, 0.0);
        test.put(1992, 100.0);
        test.put(1994, 200.0);
        test.put(1998, 200.0);
        test.put(1993, 200.0);
        test.put(1990, 200.0);
        TimeSeries test1 = new TimeSeries(test, 1991, 1996);
        System.out.println(test1.keySet());
    }

    @Test
    public void testYearsData() {
        TimeSeries test = new TimeSeries();
        test.put(1991, 0.0);
        test.put(1992, 100.0);
        test.put(1994, 200.0);
        test.put(1998, 200.0);
        test.put(1993, 200.0);
        test.put(1990, 200.0);
        System.out.println(test.data());
    }

    @Test
    public void testDivide() {
        TimeSeries test = new TimeSeries();
        test.put(1991, 100.0);
        test.put(1992, 200.0);
        test.put(1994, 300.0);
        //test.put(1998, 400.0);

        TimeSeries test1 = new TimeSeries();
        test1.put(1991, 10.0);
        test1.put(1992, 20.0);
        test1.put(1994, 30.0);
        test1.put(1998, 40.0);
        test1.put(1989, 123.0);

        TimeSeries ts = test.dividedBy(test1);
        System.out.println(ts.data());
    }

    @Test
    public void testPlus() {
        TimeSeries test = new TimeSeries();
        test.put(1991, 100.0);
        test.put(1992, 200.0);
        test.put(1994, 300.0);
        //test.put(1998, 400.0);

        TimeSeries test1 = new TimeSeries();
        test1.put(1991, 10.0);
        test1.put(1992, 20.0);
        test1.put(1994, 30.0);
        test1.put(1998, 40.0);
        test1.put(1989, 123.0);

        TimeSeries ts = test.plus(test1);
        System.out.println(ts);
    }
} 