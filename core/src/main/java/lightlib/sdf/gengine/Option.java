package lightlib.sdf.gengine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhangwei<br>
 * e-mail:david.message@gmail.com<br>
 * Create:2016-4-10
 */
public class Option {
    private final Map<String, Object> confMap = new HashMap<>();

    public Option() {
        this(null);
    }

    public Option(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            confMap.putAll(map);
        }
    }

    public Object put(String key, Object value) {
        return confMap.put(key, value);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        Object v = confMap.get(name);
        if (v != null && v instanceof Boolean) {
            return (Boolean) v;
        }
        return defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        Object v = confMap.get(name);
        if (v != null && v instanceof Number) {
            return ((Number) v).doubleValue();
        }
        return defaultValue;
    }

    public float getFloat(String name, float defaultValue) {
        Object v = confMap.get(name);
        if (v != null && v instanceof Number) {
            return ((Number) v).floatValue();
        }
        return defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        Object v = confMap.get(name);
        if (v != null && v instanceof Number) {
            return ((Number) v).intValue();
        }
        return defaultValue;
    }

    public long getLong(String name, long defaultValue) {
        Object v = confMap.get(name);
        if (v != null && v instanceof Number) {
            return ((Number) v).longValue();
        }
        return defaultValue;
    }

    public String getString(String name) {
        Object v = confMap.get(name);
        if (v != null) {
            if (v instanceof String)
                return (String) v;
            else {
                v.toString();
            }
        }
        return null;
    }

    public String getString(String name, String defaultValue) {
        String value = getString(name);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public Object get(String name) {
        return confMap.get(name);
    }

    public boolean contains(String name) {
        return confMap.containsKey(name);
    }
}
