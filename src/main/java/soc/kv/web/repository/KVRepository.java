package soc.kv.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.SneakyThrows;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import soc.kv.common.Environment;
import soc.kv.entities.KeyValue;

public class KVRepository {

    @SneakyThrows
    private Connection getConnection() {
        String host = Environment.getPostgresHost();
        String port = Environment.getPostgresPort();
        String database = Environment.getPostgresDatabase();
        String username = Environment.getPostgresUser();
        String connectionString = "jdbc:postgresql://%s:%s/%s".formatted(host, port, database);
        return DriverManager.getConnection(connectionString, username, null);
    }

    @SneakyThrows
    public void upsert(String key, String value) {
        try (Connection conn = getConnection()) {
            DSL.using(conn, SQLDialect.POSTGRES)
                .insertInto(KeyValue.table())
                .columns(KeyValue.keyField(), KeyValue.valueField())
                .values(key, value)
                .onConflict(KeyValue.keyField())
                .doUpdate()
                .set(KeyValue.valueField(), value)
                .execute();
        }
    }

    @SneakyThrows
    public KeyValue fetch(String key) {
        try (Connection conn = getConnection()) {
            return DSL.using(conn, SQLDialect.POSTGRES)
                .select(KeyValue.asterisk())
                .from(KeyValue.table())
                .where(KeyValue.keyField().eq(key))
                .fetchAnyInto(KeyValue.class);
        }
    }

}
