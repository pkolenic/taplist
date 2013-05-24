package com.ratworkshop.brewforia.adapters;

import java.util.List;

import com.ratworkshop.brewforia.models.Brew;
import com.ratworkshop.brewforia.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TaplistAdapter extends ArrayAdapter<Brew> {

	Context context;
	int layoutResourceId;
	List<Brew> taplist = null;
	
	
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
		holder.brewTitle.setText(brew.name);
		holder.brewAPV.setText(Double.toString(brew.abv) + "%");
		holder.glassPrice.setText(String.format("$%.2f",brew.glass));
		holder.quartPrice.setText(String.format("$%.2f",brew.quart));
		holder.growlerPrice.setText(String.format("$%.2f", brew.growler));
		holder.imgIcon.setImageResource(brew.icon);
		
		return row;
	}

	
	static class BrewHolder
	{
		ImageView imgIcon;
		TextView brewTitle;
		TextView brewAPV;
		TextView glassPrice;
		TextView quartPrice;
		TextView growlerPrice;
	}
}
