package net.modificationstation.stationapi.api.lookup;

import net.mine_diver.unsafeevents.Event;

public abstract class ApiLookupEvent<T> extends Event {
    public final Class<?> apiClass;
    private final T defaultValue;

    private T foundObject;

    public ApiLookupEvent(
        Class<?> apiClass,
        T defaultValue
    ) {
        this.apiClass = apiClass;
        this.defaultValue = defaultValue;
    }

    protected void found(T foundObject) {
        this.foundObject = foundObject;
        if (this.isCancelable()) {
            this.cancel();
        }
    }

    public T getFoundObject() {
        if (apiClass.isInstance(this.foundObject)) {
            return foundObject;
        } else {
            return defaultValue;
        }
    }

}
