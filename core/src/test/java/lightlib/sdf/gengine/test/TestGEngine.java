package lightlib.sdf.gengine.test;

import com.alibaba.fastjson.JSON;
import lightlib.sdf.gengine.GEngine;
import lightlib.sdf.gengine.GEngineException;
import lightlib.sdf.gengine.Option;
import lightlib.sdf.gengine.ResultSet;
import lightlib.sdf.gengine.plugin.Console;
import org.junit.Assert;
import org.junit.Test;

public class TestGEngine {

    @Test
    public void testVarBinding() {
        String script = "console.info('log arg:%s',args[0]) \n console.logInfo('Now:'+System.currentTimeMillis()) \n return 123L";

        Option option = new Option();
        option.put(Console.iLogType, 1);

        ResultSet rs = GEngine.INSTANCE.exec(script, option, "TEST Var");

        Assert.assertNotNull(rs);

        System.out.println(JSON.toJSONString(rs));
    }
}
