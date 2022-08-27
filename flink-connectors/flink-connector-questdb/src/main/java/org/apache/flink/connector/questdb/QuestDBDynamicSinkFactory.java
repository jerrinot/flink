package org.apache.flink.connector.questdb;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.factories.DynamicTableSinkFactory;
import org.apache.flink.table.types.DataType;

import java.util.Collections;
import java.util.Set;

/**
 *
 *
 */
public final class QuestDBDynamicSinkFactory implements DynamicTableSinkFactory {
    private static final String FACTORY_IDENTIFIER = "questdb";

    public QuestDBDynamicSinkFactory() {
        System.out.println("new instance");
    }

    @Override
    public DynamicTableSink createDynamicTableSink(Context context) {
        DataType physicalRowDataType = context.getPhysicalRowDataType();
        return new QuestDBDynamicTableSink(physicalRowDataType);
    }

    @Override
    public String factoryIdentifier() {
        return FACTORY_IDENTIFIER;
    }

    @Override
    public Set<ConfigOption<?>> requiredOptions() {
        return Collections.emptySet();
    }

    @Override
    public Set<ConfigOption<?>> optionalOptions() {
        return Collections.emptySet();
    }
}
