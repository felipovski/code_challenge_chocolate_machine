package com.chococompany.entity;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;

public enum ChocolateType {
    A (80),
    B(85),
    C(90);

    private final int percentage;

    ChocolateType(int percentage){
        this.percentage = percentage;
    }

    public int getPercentage(){
        return percentage;
    }

    public static ChocolateType findByStrType(String typeStr) {
        return Arrays.stream(ChocolateType.values())
                .filter(type -> type.toString().equals(typeStr))
                .findFirst()
                .orElseThrow();
    }
}
