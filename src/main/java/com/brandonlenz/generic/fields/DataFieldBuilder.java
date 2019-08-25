package com.brandonlenz.generic.fields;

public interface DataFieldBuilder<F extends DataField> {

    F build(byte[] rawData);

    F build(String data);
}
