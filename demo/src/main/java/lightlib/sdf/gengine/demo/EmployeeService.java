package lightlib.sdf.gengine.demo;

import lightlib.sdf.dataset.IData;

import java.util.HashMap;
import java.util.Map;

public class EmployeeService implements IData {
    @Override
    public String getName() {
        return "emp";
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
        emp.put("age", 28);
        emp.put("level", 8);
        emp.put("salary", 18000);
        emp.put("memo", "If not now,when?if not me,who?");
        emp.put("createTime", System.currentTimeMillis());
        return emp;
    }

    @Override
    public boolean ping() {
        return true;
    }
}
