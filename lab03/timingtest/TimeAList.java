package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        int[] n = new int[9];

        AList<Double> times = new AList<>();
        double[] t = new double[9];

        AList<Integer> op = new AList<>();
        int[] ops = new int[9];

        AList<Integer> testlist = new AList<>();

        int j = 0;
        long start_time = System.nanoTime();
        for (int i = 1; i <= 1280000; i++) {
            testlist.addLast(1);
            if (i == 1000 || i == 2000 || i == 4000 || i == 8000 || i == 16000 || i == 32000 || i == 64000 || i == 128000 || i == 1280000) {
                long end_time = System.nanoTime();
                long time = end_time - start_time;
                n[j] = i;
                t[j] = time / 1000000000.0;
                ops[j] = i;
                j += 1;
            }
        }
        for (int i = 0; i < n.length; i++) {
            Ns.addLast(n[i]);
            times.addLast(t[i]);
            op.addLast(ops[i]);
        }

        printTimingTable(Ns, times, op);
    }
}
