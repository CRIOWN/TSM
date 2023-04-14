package com.criown.utils;

public enum CityEnum {
    NANJING(0, "南京市"),
    SUZHOU(1, "苏州市"),
    WUXI(2, "无锡市"),
    CHANGZHOU(3, "常州市"),
    NANTONG(4, "南通市"),
    YANGZHOU(5, "扬州市"),
    ZHENJIANG(6, "镇江市"),
    XUZHOU(7, "徐州市"),
    HUAIAN(8, "淮安市"),
    YANCHENG(9, "盐城市"),
    LIANGYUNGANG(10,"连云港"),
    TAIZHOU(11,"泰州市"),
    QIANSU(12,"宿迁市");

    private final int value;
    private final String name;

    CityEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        for (CityEnum cityEnum : CityEnum.values()) {
            if (cityEnum.getValue() == value) {
                return cityEnum.getName();
            }
        }
        return null;
    }

}
