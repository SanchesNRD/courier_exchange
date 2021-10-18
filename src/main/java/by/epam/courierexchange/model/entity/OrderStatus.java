package by.epam.courierexchange.model.entity;

import java.util.Arrays;

public enum OrderStatus {
    AGREED((short)1), DELIVERED((short)2), COMPLETED((short)3);

    private final short id;

    OrderStatus(short id){
        this.id=id;
    }

    public short getStatusId() {
        return id;
    }


    public static OrderStatus parseStatus(short statusId) {
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status.id == statusId)
                .findFirst()
                .orElse(AGREED);
    }
}
