package com.klarna.org.apache.flink.api.java.io.jdbc.dialect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostgresDialectTest {

    @Test
    public void whenSchemaExistsShouldQuoteCorrectly() {
        JDBCDialects.PostgresDialect postgresDialect = new JDBCDialects.PostgresDialect();
        String identifier = "schema.table";
        String quotedIdentifier = postgresDialect.quoteIdentifier(identifier);
        assertEquals("\"schema\".\"table\"", quotedIdentifier);
    }

    @Test
    public void whenOnlyTableNameShouldQuoteCorrectly() {
        JDBCDialects.PostgresDialect postgresDialect = new JDBCDialects.PostgresDialect();
        String identifier = "table";
        String quotedIdentifier = postgresDialect.quoteIdentifier(identifier);
        assertEquals("\"table\"", quotedIdentifier);
    }
}
