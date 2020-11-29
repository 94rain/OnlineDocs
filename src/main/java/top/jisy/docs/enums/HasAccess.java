package top.jisy.docs.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HasAccess {

    Y("Y"),
    N("N");

    private String hasAccessString;

    private HasAccess(String hasAccessString) {
        this.hasAccessString = hasAccessString;
    }

    public String getHasAccessString() {
        return this.hasAccessString;
    }

    @JsonValue
    public boolean hasAccess() {
        return this.equals(Y);
    }
}
