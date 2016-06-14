package com.dhilip.ssfa.transport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dhilip on 6/4/2016.
 */
class CustomAdapterSchedule extends ArrayAdapter<Guest>
{
    public CustomAdapterSchedule(Context context, List<Guest> guests)
    {
        super(context, R.layout.listviewitem_time, guests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater fragmentViewInflater = LayoutInflater.from(getContext());
        View listViewItem_guests = fragmentViewInflater.inflate(R.layout.listviewitem_schedule, parent, false);

        Guest currentGuest = getItem(position);
        TextView listView_schedule_textViewName = (TextView) listViewItem_guests.findViewById(R.id.listView_schedule_textViewName);
        TextView listView_schedule_textViewModeOfTravel = (TextView) listViewItem_guests.findViewById(R.id.listView_schedule_textViewModeOfTravel);
        TextView listView_schedule_textViewOthersCount = (TextView) listViewItem_guests.findViewById(R.id.listView_schedule_textViewOthersCount);
        TextView listView_schedule_textViewDetails = (TextView) listViewItem_guests.findViewById(R.id.listView_schedule_textViewDetails);
        TextView listView_schedule_textViewID = (TextView) listViewItem_guests.findViewById(R.id.listView_schedule_textViewID);

        listView_schedule_textViewName.setText(currentGuest.getName());
        listView_schedule_textViewModeOfTravel.setText(currentGuest.getModeOfTravel());
        listView_schedule_textViewOthersCount.setText(currentGuest.getOthersCount());
        listView_schedule_textViewDetails.setText(currentGuest.getDetailsOfTravel());
        listView_schedule_textViewID.setText("" + currentGuest.getId());


        if (currentGuest.isDeparture())
            listView_schedule_textViewName.setTextColor(listView_schedule_textViewName.getContext().getResources().getColor(R.color.colorDeparture));
        else
            listView_schedule_textViewName.setTextColor(listView_schedule_textViewName.getContext().getResources().getColor(R.color.colorArrival));

        return listViewItem_guests;
    }
}
