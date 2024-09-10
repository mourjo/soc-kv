package soc.kv.web.repository;

import static soc.kv.common.Database.getConnection;

import java.sql.Connection;
import lombok.SneakyThrows;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import soc.kv.common.Database;
import soc.kv.entities.KeyValue;

public class KVRepository {

    @SneakyThrows
    public void upsert(String key, String value) {
        try (Connection conn = Database.getConnection()) {
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
        try (Connection conn = Database.getConnection()) {
            return DSL.using(conn, SQLDialect.POSTGRES)
                .select(KeyValue.asterisk())
                .from(KeyValue.table())
                .where(KeyValue.keyField().eq(key))
                .fetchAnyInto(KeyValue.class);
        }
    }

}
