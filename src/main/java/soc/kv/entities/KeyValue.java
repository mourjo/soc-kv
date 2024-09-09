package soc.kv.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jooq.Field;
import org.jooq.SelectFieldOrAsterisk;
import org.jooq.impl.DSL;

@NoArgsConstructor
@Getter
@Table(name = "key_values")
public class KeyValue {

    @Column(name = "id")
    long id;

    @Column(name = "k")
    String key;

    @Column(name = "v")
    String value;

    @Column(name = "created_at")
    OffsetDateTime createdAt;

    @Column(name = "updated_at")
    OffsetDateTime updatedAt;

    public static Field<Long> idField() {
        return DSL.field("id", Long.class);
    }

    public static Field<String> keyField() {
        return DSL.field("k", String.class);
    }

    public static Field<String> valueField() {
        return DSL.field("v", String.class);
    }

    public static Field<OffsetDateTime> updatedAtField() {
        return DSL.field("updated_at", OffsetDateTime.class);
    }

    public static Field<OffsetDateTime> createdAtField() {
        return DSL.field("created_at", OffsetDateTime.class);
    }

    public static org.jooq.Table<org.jooq.Record> table() {
        return DSL.table("key_values");
    }

    public static SelectFieldOrAsterisk[] asterisk() {
        return new SelectFieldOrAsterisk[]{
            idField(),
            keyField(),
            valueField(),
            createdAtField(),
            updatedAtField(),
        };
    }
}
