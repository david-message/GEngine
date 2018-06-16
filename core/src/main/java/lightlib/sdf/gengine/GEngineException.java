package lightlib.sdf.gengine;

/**
 *
 */
public class GEngineException extends Exception {

    public GEngineException(String msg) {
        super(msg);
    }

    public GEngineException(Throwable t) {
        super(t);
    }

    public GEngineException(String msg, Throwable t) {
        super(msg, t);
    }
}
