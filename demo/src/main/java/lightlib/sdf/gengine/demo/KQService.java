package lightlib.sdf.gengine.demo;

import lightlib.sdf.dataset.IData;

import java.util.HashMap;
import java.util.Map;

public class KQService implements IData {
    @Override
    public String getName() {
        return "kq";
    }

    @Override
    public boolean precheckArgument(Object... args) {
        return args != null && args.length == 1 && args[0] instanceof Number;
    }

    @Override
    public Object getData(Object... args) {
        Map<String, Object> emp = new HashMap<>();
        emp.put("id", args[0]);
        emp.put("name", "WhoIam");
        emp.put("补卡", 28);
        emp.put("加班", 208);
        emp.put("请假", 108);
        emp.put("createTime", System.currentTimeMillis());
        return emp;
    }

    @Override
    public boolean ping() {
        return true;
    }
}
