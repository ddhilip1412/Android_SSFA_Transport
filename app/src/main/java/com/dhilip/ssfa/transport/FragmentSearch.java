package com.dhilip.ssfa.transport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dhilip on 6/24/2016.
 */


public class FragmentSearch extends Fragment
{
    View rootView;
    private ListView listView_searchResults;
    private EditText editText_search;
    private CustomAdapterSchedule customGuestAdapter;

    public static FragmentSearch newInstance()
    {
        return new FragmentSearch();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        InitializeControls();
        SetupListView();
        return rootView;
    }

    private void InitializeControls()
    {
        editText_search = (EditText) rootView.findViewById(R.id.editText_search);
        listView_searchResults = (ListView) rootView.findViewById(R.id.listView_searchResults);

        listView_searchResults.setTextFilterEnabled(true);

        editText_search.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (before > count)
                    customGuestAdapter.resetList();
                customGuestAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void ShowDetailsOfSelectedGuest(View viewClicked)
    {
        TransDBHandler db = new TransDBHandler(this.getContext());
        TextView listView_schedule_textViewID = (TextView) viewClicked.findViewById(R.id.listView_schedule_textViewID);
        TextView listView_schedule_textViewName = (TextView) viewClicked.findViewById(R.id.listView_schedule_textViewName);
        int guestID = Integer.parseInt(listView_schedule_textViewID.getText().toString());
        boolean isDeparture = (listView_schedule_textViewName.getCurrentTextColor() == this.getActivity().getResources().getColor(R.color.colorDeparture));
        DialogEditGuest dialogEditGuest = new DialogEditGuest(db.getGuest(guestID), isDeparture);
        dialogEditGuest.show(this.getActivity().getSupportFragmentManager(), "Edit Current Guest");
    }

    private void SetupListView()
    {
        List<Guest> completeGuestList = PopulateCompleteGuestList();
        customGuestAdapter = new CustomAdapterSchedule(this.getContext(), completeGuestList);
        listView_searchResults.setAdapter(customGuestAdapter);

        listView_searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id)
            {
                ShowDetailsOfSelectedGuest(viewClicked);
            }
        });
    }

    private List<Guest> PopulateCompleteGuestList()
    {
        TransDBHandler db = new TransDBHandler(this.getContext());
        return db.getAllGuests();
    }
}
