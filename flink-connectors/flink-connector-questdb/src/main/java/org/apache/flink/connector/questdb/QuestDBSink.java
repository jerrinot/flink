package org.apache.flink.connector.questdb;

import io.questdb.client.Sender;

import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.api.connector.sink2.SinkWriter;
import org.apache.flink.table.data.RowData;

import java.io.IOException;

public class QuestDBSink implements Sink<RowData> {

    @Override
    public SinkWriter<RowData> createWriter(InitContext context) throws IOException {
        Sender sender = Sender.builder().address("localhost").build();
        return new QuestDBSinkWriter(sender);
    }
}
