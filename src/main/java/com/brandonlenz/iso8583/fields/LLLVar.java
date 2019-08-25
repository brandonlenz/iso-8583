package com.brandonlenz.iso8583.fields;

import com.brandonlenz.generic.fields.VariableLengthIndicatedField;
import com.brandonlenz.generic.fields.VariableLengthIndicator;

public class LLLVar extends VariableLengthIndicatedField {
    public LLLVar(LLLVarDefinition definition, VariableLengthIndicator vli) {
        super(definition, vli);
    }
}
