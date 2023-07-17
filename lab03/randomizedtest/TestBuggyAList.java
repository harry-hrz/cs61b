package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  @Test
  public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> good = new AListNoResizing<>();
      BuggyAList<Integer> bug = new BuggyAList<>();

      good.addLast(4);
      good.addLast(5);
      good.addLast(6);

      bug.addLast(4);
      bug.addLast(5);
      bug.addLast(6);

      for (int i = 0; i < 3; i++) {
          int g = good.removeLast();
          int b = bug.removeLast();
          assertEquals(g, b);
      }
  }

  @Test
  public void randomTest() {
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> B = new BuggyAList<>();
      int N = 5000;
      for (int i = 0; i < N; i += 1) {
          int operationNumber = StdRandom.uniform(0, 4);
          if (operationNumber == 0) {
              // addLast
              int randVal = StdRandom.uniform(0, 100);
              L.addLast(randVal);
              B.addLast(randVal);
          } else if (operationNumber == 1) {
              // size
              int size = L.size();
              int bsize = B.size();
              assertEquals(size, bsize);
          } else if (operationNumber == 2) {
              // getlast
              if (L.size() > 0 && B.size() > 0) {
                  int Alast = L.getLast();
                  int Blast = L.getLast();
                  assertEquals(Alast, Blast);
              }
          } else if (operationNumber == 3) {
              // remove last
              if (L.size() > 0 && B.size() > 0) {
                  int removed_last = L.removeLast();
                  int removeLast = B.removeLast();
                  assertEquals(removed_last, removeLast);
              }
          }
      }
  }
}
