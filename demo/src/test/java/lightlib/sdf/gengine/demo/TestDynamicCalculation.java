package lightlib.sdf.gengine.demo;

import com.alibaba.fastjson.JSON;
import lightlib.sdf.dataset.DataSet;
import lightlib.sdf.gengine.GEngine;
import lightlib.sdf.gengine.Option;
import lightlib.sdf.gengine.ResultSet;
import lightlib.sdf.gengine.plugin.Console;
import lightlib.sdf.gengine.plugin.DynamicDataSet;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestDynamicCalculation {

    @Test
    public void testInvokeDataSet() {
        //注册数据源
        DataSet.register(new EmployeeService());

        Assert.assertTrue("未注册数据源:emp", DataSet.isRegistered("emp"));

        Option option = new Option();
        option.put(Console.iLogType, 2);

        //配置数据源
        Set<String> dsSet = new HashSet<>();
        dsSet.add("emp");
        option.put(DynamicDataSet.DataSourceOption, dsSet);

        String script = "console.info('log arg:%s',args[0]) \n def emp = ds.invoke('emp',args[0]) \n def ts = emp.level*emp.salary \n if(emp.age>25){ts*=1.1}else{ts*=0.9} \n return ts";
        ResultSet rs = GEngine.INSTANCE.exec(script, option, 123);

        Assert.assertNotNull(rs);
        System.out.println(JSON.toJSONString(rs));

        script = "console.info('log arg:%s',args[0]) \n def emp = ds.invoke('emp',args[0]) \n def ts = emp.level*emp.salary \n if(emp.age>25){ts*=1.1}else{ts*=0.9} \n return ts";
        rs = GEngine.INSTANCE.exec(script, option, "123");

        Assert.assertNotNull(rs);
        System.out.println(JSON.toJSONString(rs));
    }
}
