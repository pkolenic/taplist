package com.ratworkshop.brewforia.models;

public class Brew {
	public String id;
	public String name;
	public String description;
	public double abv;
	public double glass;
	public double quart;
	public double growler;
	public boolean isFeatured;
	public int icon;

	public Brew(String id, String name, String description, double d, double e, double f, double g, boolean isFeatured, int icon) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.abv = d;
		this.glass = e;
		this.quart = f;
		this.growler = g;
		this.isFeatured = isFeatured;
		this.icon = icon;
	}

	@Override
	public String toString() {
		return name.concat(" " + abv + "%");
	}

}
