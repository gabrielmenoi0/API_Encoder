package com.urlEncoder.builder.domain;
import java.util.function.Supplier;

public class Patter<T> {
    private final Supplier<T> supplier;

    private Patter(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        return supplier.get();
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        private Supplier<T> supplier;

        private Builder() {
        }

        public Builder<T> fromFunction(Supplier<T> supplier) {
            this.supplier = supplier;
            return this;
        }

        public Patter<T> build() {
            if (supplier == null) {
                throw new IllegalStateException("A função deve ser definida");
            }
            return new Patter<>(supplier);
        }
    }
}
