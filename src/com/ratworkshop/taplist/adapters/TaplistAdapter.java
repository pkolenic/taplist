package com.ratworkshop.taplist.adapters;

import java.util.List;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Brew;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaplistAdapter extends ArrayAdapter<Brew> {

	private Context context;
	private int layoutResourceId;
	private List<Brew> taplist = null;
	private int backgroundColor = Color.parseColor("#000000");
	private int featuredColor = Color.parseColor("#607d32");
	private int featuredNameColor = Color.parseColor("#607d32");
	private int listColor = Color.parseColor("#e9dcc8");
	private int listNameColor = Color.parseColor("#e9dcc8");
	private Typeface featuredFontFace = Typeface.DEFAULT_BOLD;
	private Typeface featuredNameFontFace = Typeface.DEFAULT_BOLD;
	private Typeface listFontFace = Typeface.DEFAULT;
	private Typeface listNameFontFace = Typeface.DEFAULT_BOLD;
	
	
	static class BrewHolder
	{
		ImageView imgIcon;
		TextView brewTitle;
		TextView brewAPV;
		TextView glassPrice;
		TextView quartPrice;
		TextView growlerPrice;
	}
	
	public TaplistAdapter(Context context, int layoutResourceId, List<Brew> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.taplist = items;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		BrewHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new BrewHolder();
			
			holder.imgIcon = (ImageView)row.findViewById(R.id.taplistIcon);
			holder.brewTitle = (TextView)row.findViewById(R.id.taplistTitle);
			holder.brewAPV = (TextView)row.findViewById(R.id.taplistAPV);
			holder.glassPrice = (TextView)row.findViewById(R.id.taplistGlass);
			holder.quartPrice = (TextView)row.findViewById(R.id.taplistQuart);
			holder.growlerPrice = (TextView)row.findViewById(R.id.taplistGrowler);
			
			row.setTag(holder);
		} else {
			holder = (BrewHolder)row.getTag();
		}
		
		Brew brew = taplist.get(position);
		holder.brewTitle.setText(brew.getName());
		holder.brewAPV.setText(Double.toString(brew.getAbv()) + "%");
		holder.glassPrice.setText(String.format("$%.2f",brew.getGlass()));
		holder.quartPrice.setText(String.format("$%.2f",brew.getQuart()));
		holder.growlerPrice.setText(String.format("$%.2f", brew.getGrowler()));
		holder.imgIcon.setImageResource(brew.getIcon());
		
		// Set Colors
		row.setBackgroundColor(backgroundColor);
		holder.brewTitle.setTextColor(brew.isFeatured() ? featuredNameColor : listNameColor);
		holder.brewAPV.setTextColor(brew.isFeatured() ? featuredColor : listColor);
		holder.glassPrice.setTextColor(brew.isFeatured() ? featuredColor : listColor);
		holder.quartPrice.setTextColor(brew.isFeatured() ? featuredColor : listColor);
		holder.growlerPrice.setTextColor(brew.isFeatured() ? featuredColor : listColor);
		
		// Set Styles
		holder.brewTitle.setTypeface(brew.isFeatured() ? featuredNameFontFace : listNameFontFace);
		holder.brewAPV.setTypeface(brew.isFeatured() ? featuredFontFace : listFontFace);
		holder.glassPrice.setTypeface(brew.isFeatured() ? featuredFontFace : listFontFace);
		holder.quartPrice.setTypeface(brew.isFeatured() ? featuredFontFace : listFontFace);
		holder.growlerPrice.setTypeface(brew.isFeatured() ? featuredFontFace : listFontFace);
		
		return row;
	}

	public void setBackgroundColor(String color) {
		backgroundColor = Color.parseColor(color);
	}
	
	public void setFeaturedColor(String color) {
		featuredColor = Color.parseColor(color);
	}
	
	public void setFeaturedNameColor(String color) {
		featuredNameColor = Color.parseColor(color);
	}
	
	public void setListColor(String color) {
		listColor = Color.parseColor(color);
	}
	
	public void setListNameColor(String color) {
		listNameColor = Color.parseColor(color);
	}

	public void setFeaturedFontFace(Typeface featuredFontFace) {
		this.featuredFontFace = featuredFontFace;
	}

	public void setFeaturedNameFontFace(Typeface featuredNameFontFace) {
		this.featuredNameFontFace = featuredNameFontFace;
	}

	public void setListFontFace(Typeface listFontFace) {
		this.listFontFace = listFontFace;
	}

	public void setListNameFontFace(Typeface listNameFontFace) {
		this.listNameFontFace = listNameFontFace;
	}
}
