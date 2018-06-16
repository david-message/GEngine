package lightlib.sdf.gengine;

import lightlib.sdf.gengine.plugin.BasePlugin;

public class Argument extends BasePlugin<Object[]> {

    protected Argument() {
    }

    @Override
    public String getName() {
        return "args";
    }

    @Override
    public void init(Option option) {
        // snapshot
        ContextSnapshot.getContext().log("init-argument", getContext());
    }

    protected void setData(Object[] data) {
        threadLocal.set(data);
    }

}
