package lightlib.sdf.gengine.plugin;

import lightlib.sdf.gengine.Option;
import lightlib.sdf.gengine.Plugin;

import java.util.logging.Logger;

/**
 * @param <T>
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-5-2
 */
public abstract class BasePlugin<T> implements Plugin<T> {

    protected final Logger log = Logger.getLogger(getClass().getName());
    protected final ThreadLocal<T> threadLocal = new ThreadLocal<>();

    @Override
    public T getContext() {
        return threadLocal.get();
    }

    @Override
    public void destroy(Option option) {
        threadLocal.remove();
    }
}
