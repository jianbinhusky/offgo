package advanced;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.junit.Assert;

public class TestUtils {
    private static final Logger logger = Logger.getLogger(TestUtils.class);
    private static final String ENABLE_FILEANALYZER = "### ENABLE FILEANALYZER ###";
    private static final String DISABLE_FILEANALYZER = "### DISABLE FILEANALYZER ###";
    private static final String MAX_LINES_STRING = "maxLines=";
    private static final int DEFAULT_MAX_LINES = 200;

    public TestUtils() {
    }

    public static void enableFileAnalyzer() {
        logger.error("### ENABLE FILEANALYZER ###");
    }

    /** @deprecated */
    @Deprecated
    public static void disableFileAnalyzer() {
        logger.error("### DISABLE FILEANALYZER ###maxLines=200");
    }

    /** @deprecated */
    @Deprecated
    public static void disableFileAnalyzer(int forMaxLines) {
        logger.error("### DISABLE FILEANALYZER ###maxLines=" + forMaxLines);
    }

    public static void disableFileAnalyzer(String reason) {
        disableFileAnalyzer(reason, 200);
    }

    public static void disableFileAnalyzer(String reason, int forMaxLines) {
        logger.error("### Reason for disabling the fileanalyzer : " + reason);
        logger.error("### DISABLE FILEANALYZER ###maxLines=" + forMaxLines);
    }

    public static boolean forceGC() {
        final AtomicBoolean finalizedFlag = new AtomicBoolean(false);
        Object dummy = new Object() {
            protected void finalize() throws Throwable {
                finalizedFlag.set(true);
                super.finalize();
            }
        };
        Assert.assertNotNull(dummy);
        dummy = null;

        for(int i = 0; i < 20 && !finalizedFlag.get(); ++i) {
            System.gc();
            System.runFinalization();
            Thread.yield();
        }

        boolean result = finalizedFlag.get();
        return result;
    }

    public static long dumpMemory() {
        forceGC();
        return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L;
    }
}
