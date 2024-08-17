package com.ponsun.san.common.entity;

public enum Status {
    ACTIVE("A","Active"), DELETE("D", "DELETED");

    private final String code;

    private final String label;

    Status(final String code, final String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static String getCodeByStatus(final Status status) {
        String code = Status.ACTIVE.getCode();
        switch (status) {
            case ACTIVE:
                code = Status.ACTIVE.getCode();
                break;
            case DELETE:
                code = Status.DELETE.getCode();
                break;
        }
        return code;
    }
}
