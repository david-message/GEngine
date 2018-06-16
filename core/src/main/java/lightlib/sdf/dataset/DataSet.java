package lightlib.sdf.dataset;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DataSet {
    private static final ConcurrentMap<String, IData> dataMap = new ConcurrentHashMap<>();

    private DataSet() {
    }

    public static boolean register(IData iData) {
        dataMap.putIfAbsent(iData.getName(), iData);
        return iData.equals(dataMap.get(iData.getName()));
    }

    public static boolean isRegistered(String name) {
        return dataMap.containsKey(name);
    }

    public static void unregister(String name) {
        dataMap.remove(name);
    }

    public static IData get(String name) {
        return dataMap.get(name);
    }

    //TODO heartbeat check: ping
}
