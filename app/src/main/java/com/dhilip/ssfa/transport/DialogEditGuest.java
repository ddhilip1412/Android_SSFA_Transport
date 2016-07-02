package com.dhilip.ssfa.transport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dhilip on 6/9/2016.
 */
public class DialogEditGuest extends DialogFragment
{
    static final String EMPTY_STRING = "";
    static EditText dialog_editText_arr_other;
    static TextView dialog_textView_arrivalTime;
    static EditText dialog_editText_dep_other;
    static TextView dialog_textView_departureTime;
    static TextView dialog_textView_selected;
    static RadioButton dialog_radioButton_arr_other;
    static RadioButton dialog_radioButton_dep_other;
    static Date date;
    static List<Guest> guests;
    static int arrivalID;
    static int departureID;
    boolean isDeparture;
    View rootView;
    EditText dialog_editText_name;
    EditText dialog_editText_othersCount;
    EditText dialog_editText_hometown;
    EditText dialog_editText_arrivalDetails;
    EditText dialog_editText_placeOfStay;
    EditText dialog_editText_departureDetails;
    EditText dialog_editText_contactNo;
    EditText dialog_editText_facebookID;
    EditText dialog_editText_emailID;
    Button dialog_button_Update;
    Button dialog_button_Done;
    RadioButton dialog_radioButton_arr_airport;
    RadioButton dialog_radioButton_dep_airport;
    RadioButton dialog_radioButton_arr_railwayStation;
    RadioButton dialog_radioButton_dep_railwayStation;
    RadioGroup dialog_radioGroup_arrival;
    RadioGroup dialog_radioGroup_departure;
    CheckBox dialog_checkBox_isArtist;


