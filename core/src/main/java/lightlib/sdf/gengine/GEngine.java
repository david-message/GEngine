package lightlib.sdf.gengine;

import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import lightlib.sdf.gengine.plugin.Console;
import lightlib.sdf.gengine.plugin.DynamicDataSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-4-10
 */
public class GEngine {
    private final GroovyClassLoader clzLoader;
    private final Set<Plugin<?>> plugins = new HashSet<>();

    private final Argument args = new Argument();
    private final EnvBinding binding;

    public static final GEngine INSTANCE = new GEngine();

    private GEngine() {
        clzLoader = new GroovyClassLoader();
        clzLoader.setShouldRecompile(true);
        binding = new EnvBinding();

        init();
    }

    protected void init() {
        // init binding
        plugins.add(args);
        plugins.add(new Console());
        plugins.add(new DynamicDataSet());

        binding.initPlugin(plugins);
    }

    public ResultSet exec(String scriptCode, Option option, Object... args) {
        this.args.setData(args);
        ResultSet rs = new ResultSet();
        try {
            for (Plugin<?> plugin : plugins) {//init plugin
                plugin.init(option);
            }

            Class<Script> clz = clzLoader.parseClass(scriptCode);//
            Script script = clz.newInstance();
            script.setBinding(binding);

            //save result
            Object rt = script.run();
            rs.setResult(rt);
        } catch (Throwable t) {
            rs.setError(t);
        } finally {//detroy
            rs.setSnapshot(ContextSnapshot.getContext().getCsList());

            for (Plugin<?> plugin : plugins) {
                plugin.destroy(option);
            }
            ContextSnapshot.destroy();
            clzLoader.clearCache();
        }

        return rs;
    }
}
