//: net/mindview/util/BasicGenerator.java
// Automatically create a Generator, given a class
// with a default (no-arg) constructor.
package mindview.util;

import net.mindview.util.*;

public class BasicGenerator<T> implements net.mindview.util.Generator<T> {
  private Class<T> type;
  public BasicGenerator(Class<T> type){ this.type = type; }
  public T next() {
    try {
      // Assumes type is a public class:
      return type.newInstance();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
  // Produce a Default generator given a type token:
  public static <T> net.mindview.util.Generator<T> create(Class<T> type) {
    return new BasicGenerator<T>(type);
  }
} ///:~
