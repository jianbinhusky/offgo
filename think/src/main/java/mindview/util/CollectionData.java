//: net/mindview/util/CollectionData.java
// A Collection filled with data using a generator object.
package mindview.util;
import net.mindview.util.*;

import java.util.*;

public class CollectionData<T> extends ArrayList<T> {
  public CollectionData(net.mindview.util.Generator<T> gen, int quantity) {
    for(int i = 0; i < quantity; i++)
      add(gen.next());
  }
  // A generic convenience method:
  public static <T> CollectionData<T>
  list(net.mindview.util.Generator<T> gen, int quantity) {
    return new CollectionData<T>(gen, quantity);
  }
} ///:~
