package com.rocket.vitalis.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Sebastian on 25/9/2016.
 */

@RequiredArgsConstructor
public enum EventStatus {

    OPEN("open"), CLOSED("closed"), WATCHED("watched");

    @NonNull
    String eventStatus;
}
