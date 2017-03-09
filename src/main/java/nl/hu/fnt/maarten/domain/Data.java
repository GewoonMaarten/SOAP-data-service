package nl.hu.fnt.maarten.domain;

public class Data {
    private int dataId;
    private Object value;
    private User user;

    public Data(int dataId, Object value, User user) {
        this.dataId = dataId;
        this.value = value;
        this.user = user;
    }

    public Data(Object value, User user) {
        this.value = value;
        this.user = user;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
