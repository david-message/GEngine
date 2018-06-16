package lightlib.sdf.gengine.plugin;

import com.alibaba.fastjson.JSON;
import lightlib.sdf.dataset.DataSet;
import lightlib.sdf.dataset.IData;
import lightlib.sdf.gengine.ContextSnapshot;
import lightlib.sdf.gengine.GEngineException;
import lightlib.sdf.gengine.Option;

import java.util.Set;

public class DynamicDataSet extends BasePlugin<Object> {

    public static final String DataSourceOption = "DSOption";

    @Override
    public String getName() {
        return "ds";
    }

    /**
     * 初始化可访问的dataset
     *
     * @param option
     * @throws GEngineException
     */
    @Override
    public void init(Option option) throws GEngineException {
        Object o = option.get(DataSourceOption);
        if (o == null || !(o instanceof Set)) {
            ContextSnapshot.getContext().log("DataSet init error:option undefined.");
            throw new GEngineException("Not found DataSet Option");
        }
        ContextSnapshot.getContext().log("dataset.option", o);
        threadLocal.set(new InvokeDataSet((Set<String>) o));
    }

}

class InvokeDataSet {
    private Set<String> dsSet;

    protected InvokeDataSet(Set<String> dsSet) {
        this.dsSet = dsSet;
    }

    public Object invoke(String dsName, Object... args) throws GEngineException {
        if (!dsSet.contains(dsName)) {
            String reason = "Forbidden access dataset:" + dsName;
            ContextSnapshot.getContext().log(reason);
            throw new GEngineException(reason);
        }

        IData iData = DataSet.get(dsName);
        if (iData == null) {
            String reason = "Not found dataset:" + dsName;
            ContextSnapshot.getContext().log(reason);
            throw new GEngineException(reason);
        }

        if (!iData.precheckArgument(args)) {
            String reason = "dataset:" + dsName + ",precheck fail. argument:" + (args == null ? "null" : JSON.toJSONString(args));
            ContextSnapshot.getContext().log(reason);
            throw new GEngineException(reason);
        }

        Object rt;
        try {
            rt = iData.getData(args);
        } catch (Throwable t) {
            ContextSnapshot.getContext().log("dataset:" + dsName //
                    + ", argument:" + (args == null ? "null" : JSON.toJSONString(args)) //
                    + ", error:" + t.getMessage());
            throw new GEngineException(t);
        }

        ContextSnapshot.getContext().log("dataset.invoke", new Object[]{dsName, args, rt});
        return rt;
    }

}
