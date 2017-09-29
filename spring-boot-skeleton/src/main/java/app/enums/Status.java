package app.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

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

    public static Optional<Status> parse(Integer value) {
        for (Status status : Status.values()) {
            if (status.getValue().equals(value)) {
                return  Optional.of(status);
            }
        }
        return Optional.empty();
    }

}
