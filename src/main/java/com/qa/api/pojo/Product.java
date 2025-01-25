package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Product {
	
	private Integer id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Rating {
		private double rate;
		private int count;
	}

}
