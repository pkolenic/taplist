package com.ratworkshop.taplist.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ratworkshop.taplist.R;
import com.ratworkshop.taplist.models.Pub;

public class SelectionSpinnerAdapter extends ArrayAdapter<Pub> {

	private Context context;
	private int layoutResourceId;
	private int dropdownViewResourceId = -1;
	private int selectionStringId = -1;
	private List<Pub> publist = null;

	public SelectionSpinnerAdapter(Context context, int layoutResourceId, List<Pub> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.publist = items;
	}
	
	public SelectionSpinnerAdapter(Context context, int layoutResourceId, List<Pub> items, int selectionStringId) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.publist = items;
		this.selectionStringId = selectionStringId;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		int resourceId = dropdownViewResourceId < 0 ? layoutResourceId : dropdownViewResourceId;
		View row = inflater.inflate(resourceId, parent, false);
		TextView label = (TextView)row.findViewById(R.id.text1);
		
		// Set the Pub Name
		label.setText(publist.get(position).getName());
		
		// Style the Pub Name
		label.setTextColor(publist.get(position).getPubList_font_color());
		label.setTypeface(publist.get(position).getPublist_typeface(context));
		label.setTextSize(publist.get(position).getPublist_font_size());
		label.setBackgroundColor(publist.get(position).getPublist_background_color());
		return row;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity)context).getLayoutInflater();
		View row = inflater.inflate(layoutResourceId, parent, false);
		TextView label = (TextView)row.findViewById(R.id.text1);
		if (selectionStringId > 0) {
			label.setText(selectionStringId);
		} else {
			label.setText("Select");
		}
		return row;
	}
	
	@Override
	public void setDropDownViewResource(int resource) {
		super.setDropDownViewResource(resource);
		dropdownViewResourceId = resource;
	}

	public void setSelectionStringId(int selectionStringId) {
		this.selectionStringId = selectionStringId;
	}
	
	
}
