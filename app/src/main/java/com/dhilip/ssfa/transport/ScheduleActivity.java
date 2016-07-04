package com.dhilip.ssfa.transport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity
{

    String selectedDateString;
    boolean isDeparture;
    String arrivalOrDeparture = "Arrival";
    List<Guest> airportGuestList;
    List<Guest> railwayStationGuestList;
    List<Guest> otherGuestList;
    ListView listView_schedule_airport;
    ListView listView_schedule_railwayStation;
    ListView listView_schedule_other;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        GetExtras(savedInstanceState);
        UseExtras();
        InitializeControls();

        PopulateActivityViews();
    }

    private void InitializeControls()
    {
        airportGuestList = new ArrayList<>();
        railwayStationGuestList = new ArrayList<>();
        otherGuestList = new ArrayList<>();

        AdapterView.OnItemClickListener ListViewItemClickListener = new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                ShowDetailsOfSelectedGuest(viewClicked);
            }
        };

        listView_schedule_airport = (ListView) findViewById(R.id.listView_schedule_airport);
        listView_schedule_airport.setOnItemClickListener(ListViewItemClickListener);

        listView_schedule_railwayStation = (ListView) findViewById(R.id.listView_schedule_railwayStation);
        listView_schedule_railwayStation.setOnItemClickListener(ListViewItemClickListener);

        listView_schedule_other = (ListView) findViewById(R.id.listView_schedule_other);
        listView_schedule_other.setOnItemClickListener(ListViewItemClickListener);
    }


    private void ShowDetailsOfSelectedGuest(View viewClicked)
    {
        TransDBHandler db = new TransDBHandler(this);
        TextView listView_schedule_textViewID = (TextView) viewClicked.findViewById(R.id.listView_schedule_textViewID);
        int guestID = Integer.parseInt(listView_schedule_textViewID.getText().toString());
        DialogEditGuest dialogEditGuest = new DialogEditGuest(db.getGuest(guestID), isDeparture);
        dialogEditGuest.show(getSupportFragmentManager(), "Edit Current Guest");
    }

    private void PopulateActivityViews()
    {
        PopulateIndividualGuestLists();
        SetListViewAdapters();
        SetViewVisibilities();
        SetListViewHeights();
    }

    private void SetListViewHeights()
    {
        HelperFunctions.setDynamicHeight(listView_schedule_airport);
        HelperFunctions.setDynamicHeight(listView_schedule_railwayStation);
        HelperFunctions.setDynamicHeight(listView_schedule_other);
    }

    private void SetViewVisibilities()
    {
        if (airportGuestList.size() < 1)
        {
            findViewById(R.id.textView_airport).setVisibility(View.GONE);
            listView_schedule_airport.setVisibility(View.GONE);
        }
        if (railwayStationGuestList.size() < 1)
        {
            findViewById(R.id.textView_railwayStation).setVisibility(View.GONE);
            listView_schedule_railwayStation.setVisibility(View.GONE);
        }
        if (otherGuestList.size() < 1)
        {
            findViewById(R.id.textView_other).setVisibility(View.GONE);
            listView_schedule_other.setVisibility(View.GONE);
        }
    }

    private void SetListViewAdapters()
    {
        CustomAdapterSchedule airportAdapter = new CustomAdapterSchedule(this, airportGuestList);
        listView_schedule_airport.setAdapter(airportAdapter);

        CustomAdapterSchedule railwayStationAdapter = new CustomAdapterSchedule(this, railwayStationGuestList);
        listView_schedule_railwayStation.setAdapter(railwayStationAdapter);

        CustomAdapterSchedule otherAdapter = new CustomAdapterSchedule(this, otherGuestList);
        listView_schedule_other.setAdapter(otherAdapter);
    }

    private void PopulateIndividualGuestLists()
    {
        List<Guest> currentGuestList = PopulateCurrentGuestList();
        Guest currentGuest;
        for (int i = 0; i < currentGuestList.size(); i++)
        {
            currentGuest = currentGuestList.get(i);
            if (currentGuest.getModeOfTravel().equals(getString(R.string.travelMode_AIRPORT)))
                airportGuestList.add(currentGuest);
            else if (currentGuest.getModeOfTravel().equals(getString(R.string.travelMode_RAILWAYSTATION)))
                railwayStationGuestList.add(currentGuest);
            else
                otherGuestList.add(currentGuest);
        }
    }

    private List<Guest> PopulateCurrentGuestList()
    {
        TransDBHandler db = new TransDBHandler(this);
        int isDepartureInt = 0;
        int isDone = 0;
        if (isDeparture) isDepartureInt = 1;
        String convertedDateString = HelperFunctions.ConvertDateString(selectedDateString, Constants.SECONDARY_DATEFORMAT, Constants.DATEFORMAT);
        return db.getGuests(convertedDateString, isDepartureInt, isDone);
    }

    private void UseExtras()
    {
        if (isDeparture)
            arrivalOrDeparture = "Departure";

        String title = arrivalOrDeparture + "s on " + selectedDateString;
        setTitle(title);
    }

    private void GetExtras(Bundle savedInstanceState)
    {

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
            {
                selectedDateString = null;
            } else
            {
                selectedDateString = extras.getString(Constants.SELECTED_DATETIME);
                isDeparture = extras.getBoolean(Constants.ISDEPARTURE);
            }
        } else
        {
            selectedDateString = savedInstanceState.getString(Constants.SELECTED_DATETIME);
            isDeparture = savedInstanceState.getBoolean(Constants.ISDEPARTURE);
        }
    }
}
