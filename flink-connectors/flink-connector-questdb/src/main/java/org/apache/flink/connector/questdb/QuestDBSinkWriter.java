package org.apache.flink.connector.questdb;

import io.questdb.client.Sender;

import org.apache.flink.api.connector.sink2.SinkWriter;
import org.apache.flink.table.data.RowData;

import java.io.IOException;

public class QuestDBSinkWriter implements SinkWriter<RowData> {
    private final Sender sender;

    public QuestDBSinkWriter(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void write(RowData element, Context context) throws IOException, InterruptedException {
        System.out.println("writing " + element);
    }

    @Override
    public void flush(boolean endOfInput) throws IOException, InterruptedException {
        System.out.println("flushing, end-of-input: " + endOfInput);
    }

    @Override
    public void close() throws Exception {
        sender.close();
    }
}
