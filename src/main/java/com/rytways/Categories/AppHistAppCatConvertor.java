package com.rytways.Categories;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AppHistAppCatConvertor implements AttributeConverter<AppHistAppCat, Integer>{
	
	@Override
    public Integer convertToDatabaseColumn(AppHistAppCat Categories) {
        return Categories.getcatValue();
    }

	@Override
	public AppHistAppCat convertToEntityAttribute(Integer dbData) {
		// TODO Auto-generated method stub
		 return AppHistAppCat.fromcatValue(dbData);
	}
	
}
