package com.ratworkshop.taplist.models;

import com.ratworkshop.taplist.R;

public class Brew {
	
	public enum BrewType {
		IPA, PORTER, STOUT, PILSNER, CIDAR, ALE, AMBER, WHEAT
	}
	
	private String id;
	private String name;
	private String description;
	private double abv;
	private double glass;
	private double quart;
	private double growler;
	private boolean isFeatured;
	private BrewType brewtype;
	private int icon;
	private String custom_icon;

	public Brew(String id, String name, String description, double d, double e, double f, double g, boolean isFeatured, String custom_icon, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.abv = d;
		this.glass = e;
		this.quart = f;
		this.growler = g;
		this.isFeatured = isFeatured;
		this.custom_icon = custom_icon;
		setBrewtype(type);
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
	
	public String getCustomIcon() {
		return custom_icon;
	}

	public BrewType getBrewtype() {
		return brewtype;
	}

	public void setBrewtype(String brewtype) {
		this.brewtype = BrewType.valueOf(brewtype);

		switch(this.brewtype) {
			case IPA:
				icon = R.drawable.belgian_ale;
				break;
			case PORTER:
				icon = R.drawable.porter_stout;
				break;
			case STOUT:
				icon = R.drawable.porter_stout;
				break;
			case PILSNER: 
				icon = R.drawable.classic_pilsner;
				break;
			case CIDAR:
				icon = R.drawable.classic_pilsner;
				break;
			case ALE:
				icon = R.drawable.craft_pub;
				break;
			case AMBER:
				icon = R.drawable.craft_pub;
				break;
			case WHEAT:
				icon = R.drawable.wheat_beer;
				break;
			default:
				icon = R.drawable.craft_pub;
				break;
		}
	}

	@Override
	public String toString() {
		return name.concat(" " + abv + "%");
	}

}
