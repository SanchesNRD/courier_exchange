package by.epam.courierexchange.model.entity;

import java.util.Arrays;

public enum UserStatus{
    CONFIRMED(1),
    NON_CONFIRMED(2),
    BANED(3),
    COURIER_CONFIRMED(4),
    ADMIN(5);

    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private final int statusId;

    UserStatus(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }


    public static UserStatus parseStatus(short statusId) {
        return Arrays.stream(UserStatus.values())
                .filter(status -> status.statusId == statusId)
                .findFirst()
                .orElse(NON_CONFIRMED);
    }

    @Override
    public String toString() {
        return this.name().toLowerCase()
                .replaceAll(UNDERSCORE, HYPHEN);
    }
}
