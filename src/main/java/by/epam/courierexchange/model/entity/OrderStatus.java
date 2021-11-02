package by.epam.courierexchange.model.entity;

import java.util.Arrays;

public enum OrderStatus {
    AGREED(1), DELIVERED(2), COMPLETED(3);

    private final int id;

    OrderStatus(int id){
        this.id=id;
    }

    public int getStatusId() {
        return id;
    }


    public static OrderStatus parseStatus(int statusId) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.id == statusId)
                .findFirst()
                .orElse(AGREED);
    }
}
