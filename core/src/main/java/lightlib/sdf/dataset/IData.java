package lightlib.sdf.dataset;

public interface IData {

    /**
     * @return
     */
    String getName();

    /**
     * @param args
     * @return
     */
    boolean precheckArgument(Object... args);

    /**
     * @param args
     * @return
     */
    Object getData(Object... args);

    /**
     * 心跳检查
     *
     * @return
     */
    boolean ping();
}
