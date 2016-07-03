package com.dhilip.ssfa.transport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhilip on 6/4/2016.
 */
class CustomAdapterSchedule extends ArrayAdapter<Guest> implements Filterable
{
    List<Guest> currentGuests;
    List<Guest> originalGuests;
    GuestFilter guestFilter;

    public CustomAdapterSchedule(Context context, List<Guest> guests)
    {
        super(context, R.layout.listviewitem_time, guests);
        currentGuests = guests;
        originalGuests = guests;
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

    @Override
    public int getCount()
    {
        return currentGuests.size();
    }

    @Override
    public Guest getItem(int position)
    {
        return currentGuests.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return currentGuests.get(position).hashCode();
    }

    public void resetList()
    {
        currentGuests = originalGuests;
    }

    @Override
    public Filter getFilter()
    {
        if (guestFilter == null)
            guestFilter = new GuestFilter();
        return guestFilter;
    }

    private class GuestFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0)
            {
                // No filter implemented we return all the list
                results.values = originalGuests;
                results.count = originalGuests.size();
            } else
            {
                // We perform filtering operation
                List<Guest> filteredGuests = new ArrayList<>();

                for (Guest guest : currentGuests)
                {
                    if (guest.contains(constraint))
                        filteredGuests.add(guest);
                }
                results.values = filteredGuests;
                results.count = filteredGuests.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results)
        {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else
            {
                currentGuests = (List<Guest>) results.values;
                notifyDataSetChanged();
            }

        }

    }
}
