package br.com.spring.rest.dev.superior.weekend;

import br.com.spring.rest.dev.superior.weekend.config.DBConfig;
import br.com.spring.rest.dev.superior.weekend.order.model.Order;
import br.com.spring.rest.dev.superior.weekend.order.model.OrderStatus;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class AppMain {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConfig.getConnection();
        Statement st = connection.createStatement();

        List<Product> productList = getListProduct(st);
        productList.forEach(System.out::println);

        List<Order> orderList = getListOrder(st);
        orderList.forEach(System.out::println);

        List<Order> orderListComplete = getListOrderWithProducts(st);
        orderListComplete.forEach(System.out::println);

        DBConfig.closeStatement(st);
        connection.close();
    }

    private static List<Product> getListProduct(Statement st) throws SQLException {
        List<Product> productList = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from dev_pedidos.tb_product");

        while (rs.next()) {
            productList.add(instanceProductFrom(rs));
        }

        return productList;
    }

    private static Product instanceProductFrom(ResultSet rs) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getBigDecimal("price"))
                .imageUri(rs.getString("image_uri"))
                .build();
    }

    private static List<Order> getListOrderWithProducts(Statement st) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("     SELECT *                                                                             ");
        stringBuilder.append("       FROM dev_pedidos.tb_order                                                          ");
        stringBuilder.append(" INNER JOIN dev_pedidos.tb_order_product ON (tb_order.id = tb_order_product.order_id)     ");
        stringBuilder.append(" INNER JOIN dev_pedidos.tb_product       ON (tb_product.id = tb_order_product.product_id) ");

        ResultSet rs = st.executeQuery(stringBuilder.toString());

        Map<Long, Order> orderMap = new HashMap<>();
        Map<Long, Product> productMap = new HashMap<>();

        while (rs.next()) {
            orderMap = getMapListOrdersFrom(rs, orderMap, productMap);
        }

        return orderMap.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static Map<Long, Order> getMapListOrdersFrom(ResultSet rs,
                                                         Map<Long, Order> orderMap,
                                                         Map<Long, Product> productMap) throws SQLException {
        Long idOrder = rs.getLong("order_id");

        if (Objects.isNull(orderMap.get(idOrder))) {
            Order order = instanceOrderFrom(rs, "order_id");
            order.instanceListProducts();
            orderMap.put(idOrder, order);
        }

        Long idProduct = rs.getLong("product_id");

        if (Objects.isNull(productMap.get(idProduct))) {
            Product product = instanceProductFrom(rs);
            productMap.put(idProduct, product);
        }

        orderMap.get(idOrder).getProducts().add(productMap.get(idProduct));
        return orderMap;
    }

    private static List<Order> getListOrder(Statement st) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        ResultSet rs = st.executeQuery("select * from dev_pedidos.tb_order");

        while (rs.next()) {
            orderList.add(instanceOrderFrom(rs, "id"));
        }

        return orderList;
    }

    private static Order instanceOrderFrom(ResultSet rs, String colunaId) throws SQLException {
        return Order.builder()
                .id(rs.getLong(colunaId))
                .latitude(rs.getBigDecimal("latitude"))
                .longitude(rs.getBigDecimal("longitude"))
                .moment(rs.getTimestamp("moment").toInstant())
                .orderStatus(OrderStatus.fromCodigo(rs.getInt("status")))
                .build();
    }
}
