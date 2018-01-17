package advanced.lang;

import java.util.List;
import java.util.Map;

public class ThreadLocalHolder implements Runnable {
    private ThreadLocal<String> status = new ThreadLocal<>();
    private ThreadLocal<StringBuffer> sb = new ThreadLocal<>();
    private ThreadLocal<List<String>> list = new ThreadLocal<>();
    private ThreadLocal<Map<String, Object>> info = new ThreadLocal<>();

    public String getStatus() {
        return this.status.get();
    }

    public StringBuffer getStringBuffer() {
        return this.sb.get();
    }

    public List getList() {
        return this.list.get();
    }

    public Map<String, Object> getInfo() {
        return this.info.get();
    }


    @Override
    public void run() {

    }
}
