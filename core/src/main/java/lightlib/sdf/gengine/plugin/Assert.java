package lightlib.sdf.gengine.plugin;

import lightlib.sdf.gengine.ContextSnapshot;
import lightlib.sdf.gengine.GEngineException;
import lightlib.sdf.gengine.Option;

public class Assert extends BasePlugin<AssertOP> {

    protected final AssertOP ap = new AssertOP();

    @Override
    public String getName() {
        return "assert";
    }

    @Override
    public void init(Option option) throws GEngineException {
        threadLocal.set(ap);
    }
}

class AssertOP {

    AssertOP() {
    }

    public void fail(String message) throws GEngineException {
        ContextSnapshot.getContext().log("assert.fail", message);
        throw new GEngineException("assert.fail:" + message);
    }

    public void isFalse(String message, boolean condition) throws GEngineException {
        ContextSnapshot.getContext().log("assert.isFalse fail", message);
        throw new GEngineException("assert.isFalse fail:" + message);
    }

    public void isTrue(String message, boolean condition) throws GEngineException {
        ContextSnapshot.getContext().log("assert.isTrue fail", message);
        throw new GEngineException("assert.isTrue fail:" + message);
    }

    public void isNotNull(String message, Object object) throws GEngineException {
        isTrue(message, object != null);
    }

    public void isNull(String message, Object object) throws GEngineException {
        isTrue(message, object == null);
    }

    public void assertEquals(String message, Object expected, Object actual) throws GEngineException {
        if (expected != null || actual != null) {
            if (expected == null || !expected.equals(actual)) {
                ContextSnapshot.getContext().log("assert.assertEquals fail", message);
                throw new GEngineException("assert.isTrue fail:" + message);
            }
        }
    }
}
