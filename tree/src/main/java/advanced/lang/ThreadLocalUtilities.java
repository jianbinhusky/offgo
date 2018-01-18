package advanced.lang;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

public final class ThreadLocalUtilities {
    private static final Logger LOG = Logger.getLogger(ThreadLocalUtilities.class.getName());
    private static final String THREAD_LOCALS_FIELD = "threadLocals";
    private static final Map<Class, Map<String, Field>> reflectionLookupMap = new ConcurrentHashMap(10, 0.75F, 16);

    private ThreadLocalUtilities() {
    }

    public static void clearThreadLocalMemoryForCurrentThread(Map<ThreadLocal, Object> threadlocalsBefore) {
        try {
            Map<ThreadLocal, Object> currentTLs = extractThreadLocals(Thread.currentThread(), true);
            Iterator var3 = currentTLs.entrySet().iterator();

            while(var3.hasNext()) {
                Entry<ThreadLocal, Object> e = (Entry)var3.next();
                ThreadLocal tl = (ThreadLocal)e.getKey();
                if (tl != null && threadlocalsBefore.containsKey(tl)) {
                    tl.set(e.getValue());
                }
            }
        } catch (Exception var5) {
            LOG.error("error clearing ThreadLocal data for " + Thread.currentThread(), var5);
        }

    }

    public static Map<ThreadLocal, Object> extractThreadLocalValuesForCurrentThread() {
        return extractThreadLocals(Thread.currentThread(), false);
    }

    private static Map<ThreadLocal, Object> extractThreadLocals(Thread t, boolean clear) {
        WeakHashMap ret = null;

        try {
            Field f = getOrLookupField(t.getClass(), "threadLocals");
            Object threadLocalsMap = getFieldViaReflection(t, (Field)f);
            if (threadLocalsMap != null) {
                if (clear) {
                    setFieldViaReflection(t, f, (Object)null);
                }

                WeakReference[] table = (WeakReference[])getFieldViaReflection(threadLocalsMap, "table");
                if (table != null) {
                    WeakReference[] var9 = table;
                    int var8 = table.length;

                    for(int var7 = 0; var7 < var8; ++var7) {
                        WeakReference<ThreadLocal> ref = var9[var7];
                        ThreadLocal tl = ref == null ? null : (ThreadLocal)ref.get();
                        if (tl != null) {
                            Object value = getFieldViaReflection(ref, (String)"value");
                            if (value != null) {
                                if (ret == null) {
                                    ret = new WeakHashMap();
                                }

                                ret.put(tl, value);
                            }
                        }
                    }
                }
            }
        } catch (Exception var12) {
            LOG.error("error extracting ThreadLocal from " + t, var12);
        }

        return (Map)(ret == null ? Collections.EMPTY_MAP : ret);
    }

    private static <T> T getFieldViaReflection(Object o, String fieldName) {
        try {
            return (T) getOrLookupField(o.getClass(), fieldName).get(o);
        } catch (Exception var3) {
            LOG.warn("could not read field '" + fieldName + "' of " + o + " due to " + var3.getMessage());
            return null;
        }
    }

    private static <T> T getFieldViaReflection(Object o, Field field) {
        try {
            return (T) field.get(o);
        } catch (Exception var3) {
            LOG.warn("could not read field '" + field + "' of " + o + " due to " + var3.getMessage());
            return null;
        }
    }

    private static void setFieldViaReflection(Object o, Field field, Object value) {
        try {
            field.set(o, value);
        } catch (Exception var4) {
            LOG.warn("could not write field '" + field + "' of " + o + " due to " + var4.getMessage());
        }

    }

    private static Field getOrLookupField(Class cl, String fieldName) throws SecurityException, NoSuchFieldException {
        Field field = null;
        Map<String, Field> clMap = (Map)reflectionLookupMap.get(cl);
        if (clMap == null) {
            clMap = new ConcurrentHashMap(3);
            reflectionLookupMap.put(cl, clMap);
        } else {
            field = (Field)((Map)clMap).get(fieldName);
        }

        if (field == null) {
            field = cl.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            ((Map)clMap).put(fieldName, field);
        }

        return field;
    }
}

