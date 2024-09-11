package soc.kv.web.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import soc.kv.common.Database;
import soc.kv.entities.KeyValueLog;

public class HistoryRepository {

    @SneakyThrows
    public List<KeyValueLog> readLog(String key) {
        try (Connection conn = Database.getConnection()) {
            Map.of()
            return DSL.using(conn, SQLDialect.POSTGRES)
                .select(KeyValueLog.asterisk())
                .from(KeyValueLog.table())
                .where(KeyValueLog.keyField().eq(key))
                .fetchInto(KeyValueLog.class);
        }
    }

    @SneakyThrows
    public void appendLog(String key, String value) {
        try (Connection conn = Database.getConnection()) {
            DSL.using(conn, SQLDialect.POSTGRES)
                .insertInto(KeyValueLog.table())
                .columns(KeyValueLog.keyField(), KeyValueLog.valueField())
                .values(key, value)
                .execute();
        }
    }
}
