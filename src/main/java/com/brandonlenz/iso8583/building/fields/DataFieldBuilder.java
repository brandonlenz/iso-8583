package com.brandonlenz.iso8583.building.fields;

import com.brandonlenz.iso8583.fields.DataField;
import java.util.function.Supplier;

public class DataFieldBuilder<F extends DataField> {

    final Supplier<F> supplier;

    public DataFieldBuilder(Supplier<F> supplier) {
        this.supplier = supplier;
    }

    public F build() {
        return supplier.get();
    }

    public F build(byte[] rawData) {
        F dataField = supplier.get();
        dataField.setRawData(rawData);
        return dataField;
    }

    public F build(String data) {
        F dataField = supplier.get();
        byte[] rawData = dataField.getDefinition().getEncoding().encode(data);
        dataField.setRawData(rawData);
        return dataField;
    }
}
