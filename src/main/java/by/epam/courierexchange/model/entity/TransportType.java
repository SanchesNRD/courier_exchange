package by.epam.courierexchange.model.entity;

import java.util.Arrays;

public enum TransportType{
    BIKE(1), CAR(2), TRUCK(3);

    private int id;

    TransportType(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }


    public static TransportType parseType(short id) {
        return Arrays.stream(TransportType.values())
                .filter(status -> status.id == id)
                .findFirst()
                .orElse(BIKE);
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
