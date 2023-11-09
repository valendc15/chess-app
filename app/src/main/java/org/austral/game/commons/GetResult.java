package org.austral.game.commons;

import java.util.Optional;

public class GetResult<T, R> {
    private final Optional<T> succesValue;

    private final R errorValue;

    public GetResult(Optional<T> succesValue, R errorValue) {
        this.succesValue = succesValue;
        this.errorValue = errorValue;
    }

    public Optional<T> getSuccesValue() {
        return succesValue;
    }

    public R getErrorValue() {
        return errorValue;
    }


}
