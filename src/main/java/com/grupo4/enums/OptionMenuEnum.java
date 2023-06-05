package com.grupo4.enums;

public enum OptionMenuEnum {
    STORE("Store", 0), PRODUCT("Product", 1), CLIENT("Client", 2), EXIT("SAIR", 3);

    private String name;
    private int value;

    private OptionMenuEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public static OptionMenuEnum getValue(int value) throws Exception {
        for (OptionMenuEnum option : OptionMenuEnum.values()) {
            if(option.getValue() == value) {
                return option;
            }
        }

        throw new Exception();
    }
}
