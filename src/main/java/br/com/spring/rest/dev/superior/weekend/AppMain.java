package br.com.spring.rest.dev.superior.weekend;

import br.com.spring.rest.dev.superior.weekend.config.DBConfig;
import br.com.spring.rest.dev.superior.weekend.order.model.Order;
import br.com.spring.rest.dev.superior.weekend.product.model.Product;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class AppMain {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConfig.getConnection();

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from dev_pedidos.tb_product");

        while (rs.next()) {
            log.info("{}", instanceProductFrom(rs).toStringProduct());
        }

        DBConfig.closeStatement(st);
        connection.close();
    }

    private static Product instanceProductFrom(ResultSet rs) throws SQLException {
        return Product.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getBigDecimal("price"))
                .build();
    }

    private static Order instanceOrderFrom(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getLong("id"))
                .latitude(rs.getBigDecimal("latitude"))
                .longitude(rs.getBigDecimal("longitude"))
                .build();
    }
}
