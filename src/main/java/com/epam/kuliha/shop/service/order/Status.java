package com.epam.kuliha.shop.service.order;

import java.util.Arrays;

public enum Status {
    ACCEPTED(1),
    CONFIRMED(2),
    PROCESSING(3),
    SENT(4),
    COMPLETED(5),
    CANCELED(5);

    private final int id;

    Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Status getStatus(int id){
        return Arrays.stream(values()).filter(x -> x.id == id).findFirst().orElse(CANCELED);
    }
}
