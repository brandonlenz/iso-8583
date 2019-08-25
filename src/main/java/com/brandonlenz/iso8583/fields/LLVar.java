package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.fields.VariableLengthIndicator;
import com.brandonlenz.generic.fields.VariableLengthIndicatedField;

public final class LLVar extends VariableLengthIndicatedField {

    public LLVar(LLVarDefinition definition, VariableLengthIndicator vli) {
        super(definition, vli);
    }

}
