package org.apache.flink.connector.questdb;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.util.TestLoggerExtension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import static org.apache.flink.table.api.Expressions.row;

@ExtendWith(TestLoggerExtension.class)
public class QuestDBDynamicSinkFactoryTest {

    @Test
    public void testSmoke() throws ExecutionException, InterruptedException {
        TableEnvironment tableEnvironment =
                TableEnvironment.create(EnvironmentSettings.inStreamingMode());

        tableEnvironment.executeSql(
                "CREATE TABLE questTable ("
                        + "a BIGINT NOT NULL,\n"
                        + "b TIMESTAMP,\n"
                        + "c STRING NOT NULL,\n"
                        + "d FLOAT,\n"
                        + "e TINYINT NOT NULL,\n"
                        + "f DATE,\n"
                        + "g TIMESTAMP NOT NULL,"
                        + "h as a + 2\n"
                        + ")\n"
                        + "WITH (\n"
                        + String.format("'%s'='%s',\n", "connector", "questdb")
                        + String.format("'%s'='%s',\n", "table", "flink_table")
                        + ")");

        tableEnvironment
                .fromValues(
                        row(
                                1L,
                                LocalDateTime.of(2022, 6, 1, 10, 10, 10, 10_000),
                                "ABCDE",
                                12.12f,
                                (byte) 2,
                                LocalDate.ofEpochDay(12345),
                                LocalDateTime.parse("2012-12-12T12:12:12")))
                .executeInsert("questTable")
                .await();
    }

}
