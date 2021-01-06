package br.com.spring.rest.dev.superior.weekend.order.model;

import java.util.Objects;
import java.util.stream.Stream;

public enum OrderStatus {

    DEFAULT(0, "N/A"),

    PENDING(1, "Pending"),

    DELIVERED(2, "Delivered");

    private final int id;
    private final String description;

    OrderStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public long getIdToLong() {
        return this.id;
    }

    public static OrderStatus fromCodigo(int codigo) {
        return Stream.of(OrderStatus.values()).filter(t -> t.getId() == codigo).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static OrderStatus fromCodigo(long codigo) {
        return Stream.of(OrderStatus.values()).filter(t -> t.getId() == codigo).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static boolean isValorCodigoValidoEmListaValoresAoOrderStatus(int codigo) {
        return Stream.of(OrderStatus.values()).anyMatch(t -> t.getId() == codigo);
    }

    public boolean isPending() {
        return Objects.equals(getId(), OrderStatus.PENDING.ordinal());
    }

    public boolean isDelivered() {
        return Objects.equals(getId(), OrderStatus.DELIVERED.ordinal());
    }

}
