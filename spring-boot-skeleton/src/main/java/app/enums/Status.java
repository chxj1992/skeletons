package app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    NORMAL(1),
    DISABLED(2);

    private Integer value;

    Status(Integer value) {
        this.value = value;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static Status create(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        for (Status v : values()) {
            if (value.equals(v.getValue().toString())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }


}