    public DialogEditGuest(List<Guest> guests, boolean isDeparture)
    {
        DialogEditGuest.guests = guests;

        if (guests.get(0).isDeparture())
        {
            departureID = guests.get(0).getId();
            arrivalID = guests.get(1).getId();
        } else
        {
            departureID = guests.get(1).getId();
            arrivalID = guests.get(0).getId();
        }

        this.isDeparture = isDeparture;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.dialog_updateguests, null);
        InitializeControls();
        PopulateControls(guests);
        return rootView;
    }

    private void InitializeControls()
    {
        // Initialization
        dialog_editText_emailID = (EditText) rootView.findViewById(R.id.dialog_editText_emailID);
        dialog_editText_name = (EditText) rootView.findViewById(R.id.dialog_editText_name);
        dialog_editText_othersCount = (EditText) rootView.findViewById(R.id.dialog_editText_othersCount);
        dialog_editText_hometown = (EditText) rootView.findViewById(R.id.dialog_editText_homeTown);
        dialog_editText_arr_other = (EditText) rootView.findViewById(R.id.dialog_editText_arr_other);
        dialog_textView_arrivalTime = (TextView) rootView.findViewById(R.id.dialog_editText_arrivalTime);
        dialog_editText_arrivalDetails = (EditText) rootView.findViewById(R.id.dialog_editText_arrivalDetails);
        dialog_editText_placeOfStay = (EditText) rootView.findViewById(R.id.dialog_editText_placeOfStay);
        dialog_editText_dep_other = (EditText) rootView.findViewById(R.id.dialog_editText_dep_other);
        dialog_textView_departureTime = (TextView) rootView.findViewById(R.id.dialog_editText_departureTime);
        dialog_editText_departureDetails = (EditText) rootView.findViewById(R.id.dialog_editText_departureDetails);
        dialog_editText_contactNo = (EditText) rootView.findViewById(R.id.dialog_editText_contactNo);
        dialog_editText_facebookID = (EditText) rootView.findViewById(R.id.dialog_editText_facebookID);
        dialog_button_Update = (Button) rootView.findViewById(R.id.dialog_button_Update);
        dialog_button_Done = (Button) rootView.findViewById(R.id.dialog_button_Done);
        dialog_radioButton_arr_airport = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_arr_airport);
        dialog_radioButton_dep_airport = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_dep_airport);
        dialog_radioButton_arr_railwayStation = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_arr_railwayStation);
        dialog_radioButton_dep_railwayStation = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_dep_railwayStation);
        dialog_radioButton_arr_other = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_arr_other);
        dialog_radioButton_dep_other = (RadioButton) rootView.findViewById(R.id.dialog_radioButton_dep_other);
        dialog_radioGroup_arrival = (RadioGroup) rootView.findViewById(R.id.dialog_radioGroup_arrival);
        dialog_radioGroup_arrival.setClickable(true);
        dialog_radioGroup_departure = (RadioGroup) rootView.findViewById(R.id.dialog_radioGroup_departure);
        dialog_radioGroup_departure.setClickable(true);
        dialog_checkBox_isArtist = (CheckBox) rootView.findViewById(R.id.dialog_checkBox_isArtist);

        AssignEventsForControls();
    }

    private void PopulateControls(List<Guest> guests)
    {
        dialog_editText_name.setText(guests.get(0).getName());
        dialog_editText_othersCount.setText(guests.get(0).getOthersCount());
        dialog_editText_hometown.setText(guests.get(0).getHometown());

        if (!guests.get(0).isDeparture())
        {
            dialog_textView_arrivalTime.setText(guests.get(0).getTimeOfTravel());
            dialog_editText_arrivalDetails.setText(guests.get(0).getDetailsOfTravel());

            if (guests.get(0).getModeOfTravel().equals(getString(R.string.travelMode_AIRPORT)))
                dialog_radioButton_arr_airport.setChecked(true);
            else if (guests.get(0).getModeOfTravel().equals(getString(R.string.travelMode_RAILWAYSTATION)))
                dialog_radioButton_arr_railwayStation.setChecked(true);
            else
            {
                dialog_radioButton_arr_other.setChecked(true);
                dialog_editText_arr_other.setText(guests.get(0).getModeOfTravel());
                dialog_editText_arr_other.setVisibility(View.VISIBLE);
            }

            dialog_textView_departureTime.setText(guests.get(1).getTimeOfTravel());
            dialog_editText_departureDetails.setText(guests.get(1).getDetailsOfTravel());

            if (guests.get(1).getModeOfTravel().equals(getString(R.string.travelMode_AIRPORT)))
                dialog_radioGroup_departure.check(R.id.dialog_radioButton_dep_airport);
            else if (guests.get(1).getModeOfTravel().equals(getString(R.string.travelMode_RAILWAYSTATION)))
                dialog_radioGroup_departure.check(R.id.dialog_radioButton_dep_railwayStation);
            else
            {
                dialog_radioButton_dep_other.setChecked(true);
                dialog_editText_dep_other.setText(guests.get(1).getModeOfTravel());
                dialog_editText_dep_other.setVisibility(View.VISIBLE);
            }
        } else
        {
            dialog_textView_arrivalTime.setText(guests.get(1).getTimeOfTravel());
            dialog_editText_arrivalDetails.setText(guests.get(1).getDetailsOfTravel());

            if (guests.get(1).getModeOfTravel().equals(getString(R.string.travelMode_AIRPORT)))
                dialog_radioButton_arr_airport.setChecked(true);

            else if (guests.get(1).getModeOfTravel().equals(getString(R.string.travelMode_RAILWAYSTATION)))
                dialog_radioButton_arr_railwayStation.setChecked(true);
            else
            {
                dialog_radioButton_arr_other.setChecked(true);
                dialog_editText_arr_other.setText(guests.get(1).getModeOfTravel());
                dialog_editText_arr_other.setVisibility(View.VISIBLE);
            }

            dialog_textView_departureTime.setText(guests.get(0).getTimeOfTravel());
            dialog_editText_departureDetails.setText(guests.get(0).getDetailsOfTravel());

            if (guests.get(0).getModeOfTravel().equals(getString(R.string.travelMode_AIRPORT)))
                dialog_radioButton_dep_airport.setChecked(true);
            else if (guests.get(0).getModeOfTravel().equals(getString(R.string.travelMode_RAILWAYSTATION)))
                dialog_radioButton_dep_railwayStation.setChecked(true);
            else
            {
                dialog_radioButton_dep_other.setChecked(true);
                dialog_editText_dep_other.setText(guests.get(0).getModeOfTravel());
                dialog_editText_dep_other.setVisibility(View.VISIBLE);
            }
        }
        dialog_editText_placeOfStay.setText(guests.get(0).getPlaceOfStay());
        dialog_editText_contactNo.setText(guests.get(0).getContactNo());
        dialog_editText_facebookID.setText(guests.get(0).getFacebookID());
        dialog_editText_emailID.setText(guests.get(0).getEmailID());
        dialog_checkBox_isArtist.setChecked(guests.get(0).IsArtist());
    }

    private void AssignEventsForControls()
    {
        // Event assignments
        dialog_button_Update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (ValidateForm())
                    DoUpdateAfterConfirmation();
            }
        });

        dialog_button_Done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DoDoneAfterConfirmation();
            }
        });

        dialog_textView_arrivalTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_textView_arrivalTime.setError(null);
                dialog_textView_selected = dialog_textView_arrivalTime;
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });
        dialog_textView_departureTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_textView_departureTime.setError(null);
                dialog_textView_selected = dialog_textView_departureTime;
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });
        dialog_radioGroup_arrival.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                dialog_radioButton_arr_other.setError(null);
                if (dialog_radioButton_arr_other.isChecked())
                    dialog_editText_arr_other.setVisibility(View.VISIBLE);
                else
                    dialog_editText_arr_other.setVisibility(View.GONE);
            }
        });
        dialog_radioGroup_departure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                dialog_radioButton_dep_other.setError(null);
                if (dialog_radioButton_dep_other.isChecked())
                    dialog_editText_dep_other.setVisibility(View.VISIBLE);
                else
                    dialog_editText_dep_other.setVisibility(View.GONE);
            }
        });

        // Remove Errors
        dialog_editText_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_name.setError(null);
            }
        });

        dialog_editText_emailID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_emailID.setError(null);
            }
        });

        dialog_editText_othersCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_othersCount.setError(null);
            }
        });
        dialog_editText_hometown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_hometown.setError(null);
            }
        });
        dialog_editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_arr_other.setError(null);
            }
        });
        dialog_editText_arrivalDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_arrivalDetails.setError(null);
            }
        });
        dialog_editText_placeOfStay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_placeOfStay.setError(null);
            }
        });
        dialog_editText_dep_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_dep_other.setError(null);
            }
        });
        dialog_editText_departureDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_departureDetails.setError(null);
            }
        });
        dialog_editText_contactNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_contactNo.setError(null);
            }
        });
        dialog_editText_facebookID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_facebookID.setError(null);
            }
        });

        dialog_editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_dep_other.setError(null);
            }
        });
        dialog_editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog_editText_dep_other.setError(null);
            }
        });
    }

    private void DoUpdateAfterConfirmation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm");
        builder.setMessage("Do you really want to update?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                UpdateGuests(false);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    private void UpdateGuests(boolean isDone)
    {
        TransDBHandler db = new TransDBHandler(getContext());
        for (Guest guest : GenerateUpdatedGuestList(isDone))
            db.updateGuest(guest);
    }

    private List<Guest> GenerateUpdatedGuestList(boolean isDone)
    {
        String arrivalDetails;
        String departureDetails;

        // initialize local variables
        arrivalDetails = ((RadioButton) rootView.findViewById(dialog_radioGroup_arrival.getCheckedRadioButtonId())).getText().toString().trim();
        departureDetails = ((RadioButton) rootView.findViewById(dialog_radioGroup_departure.getCheckedRadioButtonId())).getText().toString().trim();

        //local operations
        if (arrivalDetails.equals(getString(R.string.travelMode_OTHER)))
            arrivalDetails = dialog_editText_arr_other.getText().toString().trim();
        if (departureDetails.equals(getString(R.string.travelMode_OTHER)))
            departureDetails = dialog_editText_dep_other.getText().toString().trim();

        List<Guest> updatedGuests = new ArrayList<>();
        Guest arrivalEntry = new Guest(
                dialog_editText_name.getText().toString().trim(),
                dialog_editText_othersCount.getText().toString().trim(),
                dialog_editText_hometown.getText().toString().trim(),
                dialog_editText_placeOfStay.getText().toString().trim(),
                arrivalDetails,
                dialog_textView_arrivalTime.getText().toString().trim(),
                dialog_editText_arrivalDetails.getText().toString().trim(),
                dialog_editText_contactNo.getText().toString().trim(),
                dialog_editText_facebookID.getText().toString().trim(),
                dialog_editText_emailID.getText().toString().trim(),
                dialog_checkBox_isArtist.isChecked(),
                false,
                false);
        arrivalEntry.setId(arrivalID);
        Guest departureEntry = new Guest(
                dialog_editText_name.getText().toString().trim(),
                dialog_editText_othersCount.getText().toString().trim(),
                dialog_editText_hometown.getText().toString().trim(),
                dialog_editText_placeOfStay.getText().toString().trim(),
                departureDetails,
                dialog_textView_departureTime.getText().toString().trim(),
                dialog_editText_departureDetails.getText().toString().trim(),
                dialog_editText_contactNo.getText().toString().trim(),
                dialog_editText_facebookID.getText().toString().trim(),
                dialog_editText_emailID.getText().toString().trim(),
                dialog_checkBox_isArtist.isChecked(),
                true,
                false);
        departureEntry.setId(departureID);
        if (isDeparture) departureEntry.setIsDone(isDone);
        else arrivalEntry.setIsDone(isDone);
        updatedGuests.add(arrivalEntry);
        updatedGuests.add(departureEntry);
        return updatedGuests;
    }

    private void DoDoneAfterConfirmation()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm");
        builder.setMessage("Do you really want to mark this entry as completed?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                UpdateGuests(true);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.no, null);
        builder.show();
    }

    private boolean ValidateForm()
    {
        boolean result = true;
        if (dialog_editText_name.getText().toString().trim().equals(""))
        {
            dialog_editText_name.setError("Name is required");
            result = false;
        }
        if (dialog_editText_othersCount.getText().toString().trim().equals(""))
        {
            dialog_editText_othersCount.setError("Count of others travelling is required");
            result = false;
        }
        if (dialog_editText_hometown.getText().toString().trim().equals(""))
        {
            dialog_editText_hometown.setError("Hometown is required");
            result = false;
        }
        if (dialog_textView_arrivalTime.getText().toString().trim().equals(""))
        {
            dialog_textView_arrivalTime.setError("Time of arrival is required");
            result = false;
        }
        if (dialog_editText_arrivalDetails.getText().toString().trim().equals(""))
        {
            dialog_editText_arrivalDetails.setError("Details of arrival is required");
            result = false;
        }
        if (dialog_editText_placeOfStay.getText().toString().trim().equals(""))
        {
            dialog_editText_placeOfStay.setError("Place of Stay is required");
            result = false;
        }
        if (dialog_textView_departureTime.getText().toString().trim().equals(""))
        {
            dialog_textView_departureTime.setError("Departure time is required");
            result = false;
        }
        if (dialog_editText_departureDetails.getText().toString().trim().equals(""))
        {
            dialog_editText_departureDetails.setError("Departure Details is required");
            result = false;
        }
        if (dialog_editText_facebookID.getText().toString().trim().equals(""))
        {
            dialog_editText_facebookID.setError("Facebook ID is required");
            result = false;
        }
        if (dialog_editText_emailID.getText().toString().trim().equals(""))
        {
            dialog_editText_emailID.setError("Email ID is required");
            result = false;
        }

        if (dialog_radioGroup_arrival.getCheckedRadioButtonId() != -1)
        {
            if (dialog_radioButton_arr_other.isChecked() && dialog_editText_arr_other.getText().toString().trim().equals(""))
            {
                dialog_editText_arr_other.setError("Details required");
                result = false;
            }
        } else
        {
            dialog_radioButton_arr_other.setError("Mode of arrival is required");
            result = false;
        }

        if (dialog_radioGroup_departure.getCheckedRadioButtonId() != -1)
        {
            if (dialog_radioButton_dep_other.isChecked() && dialog_editText_dep_other.getText().toString().trim().equals(""))
            {
                dialog_editText_dep_other.setError("Details required");
                result = false;
            }
        } else
        {
            dialog_radioButton_dep_other.setError("Mode of departure is required");
            result = false;
        }
        return result;
    }

    public void showTruitonTimePickerDialog(View v)
    {
        android.support.v4.app.DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(this.getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showTruitonDatePickerDialog(View v)
    {
        android.support.v4.app.DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends android.support.v4.app.DialogFragment implements
            TimePickerDialog.OnTimeSetListener
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current time as the default values for the picker
            final Calendar c;
            int hour;
            int minute;
            if (date.equals(null))
            {
                c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);
            } else
            {
                hour = date.getHours();
                minute = date.getMinutes();
            }

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }


        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            // Do something with the time chosen by the user
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            date.setHours(hourOfDay);
            date.setMinutes(minute);
            date.setSeconds(0);

            dialog_textView_selected.setText(dateFormat.format(date));
        }
    }

    public static class DatePickerFragment extends android.support.v4.app.DialogFragment implements
            DatePickerDialog.OnDateSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current date as the default date in the picker

            final Calendar c;
            int year;
            int month;
            int day;
            if (date.equals(null))
            {
                c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            } else
            {
                year = date.getYear();
                month = date.getMonth();
                day = date.getDate();
            }

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            // Do something with the date chosen by the user

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            date = calendar.getTime();
        }
    }
}