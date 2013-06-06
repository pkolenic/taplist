package com.ratworkshop.taplist.adapters;

import java.util.List;

import com.ratworkshop.taplist.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SelectionSpinnerAdapter extends ArrayAdapter<String> {

	private Context context;
	private int layoutResourceId;
	private int dropdownViewResourceId = -1;
	private int selectionStringId = -1;
	private List<String> publist = null;

	public SelectionSpinnerAdapter(Context context, int layoutResourceId, List<String> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.publist = items;
	}
	
	public SelectionSpinnerAdapter(Context context, int layoutResourceId, List<String> items, int selectionStringId) {
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
		label.setText(publist.get(position));
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
