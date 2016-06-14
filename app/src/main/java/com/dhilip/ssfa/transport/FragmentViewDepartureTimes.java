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

import java.util.Date;
import java.util.List;


/**
 * Created by Dhilip on 5/27/2016.
 */
public class FragmentViewDepartureTimes extends Fragment
{

    ListView listView_viewDepartureTimes;
    View rootView;
    SwipeRefreshLayout swipeRefreshLayout_viewDepartureTimings;

    private void InitializeControls()
    {
        swipeRefreshLayout_viewDepartureTimings = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout_viewDepartureTimes);
        listView_viewDepartureTimes = (ListView) rootView.findViewById(R.id.listView_viewDepartureTimes);

        swipeRefreshLayout_viewDepartureTimings.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener()
                {
                    @Override
                    public void onRefresh()
                    {
                        RefreshDepartureTimesListView();
                    }
                }

        );

        listView_viewDepartureTimes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                TextView listItemClicked = (TextView) viewClicked;

                Date dateTime = HelperFunctions.GetDateFrom(listItemClicked.getText().toString(),Constants.DATEFORMAT);

                Intent intent = new Intent(getActivity(), ScheduleActivity.class);
                intent.putExtra(Constants.SELECTED_DATETIME,dateTime);
                intent.putExtra(Constants.ISDEPARTURE,true);

                startActivity(intent);

            }
        });
    }

    public static FragmentViewDepartureTimes newInstance()
    {
        return new FragmentViewDepartureTimes();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_viewdeparture, container, false);

        InitializeControls();
        PopulateDepartureTimesListView();

        return rootView;
    }

    private void PopulateDepartureTimesListView()
    {
        //Create Items
        TransDBHandler db = new TransDBHandler(this.getContext());
        List<String> distinctTime = db.getDepartureDistinctTime();

        // Setup Adapter
        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.listviewitem_time, distinctTime);

        // Setup ListView
        listView_viewDepartureTimes.setAdapter(adapter);
    }

    private void RefreshDepartureTimesListView()
    {
        PopulateDepartureTimesListView();
        swipeRefreshLayout_viewDepartureTimings.setRefreshing(false);
    }

}
