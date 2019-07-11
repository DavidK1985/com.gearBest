package katzevman_David.com.gearBest.Infra.entities;

import java.awt.geom.Arc2D.Float;

import katzevman_David.com.gearBest.Infra.web.By2;

public class Product {

	public static String[][] resultsFoundArray;
	private String name;
	private Float price;
	private int discount; 

//	public Product (String productTitle,Float ProductPrice, int productDiscount) {
//		this.name = productTitle;
//		this.price = ProductPrice;
//		this.discount = productDiscount;
//	}

	public String getName() {
		return name;
	} 

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
