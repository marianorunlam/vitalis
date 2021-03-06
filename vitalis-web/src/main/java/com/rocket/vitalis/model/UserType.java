package com.rocket.vitalis.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Sebastian on 25/9/2016.
 */

@RequiredArgsConstructor
public enum UserType {

    NORMAL("normal"), SUPERVISOR("supervisor");

    @NonNull
    String userType;

    @Override
    @JsonValue
    public String toString() {
        return userType;
    }

}
