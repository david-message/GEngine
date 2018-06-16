package lightlib.sdf.gengine.plugin;

import lightlib.sdf.gengine.ContextSnapshot;
import lightlib.sdf.gengine.Option;

public class Console extends BasePlugin<Log> {

    public static final String iLogType = "Console.logType";

    @Override
    public String getName() {
        return "console";
    }

    @Override
    public void init(Option option) {
        threadLocal.set(new Log(option.getInt(iLogType, 0)));
    }

}

class Log {

    final int logType;

    protected Log(int type) {
        logType = type;
    }

    public void logDebug(String message) {
        out("Debug:" + message);
    }

    public void debug(String format, Object... args) {
        String msg = String.format(format, args);
        logDebug(msg);
    }

    public void logInfo(String message) {
        out("Info:" + message);
    }

    public void info(String format, Object... args) {
        String msg = String.format(format, args);
        logInfo(msg);
    }

    public void logWarn(String message) {
        out("Warn:" + message);
    }

    public void warn(String format, Object... args) {
        String msg = String.format(format, args);
        logWarn(msg);
    }

    public void logError(String message) {
        out("Error:" + message);
    }

    public void error(String format, Object... args) {
        String msg = String.format(format, args);
        logError(msg);
    }

    protected void out(String message) {
        //TODO 根据类型分：调试日志、命令行日志、文件日志...
        switch (logType) {
            case 0:
                System.out.println(message);
                break;
            case 1:
                System.err.println(message);
                break;
            case 2:
                ContextSnapshot.getContext().log(message);
                break;
        }
    }
}
