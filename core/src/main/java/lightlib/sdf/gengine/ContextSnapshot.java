package lightlib.sdf.gengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-5-2
 */
public class ContextSnapshot {
    protected static final ThreadLocal<ContextSnapshot> threadLocal = new ThreadLocal<>();

    private final List<Object[]> csList = new ArrayList<>();

    private ContextSnapshot() {
    }

    public static ContextSnapshot getContext() {
        ContextSnapshot cs = threadLocal.get();
        if (cs == null) {
            cs = new ContextSnapshot();
            threadLocal.set(cs);
        }
        return cs;
    }

    public static void destroy() {
        threadLocal.remove();
    }

    public void log(Object... message) {
        if (csList.size() > 32768) {
            throw new IllegalStateException("ContextSnapshot out of Max size");
        }

        csList.add(message);
    }

    protected List<Object[]> getCsList() {
        return Collections.unmodifiableList(csList);
    }
}
