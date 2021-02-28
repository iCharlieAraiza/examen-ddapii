package com.coding.components;

import java.util.List;

public class FormattedXML {
    public String createTag(String name, String content){
        return "<"+name+">"+content+"</"+name+">";
    }

    public String createTag(String name, Double content){
        return "<"+name+">"+content+"</"+name+">";
    }

    public String createTag(String name, List<String> tags){
        String tagString = "";
        for(String tag : tags){
            tagString += tag;
        }
        return createTag(name, tagString);
    }

}
