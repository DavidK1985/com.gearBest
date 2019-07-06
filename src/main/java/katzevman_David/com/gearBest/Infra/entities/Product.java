package katzevman_David.com.gearBest.Infra.entities;

import katzevman_David.com.gearBest.Infra.web.By2;

public class Product {

	private By2 name;
	private By2 price;
	private By2 discount;

	public Product (By2 productTitle, By2 ProductPrice, By2 productDiscount) {
		this.name = productTitle;
		this.price = ProductPrice;
		this.discount = productDiscount;
	}

	public By2 getName() {
		return name;
	}

	public void setName(By2 name) {
		this.name = name;
	}

	public By2 getPrice() {
		return price;
	}

	public void setPrice(By2 price) {
		this.price = price;
	}

	public By2 getDiscount() {
		return discount;
	}

	public void setDiscount(By2 discount) {
		this.discount = discount;
	}

}
