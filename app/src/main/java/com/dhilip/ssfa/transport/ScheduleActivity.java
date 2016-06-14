package com.dhilip.ssfa.transport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity
{

    Date selectedDate;
    boolean isDeparture;
    String arrivalOrDeparture = "Arrival";
    List<Guest> currentGuestList;
    ListView listView_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        InitializeControls();
        GetExtras(savedInstanceState);
        UseExtras();

        PopulateActivityViews();


    }

    private void InitializeControls()
    {
        listView_schedule = (ListView) findViewById(R.id.listView_schedule);
        listView_schedule.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                ShowDetailsOfSelectedGuest(viewClicked);
            }
        });
    }

    private void ShowDetailsOfSelectedGuest(View viewClicked)
    {
        TransDBHandler db = new TransDBHandler(this);
        TextView listView_schedule_textViewID = (TextView) viewClicked.findViewById(R.id.listView_schedule_textViewID);
        int guestID = Integer.parseInt(listView_schedule_textViewID.getText().toString());
        DialogEditGuest dialogEditGuest = new DialogEditGuest(db.getGuest(guestID));
        dialogEditGuest.show(getSupportFragmentManager(), "Edit Current Guest");
    }

    private void PopulateActivityViews()
    {
        PopulateCurrentGuestList();
        CustomAdapterSchedule adapter = new CustomAdapterSchedule(this, currentGuestList);
        listView_schedule.setAdapter(adapter);
    }

    private void PopulateCurrentGuestList()
    {
        TransDBHandler db = new TransDBHandler(this);
        String selectedDateString = HelperFunctions.GetStringFrom(selectedDate, Constants.DATEFORMAT);
        int isDepartureInt = 0;
        if (isDeparture) isDepartureInt = 1;

        currentGuestList = db.getGuests(selectedDateString, isDepartureInt);
    }

    private void UseExtras()
    {
        if (isDeparture)
            arrivalOrDeparture = "Departure";

        String title = arrivalOrDeparture + "s on " + HelperFunctions.GetStringFrom(selectedDate, Constants.SECONDARY_DATEFORMAT);
        setTitle(title);
    }

    private void GetExtras(Bundle savedInstanceState)
    {

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();
            if (extras == null)
            {
                selectedDate = null;
            } else
            {
                selectedDate = (Date) extras.getSerializable(Constants.SELECTED_DATETIME);
                isDeparture = extras.getBoolean(Constants.ISDEPARTURE);
            }
        } else
        {
            selectedDate = (Date) savedInstanceState.getSerializable(Constants.SELECTED_DATETIME);
            isDeparture = savedInstanceState.getBoolean(Constants.ISDEPARTURE);
        }
    }
}
