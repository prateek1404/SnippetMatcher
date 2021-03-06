package org.mockup.wvuta;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

// custom ArrayAdapter to format display reports
public class RowAdapter extends ArrayAdapter<Report>{

	private int resource;
	
	public RowAdapter(Context context, int textViewResourceId,
			List<Report> reports) {
		super(context, textViewResourceId, reports);
		this.resource = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout rowView;
		
		Report report = getItem(position);
		
		String date = report.getTime();
		String location = report.getLocation();
		String status = report.getStatus();
		
		// Inflate resource layout
		if (convertView == null){
			rowView = new LinearLayout(getContext());
			String inflate = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li = (LayoutInflater)getContext().getSystemService(inflate);
			li.inflate(resource, rowView, true);
		} else {
			rowView = (LinearLayout) convertView;
		}
		
		TextView datecol = (TextView) rowView.findViewById(R.id.dateCol);
		TextView locCol = (TextView) rowView.findViewById(R.id.locCol);
		TextView statCol = (TextView) rowView.findViewById(R.id.statCol);
		
		// Set appropriate values
		datecol.setText(date);
		locCol.setText(location);
		statCol.setText(status);
		
		return rowView;
	}
}