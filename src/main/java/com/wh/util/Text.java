package com.wh.util;

public abstract class Text {  
	  
    public abstract String getText();  
  
    public static Text str(final String string) {  
        return new Text() {  
            @Override  
            public String getText() {  
                return string.trim();  
            }  
        };  
    }
    public static String getStr(String string){
    	return string.trim();
    }
  
}  
