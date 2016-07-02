package com.dhilip.ssfa.transport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Dhilip on 5/27/2016.
 */
public class FragmentAdd extends Fragment
{
    private final static String EMPTY_STRING = "";
    static EditText editText_arr_other;
    static TextView textView_arrivalTime;
    static EditText editText_dep_other;
    static TextView textView_departureTime;
    static TextView textView_selected;
    static RadioButton radioButton_arr_other;
    static RadioButton radioButton_dep_other;
    static Date date;
    View rootView;
    EditText editText_name;
    EditText editText_othersCount;
    EditText editText_hometown;
    EditText editText_arrivalDetails;
    EditText editText_placeOfStay;
    EditText editText_departureDetails;
    EditText editText_contactNo;
    EditText editText_facebookID;
    EditText editText_emailID;
    Button btnAdd;
    RadioGroup radioGroup_arrival;
    RadioGroup radioGroup_departure;
    CheckBox checkBox_isArtist;

    public static FragmentAdd newInstance()
    {
        return new FragmentAdd();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_add, container, false);

        InitializeInputControls();


        return rootView;
    }

    private void InitializeInputControls()
    {
        // Local variables

        // Initialization
        editText_emailID = (EditText) rootView.findViewById(R.id.editText_emailID);
        editText_name = (EditText) rootView.findViewById(R.id.editText_name);
        editText_othersCount = (EditText) rootView.findViewById(R.id.editText_othersCount);
        editText_hometown = (EditText) rootView.findViewById(R.id.editText_homeTown);
        editText_arr_other = (EditText) rootView.findViewById(R.id.editText_arr_other);
        textView_arrivalTime = (TextView) rootView.findViewById(R.id.editText_arrivalTime);
        editText_arrivalDetails = (EditText) rootView.findViewById(R.id.editText_arrivalDetails);
        editText_placeOfStay = (EditText) rootView.findViewById(R.id.editText_placeOfStay);
        editText_dep_other = (EditText) rootView.findViewById(R.id.editText_dep_other);
        textView_departureTime = (TextView) rootView.findViewById(R.id.editText_departureTime);
        editText_departureDetails = (EditText) rootView.findViewById(R.id.editText_departureDetails);
        editText_contactNo = (EditText) rootView.findViewById(R.id.editText_contactNo);
        editText_facebookID = (EditText) rootView.findViewById(R.id.editText_facebookID);
        btnAdd = (Button) rootView.findViewById(R.id.button_Add);
        radioButton_arr_other = (RadioButton) rootView.findViewById(R.id.radioButton_arr_other);
        radioButton_dep_other = (RadioButton) rootView.findViewById(R.id.radioButton_dep_other);
        radioGroup_arrival = (RadioGroup) rootView.findViewById(R.id.dialog_radioGroup_arrival);
        radioGroup_departure = (RadioGroup) rootView.findViewById(R.id.radioGroup_departure);
        checkBox_isArtist = (CheckBox) rootView.findViewById(R.id.checkBox_isArtist);

        AssignEventsForControls();
    }

    private void AssignEventsForControls()
    {
        // Event assignments
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (ValidateForm())
                {
                    WriteToDB(view.getContext());
                    ClearInputControls();
                }

            }
        });

        textView_arrivalTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textView_arrivalTime.setError(null);
                textView_selected = textView_arrivalTime;
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });
        textView_departureTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textView_departureTime.setError(null);
                textView_selected = textView_departureTime;
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });
        radioGroup_arrival.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                radioButton_arr_other.setError(null);
                if (radioButton_arr_other.isChecked())
                    editText_arr_other.setVisibility(View.VISIBLE);
                else
                    editText_arr_other.setVisibility(View.GONE);
            }
        });
        radioGroup_departure.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                radioButton_dep_other.setError(null);
                if (radioButton_dep_other.isChecked())
                    editText_dep_other.setVisibility(View.VISIBLE);
                else
                    editText_dep_other.setVisibility(View.GONE);
            }
        });

        // Remove Errors
        editText_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_name.setError(null);
            }
        });

        editText_emailID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_emailID.setError(null);
            }
        });

        editText_othersCount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_othersCount.setError(null);
            }
        });
        editText_hometown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_hometown.setError(null);
            }
        });
        editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_arr_other.setError(null);
            }
        });
        editText_arrivalDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_arrivalDetails.setError(null);
            }
        });
        editText_placeOfStay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_placeOfStay.setError(null);
            }
        });
        editText_dep_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_dep_other.setError(null);
            }
        });
        editText_departureDetails.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_departureDetails.setError(null);
            }
        });
        editText_contactNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_contactNo.setError(null);
            }
        });
        editText_facebookID.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_facebookID.setError(null);
            }
        });

        editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_dep_other.setError(null);
            }
        });
        editText_arr_other.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editText_dep_other.setError(null);
            }
        });
    }

    private void ClearInputControls()
    {
        editText_name.setText(EMPTY_STRING);
        editText_othersCount.setText(EMPTY_STRING);
        editText_hometown.setText(EMPTY_STRING);
        editText_arr_other.setText(EMPTY_STRING);
        textView_arrivalTime.setText(EMPTY_STRING);
        editText_arrivalDetails.setText(EMPTY_STRING);
        editText_placeOfStay.setText(EMPTY_STRING);
        editText_dep_other.setText(EMPTY_STRING);
        textView_departureTime.setText(EMPTY_STRING);
        editText_departureDetails.setText(EMPTY_STRING);
        editText_contactNo.setText(EMPTY_STRING);
        editText_facebookID.setText(EMPTY_STRING);
        editText_emailID.setText(EMPTY_STRING);

        checkBox_isArtist.setChecked(false);
        radioButton_arr_other.setChecked(true);
        radioButton_arr_other.setChecked(false);
        radioButton_dep_other.setChecked(true);
        radioButton_dep_other.setChecked(false);

        ScrollView scrollView_Add = (ScrollView) rootView.findViewById(R.id.scrollView_Add);
        scrollView_Add.fullScroll(ScrollView.FOCUS_UP);
    }

    private boolean ValidateForm()
    {
        boolean result = true;
        if (editText_name.getText().toString().trim().equals(""))
        {
            editText_name.setError("Name is required");
            result = false;
        }
        if (editText_othersCount.getText().toString().trim().equals(""))
        {
            editText_othersCount.setError("Count of others travelling is required");
            result = false;
        }
        if (editText_hometown.getText().toString().trim().equals(""))
        {
            editText_hometown.setError("Hometown is required");
            result = false;
        }
        if (textView_arrivalTime.getText().toString().trim().equals(""))
        {
            textView_arrivalTime.setError("Time of arrival is required");
            result = false;
        }
        if (editText_arrivalDetails.getText().toString().trim().equals(""))
        {
            editText_arrivalDetails.setError("Details of arrival is required");
            result = false;
        }
        if (editText_placeOfStay.getText().toString().trim().equals(""))
        {
            editText_placeOfStay.setError("Place of Stay is required");
            result = false;
        }
        if (textView_departureTime.getText().toString().trim().equals(""))
        {
            textView_departureTime.setError("Departure time is required");
            result = false;
        }
        if (editText_departureDetails.getText().toString().trim().equals(""))
        {
            editText_departureDetails.setError("Departure Details is required");
            result = false;
        }
        if (editText_facebookID.getText().toString().trim().equals(""))
        {
            editText_facebookID.setError("Facebook ID is required");
            result = false;
        }
        if (editText_emailID.getText().toString().trim().equals(""))
        {
            editText_emailID.setError("Email ID is required");
            result = false;
        }

        if (radioGroup_arrival.getCheckedRadioButtonId() != -1)
        {
            if (radioButton_arr_other.isChecked() && editText_arr_other.getText().toString().trim().equals(""))
            {
                editText_arr_other.setError("Details required");
                result = false;
            }
        } else
        {
            radioButton_arr_other.setError("Mode of arrival is required");
            result = false;
        }

        if (radioGroup_departure.getCheckedRadioButtonId() != -1)
        {
            if (radioButton_dep_other.isChecked() && editText_dep_other.getText().toString().trim().equals(""))
            {
                editText_dep_other.setError("Details required");
                result = false;
            }
        } else
        {
            radioButton_dep_other.setError("Mode of departure is required");
            result = false;
        }
        return result;
    }

    private void ShowToast(CharSequence message)
    {
        Toast toast = Toast.makeText(rootView.getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void WriteToDB(Context context)
    {
        // local variables
        TransDBHandler db = new TransDBHandler(context);
        String arrivalDetails;
        String departureDetails;

        // initialize local variables
        arrivalDetails = ((RadioButton) rootView.findViewById(radioGroup_arrival.getCheckedRadioButtonId())).getText().toString().trim();
        departureDetails = ((RadioButton) rootView.findViewById(radioGroup_departure.getCheckedRadioButtonId())).getText().toString().trim();

        //local operations
        if (arrivalDetails.equals(getString(R.string.travelMode_OTHER)))
            arrivalDetails = editText_arr_other.getText().toString().trim();
        if (departureDetails.equals(getString(R.string.travelMode_OTHER)))
            departureDetails = editText_dep_other.getText().toString().trim();
        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");

//        if (
        db.addGuest(new Guest(
                editText_name.getText().toString().trim(),
                editText_othersCount.getText().toString().trim(),
                editText_hometown.getText().toString().trim(),
                editText_placeOfStay.getText().toString().trim(),
                arrivalDetails,
                textView_arrivalTime.getText().toString().trim(),
                editText_arrivalDetails.getText().toString().trim(),
                editText_contactNo.getText().toString().trim(),
                editText_facebookID.getText().toString().trim(),
                editText_emailID.getText().toString().trim(),
                checkBox_isArtist.isChecked(),
                false,
                false));

        db.addGuest(new Guest(
                editText_name.getText().toString().trim(),
                editText_othersCount.getText().toString().trim(),
                editText_hometown.getText().toString().trim(),
                editText_placeOfStay.getText().toString().trim(),
                departureDetails,
                textView_departureTime.getText().toString().trim(),
                editText_departureDetails.getText().toString().trim(),
                editText_contactNo.getText().toString().trim(),
                editText_facebookID.getText().toString().trim(),
                editText_emailID.getText().toString().trim(),
                checkBox_isArtist.isChecked(),
                true,
                false));
    }

    public void showTruitonTimePickerDialog(View v)
    {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(this.getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showTruitonDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(this.getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

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

            textView_selected.setText(dateFormat.format(date));
        }
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

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

