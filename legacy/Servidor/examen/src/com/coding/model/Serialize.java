package com.coding.model;

public interface Serialize {
    void formJson(String xml);
    void fromXML(String xml);
    String toJson();
    String toXML();
}
