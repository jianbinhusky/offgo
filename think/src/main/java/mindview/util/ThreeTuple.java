//: net/mindview/util/ThreeTuple.java
package mindview.util;

import net.mindview.util.*;

public class ThreeTuple<A,B,C> extends net.mindview.util.TwoTuple<A,B> {
  public final C third;
  public ThreeTuple(A a, B b, C c) {
    super(a, b);
    third = c;
  }
  public String toString() {
    return "(" + first + ", " + second + ", " + third +")";
  }
} ///:~
