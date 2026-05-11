package com.rytways.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ExcelDTO {
	
		private String regionName;
	    private String productName;
	    private int gsmFrom;
	    private int gsmTo;
	    private double reelPrice;
	    private String discount;
	    //private String rateUpdatedDate;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private LocalDate rateUpdatedDate;

}
