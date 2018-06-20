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

    public void isTrue(boolean condition) {
        ContextSnapshot.getContext().log("assert.isTrue", condition);
    }
}
