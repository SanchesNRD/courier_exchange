package by.epam.courierexchange.model.entity;

import java.util.Arrays;

public enum ProductType{
    DEFAULT(1,1),
    FRAGILE(2,5),
    EXPLOSIVE(3,10),
    POISONOUS(4,10);

    private int id;
    private int multiplier;

    ProductType(int id, int multiplier){
        this.id = id;
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getId() {
        return id;
    }


    public static ProductType parseType(short id) {
        return Arrays.stream(ProductType.values())
                .filter(status -> status.id == id)
                .findFirst()
                .orElse(DEFAULT);
    }
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
