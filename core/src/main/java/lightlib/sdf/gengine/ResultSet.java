package lightlib.sdf.gengine;

import java.util.List;

public class ResultSet {
    private Object result;
    private ResultError error;
    private List<Object[]> snapshot;

    protected ResultSet() {
    }

    protected void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    protected void setError(Throwable t) {
        this.error = new ResultError(t);
    }

    public ResultError getError() {
        return error;
    }

    protected void setSnapshot(List<Object[]> snapshot) {
        this.snapshot = snapshot;
    }

    public List<Object[]> getSnapshot() {
        return snapshot;
    }

    class ResultError {
        private String message;
        private String type;
        private Object[] stackTrace;
        private Object[] causeTrace;

        private ResultError(Throwable t) {
            this.message = t.getMessage();
            this.stackTrace = convert(t.getStackTrace());
            this.type = t.getClass().getName();
            if (t.getCause() != null && !t.getCause().equals(t)) {
                this.causeTrace = convert(t.getCause().getStackTrace());
            }
        }

        protected Object[] convert(StackTraceElement[] stes) {
            if (stes != null && stes.length > 0) {
                StackTraceElement ste = stes[0];
                return new Object[]{ste.getClassName(), ste.getMethodName(), ste.getLineNumber(), ste.getFileName()};
            }
            return null;
        }

        public String getType() {
            return type;
        }

        public Object[] getCauseTrace() {
            return causeTrace;
        }

        public Object[] getStackTrace() {
            return stackTrace;
        }

        public String getMessage() {
            return message;
        }

    }
}