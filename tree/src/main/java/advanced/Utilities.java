package advanced;


import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Utilities {
    private static final Logger LOG = Logger.getLogger(Utilities.class.getName());
    public static Class[] getAllInterfaces(Class cl) {
        Set lst = new LinkedHashSet();
        fillInterfaceList(cl, lst);
        return (Class[])lst.toArray(new Class[lst.size()]);
    }

    private static void fillInterfaceList(Class clazz, Set toFill) {
        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            fillInterfaceList(superClass, toFill);
        }

        Class[] ifs = clazz.getInterfaces();
        toFill.addAll(Arrays.asList(ifs));
        if (ifs != null) {
            for(int i = 0; i < ifs.length; ++i) {
                fillInterfaceList(ifs[i], toFill);
            }
        }

    }
}

