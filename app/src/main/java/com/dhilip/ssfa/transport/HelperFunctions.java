package com.dhilip.ssfa.transport;

import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dhilip on 6/11/2016.
 */
public class HelperFunctions
{
    public static Date GetDateFrom(String dateString, String dateFormat)
    {
        SimpleDateFormat currentDateFormat = new SimpleDateFormat(dateFormat);
        Date convertedDate = new Date();
        try
        {
            convertedDate = currentDateFormat.parse(dateString);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String GetStringFrom(Date date, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    public static String ConvertDateString(String dateString, String fromFormat, String toFormat)
    {
        Date dateObj = GetDateFrom(dateString, fromFormat);
        return GetStringFrom(dateObj, toFormat);
    }

    public static void setDynamicHeight(ListView mListView)
    {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null)
        {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = MeasureSpec.makeMeasureSpec(mListView.getWidth(), MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < mListAdapter.getCount(); i++)
        {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }
}
