package com.ratworkshop.taplist.models;

public class Brew {
	private String id;
	private String name;
	private String description;
	private double abv;
	private double glass;
	private double quart;
	private double growler;
	private boolean isFeatured;
	private int icon;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAbv() {
		return abv;
	}

	public void setAbv(double abv) {
		this.abv = abv;
	}

	public double getGlass() {
		return glass;
	}

	public void setGlass(double glass) {
		this.glass = glass;
	}

	public double getQuart() {
		return quart;
	}

	public void setQuart(double quart) {
		this.quart = quart;
	}

	public double getGrowler() {
		return growler;
	}

	public void setGrowler(double growler) {
		this.growler = growler;
	}

	public boolean isFeatured() {
		return isFeatured;
	}

	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return name.concat(" " + abv + "%");
	}

}
