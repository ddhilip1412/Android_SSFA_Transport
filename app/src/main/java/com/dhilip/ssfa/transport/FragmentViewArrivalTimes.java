package com.dhilip.ssfa.transport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Dhilip on 5/27/2016.
 */
public class FragmentViewArrivalTimes extends Fragment
{

    ListView listView_viewArrivalTimes;
    View rootView;
    SwipeRefreshLayout swipeRefreshLayout_viewArrivalTimings;

    public static FragmentViewArrivalTimes newInstance()
    {
        return new FragmentViewArrivalTimes();
    }

    private void InitializeControls()
    {
        swipeRefreshLayout_viewArrivalTimings = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout_viewArrivalTimes);
        listView_viewArrivalTimes = (ListView) rootView.findViewById(R.id.listView_viewArrivalTimes);

        swipeRefreshLayout_viewArrivalTimings.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        RefreshArrivalTimesListView();
                    }
                }

        );

        listView_viewArrivalTimes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                TextView listItemClicked = (TextView) viewClicked;

                String dateTime = listItemClicked.getText().toString();

                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra(Constants.SELECTED_DATETIME, dateTime);
                intent.putExtra(Constants.ISDEPARTURE, false);

                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_viewarrival, container, false);

        InitializeControls();
        PopulateArrivalTimesListView();

        return rootView;
    }

    private void PopulateArrivalTimesListView()
    {
        //Create Items
        TransDBHandler db = new TransDBHandler(this.getContext());
        List<String> distinctTime = db.getArrivalDistinctTime();
        List<String> formattedDistinctTime = new ArrayList<>();
        Date dateTime;
        for (int i = 0; i < distinctTime.size(); i++)
        {
            dateTime = HelperFunctions.GetDateFrom(distinctTime.get(i), Constants.DATEFORMAT);
            formattedDistinctTime.add(HelperFunctions.GetStringFrom(dateTime, Constants.SECONDARY_DATEFORMAT));
        }
        // Setup Adapter
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.listviewitem_time, formattedDistinctTime);

        // Setup ListView
        listView_viewArrivalTimes.setAdapter(adapter);
    }

    private void RefreshArrivalTimesListView()
    {
        PopulateArrivalTimesListView();
        swipeRefreshLayout_viewArrivalTimings.setRefreshing(false);
    }

}
