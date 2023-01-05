package com.parcel.cost.constant;

public enum OrdinalConstant {
    TRUE(true, true),
    PARTIAL_TRUE(false, true),
    FALSE(false, false);
    private final Boolean condition1;
    private final Boolean condition2;

    OrdinalConstant(Boolean condition1, Boolean condition2) {
        this.condition1 = condition1;
        this.condition2 = condition2;
    }

    public static OrdinalConstant valueOf(Boolean condition1, Boolean condition2) {
        for (OrdinalConstant category : values()) {
            if (category.condition1.equals(condition1) && category.condition2.equals(condition2)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Input");
    }
}
