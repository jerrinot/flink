package org.apache.flink.connector.questdb;

import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.logical.LogicalType;
import org.apache.flink.table.types.logical.LogicalTypeRoot;
import org.apache.flink.table.types.logical.RowType;

import java.util.List;

/**
 *
 *
 */
public final class QuestDBDynamicTableSink implements DynamicTableSink {
    private final DataType physicalRowDataType;

    public QuestDBDynamicTableSink(DataType physicalRowDataType) {
        this.physicalRowDataType = physicalRowDataType;
    }

    @Override
    public ChangelogMode getChangelogMode(ChangelogMode requestedMode) {
        return ChangelogMode.insertOnly();
    }

    @Override
    public SinkRuntimeProvider getSinkRuntimeProvider(Context context) {
        List<DataType> children = physicalRowDataType.getChildren();
        final RowType rowType = (RowType) physicalRowDataType.getLogicalType();
        List<String> fieldNames = rowType.getFieldNames();

        for (DataType dataType : children) {
            LogicalType logicalType = dataType.getLogicalType();
            LogicalTypeRoot typeRoot = logicalType.getTypeRoot();
            switch (typeRoot) {
                case CHAR:
                    break;
                case VARCHAR:
                    break;
                case BOOLEAN:
                    break;
                case BINARY:
                    break;
                case VARBINARY:
                    break;
                case DECIMAL:
                    break;
                case TINYINT:
                    break;
                case SMALLINT:
                    break;
                case INTEGER:
                    break;
                case BIGINT:
                    break;
                case FLOAT:
                    break;
                case DOUBLE:
                    break;
                case DATE:
                    break;
                case TIME_WITHOUT_TIME_ZONE:
                    break;
                case TIMESTAMP_WITHOUT_TIME_ZONE:
                    break;
                case TIMESTAMP_WITH_TIME_ZONE:
                    break;
                case TIMESTAMP_WITH_LOCAL_TIME_ZONE:
                    break;
                case INTERVAL_YEAR_MONTH:
                    break;
                case INTERVAL_DAY_TIME:
                    break;
                case ARRAY:
                    break;
                case MULTISET:
                    break;
                case MAP:
                    break;
                case ROW:
                    break;
                case DISTINCT_TYPE:
                    break;
                case STRUCTURED_TYPE:
                    break;
                case NULL:
                    break;
                case RAW:
                    break;
                case SYMBOL:
                    break;
                case UNRESOLVED:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + logicalType);
            }
        }
        return new QuestDBSinkRuntimeProvider();
    }

    @Override
    public DynamicTableSink copy() {
        return this;
    }

    @Override
    public String asSummaryString() {
        return "questdb dynamic table sink";
    }
}
