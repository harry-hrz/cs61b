package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();

        AList<Double> times = new AList<>();

        AList<Integer> op = new AList<>();

        SLList<Integer> test = new SLList<>();

        int M = 10000;
        for (int i = 1; i <= 128000; i++) {
            test.addFirst(1);
            if (i == 1000 || i == 2000 || i == 4000 || i == 8000 || i == 16000 || i == 32000 || i == 64000 || i == 128000) {
                long start = System.nanoTime();
                for (int j = 0; j < M; j++){
                    int a = test.getLast();
                }
                long end = System.nanoTime();
                double time = (end - start) / 1000000000.0;
                Ns.addLast(i);
                times.addLast(time);
                op.addLast(M);
            }
        }

        printTimingTable(Ns, times, op);
    }

}
