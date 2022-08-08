package hexlet.code;

import java.util.Objects;

public class Diff {
    private String key;
    private Object oldValue;
    private Object newValue;

    private Status status;

    public Diff(String key, Object oldValue, Object newValue, Status status) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    public Status getStatus() {
        return status;
    }
}
