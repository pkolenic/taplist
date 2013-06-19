package com.ratworkshop.taplist.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Brew;

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
	private float listSize = 12.0f;
	private float listNameSize = 12.0f;
	private float featuredSize = 12.0f;
	private float featuredNameSize = 12.0f;
	private SubheaderHolder subheader;
	
	static class BrewHolder
	{
		ImageView imgIcon;
		TextView brewTitle;
		TextView brewAPV;
		TextView glassPrice;
		TextView quartPrice;
		TextView growlerPrice;
	}
	
	static class SubheaderHolder
	{
		TextView brewAPV;
		TextView glassPrice;
		TextView quartPrice;
		TextView growlerPrice;
		Space titleSpace;
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
		
		if (subheader == null) {
			subheader = new SubheaderHolder();
			final Activity activity = (Activity) context;
			LinearLayout layout = (LinearLayout) activity.findViewById(R.id.taplistSubheader);
			subheader.brewAPV = (TextView) layout.findViewById(R.id.taplistHeaderABV);
			subheader.glassPrice = (TextView) layout.findViewById(R.id.taplistHeaderGlass);
			subheader.quartPrice = (TextView) layout.findViewById(R.id.taplistHeaderQuart);
			subheader.growlerPrice = (TextView) layout.findViewById(R.id.taplistHeaderGrowler);
			subheader.titleSpace = (Space) layout.findViewById(R.id.taplistHeaderSpace);
		}
		
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
		holder.brewAPV.setText(String.format("%.1f%%", brew.getAbv()));
		holder.glassPrice.setText(String.format("$%.2f", brew.getGlass()));
		holder.quartPrice.setText(String.format("$%.2f", brew.getQuart()));
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
		
		// Set Text Sizes
		holder.brewTitle.setTextSize(brew.isFeatured() ? featuredNameSize : listNameSize);
		holder.brewAPV.setTextSize(brew.isFeatured() ? featuredSize : listSize);
		holder.glassPrice.setTextSize(brew.isFeatured() ? featuredSize : listSize);
		holder.quartPrice.setTextSize(brew.isFeatured() ? featuredSize : listSize);
		holder.growlerPrice.setTextSize(brew.isFeatured() ? featuredSize: listSize);
		
		// Set Sizes
		holder.brewAPV.setWidth(subheader.brewAPV.getWidth());
		holder.glassPrice.setWidth(subheader.glassPrice.getWidth());
		holder.quartPrice.setWidth(subheader.quartPrice.getWidth());
		holder.growlerPrice.setWidth(subheader.growlerPrice.getWidth());
		
		// Hide Glass Icon if not enough room
		if (subheader.titleSpace.getWidth() < 100) {
			holder.imgIcon.setVisibility(View.GONE);
		} else {
			holder.imgIcon.setVisibility(View.VISIBLE);
		}
	
		return row;
	}
	
	public void setBackgroundColor(int color) {
		backgroundColor = color;
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

	public void setListSize(float listSize) {
		this.listSize = listSize;
	}

	public void setListNameSize(float listNameSize) {
		this.listNameSize = listNameSize;
	}

	public void setFeaturedSize(float featuredSize) {
		this.featuredSize = featuredSize;
	}

	public void setFeaturedNameSize(float featuredNameSize) {
		this.featuredNameSize = featuredNameSize;
	}
}
