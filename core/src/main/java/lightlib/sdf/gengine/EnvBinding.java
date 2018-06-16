package lightlib.sdf.gengine;

import java.util.Set;

import org.codehaus.groovy.control.io.NullWriter;

import groovy.lang.Binding;

/**
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-4-10
 */
public class EnvBinding extends Binding {

    public EnvBinding() {
        super();

        //@see Console plugin
//		setVariable("out", NullWriter.DEFAULT);
//      setVariable("out", System.out);
    }

    /**
     *
     */
    @Override
    public Object getVariable(String name) {
        Object obj = super.getVariable(name);
        if (obj != null && obj instanceof Plugin<?>) {
            return ((Plugin<?>) obj).getContext();
        }
        return obj;
    }

    /**
     * @param plugins
     */
    protected void initPlugin(Set<Plugin<?>> plugins) {
        for (Plugin<?> plugin : plugins) {
            setVariable(plugin.getName(), plugin);
        }
    }
}
