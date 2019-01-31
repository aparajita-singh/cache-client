package model;

import java.io.Serializable;

public class Data implements Serializable {
    private Object data;

    public Data(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
