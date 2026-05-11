package com.rytways.Categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum AppHistAppCat {
	Select(0),InputSheetApproval(1),No(2);

    private Integer catValue;
        
    
    private AppHistAppCat(Integer catValue) {
    	
    	if(catValue==null){
    		this.catValue = 0;
    	}else{
        this.catValue = catValue;
    	}
    }
 
    public Integer getcatValue() {
        return catValue;
    }
 
    public static AppHistAppCat fromcatValue(int catValue) {
        switch (catValue) {
        case 1:
            return AppHistAppCat.InputSheetApproval;
        case 2:
            return AppHistAppCat.No;
        default:
            return AppHistAppCat.Select;
        }
    }
}
