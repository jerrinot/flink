package org.apache.flink.connector.questdb;

import org.apache.flink.api.connector.sink2.Sink;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.sink.SinkV2Provider;
import org.apache.flink.table.data.RowData;

/**
 *
 *
 */
public final class QuestDBSinkRuntimeProvider implements SinkV2Provider {
    @Override
    public Sink<RowData> createSink() {
        return new QuestDBSink();
    }
}
