package com.pfar.apibank.util;

import org.modelmapper.ModelMapper;

public class Mapper {
    private Mapper() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
