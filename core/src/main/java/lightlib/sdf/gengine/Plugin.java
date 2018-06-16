package lightlib.sdf.gengine;

/**
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-4-9
 */
public interface Plugin<T> {

    String Mode = "bDebugMode";//是否调试模式

    static boolean isDebug(Option option) {
        return option.getBoolean(Mode, false);
    }

    /**
     * @return
     */
    String getName();

    /**
     * @param option
     */
    void init(Option option) throws GEngineException;

    /**
     * @return
     */
    T getContext();

    /**
     * @param option
     */
    void destroy(Option option);
}
