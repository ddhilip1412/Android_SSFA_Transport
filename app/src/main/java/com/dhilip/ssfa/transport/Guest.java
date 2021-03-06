package com.dhilip.ssfa.transport;

import java.io.Serializable;

/**
 * Created by Dhilip on 5/28/2016.
 */
public class Guest implements Serializable
{
    private int id;
    private String name;
    private String othersCount;
    private String otherDetails;
    private String hometown;
    private String placeOfStay;
    private String modeOfTravel;
    private String timeOfTravel;
    private String detailsOfTravel;
    private String contactNo;
    private String emailID;
    private boolean isArtist;
    private boolean isDone;
    private boolean isDeparture;

    public Guest()
    {
    }

    public Guest(String name, String othersCount, String otherDetails, String hometown, String placeOfStay, String modeOfTravel, String timeOfTravel, String detailsOfTravel, String contactNo, String emailID, boolean isArtist, boolean isDeparture, boolean isDone)
    {
        this.name = name;
        this.othersCount = othersCount;
        this.otherDetails = otherDetails;
        this.hometown = hometown;
        this.placeOfStay = placeOfStay;
        this.modeOfTravel = modeOfTravel;
        this.timeOfTravel = timeOfTravel;
        this.detailsOfTravel = detailsOfTravel;
        this.contactNo = contactNo;
        this.emailID = emailID;
        this.isArtist = isArtist;
        this.isDeparture = isDeparture;
        this.isDone = isDone;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOthersCount()
    {
        return othersCount;
    }

    public void setOthersCount(String othersCount)
    {
        this.othersCount = othersCount;
    }

    public String getHometown()
    {
        return hometown;
    }

    public void setHometown(String hometown)
    {
        this.hometown = hometown;
    }

    public String getModeOfTravel()
    {
        return modeOfTravel;
    }

    public void setModeOfTravel(String modeOfTravel)
    {
        this.modeOfTravel = modeOfTravel;
    }

    public String getTimeOfTravel()
    {
        return timeOfTravel;
    }

    public void setTimeOfTravel(String timeOfTravel)
    {
        this.timeOfTravel = timeOfTravel;
    }

    public String getDetailsOfTravel()
    {
        return detailsOfTravel;
    }

    public void setDetailsOfTravel(String detailsOfTravel)
    {
        this.detailsOfTravel = detailsOfTravel;
    }

    public String getPlaceOfStay()
    {
        return placeOfStay;
    }

    public void setPlaceOfStay(String placeOfStay)
    {
        this.placeOfStay = placeOfStay;
    }

    public String getContactNo()
    {
        return contactNo;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public String getOtherDetails()
    {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails)
    {
        this.otherDetails = otherDetails;
    }

    public String getEmailID()
    {
        return emailID;
    }

    public void setEmailID(String emailID)
    {
        this.emailID = emailID;
    }

    public boolean IsArtist()
    {
        return isArtist;
    }

    public void setIsArtist(boolean isArtist)
    {
        this.isArtist = isArtist;
    }

    public boolean isDeparture()
    {
        return isDeparture;
    }

    public void setIsDeparture(boolean isDeparture)
    {
        this.isDeparture = isDeparture;
    }

    public boolean isDone()
    {
        return isDone;
    }

    public void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    public boolean contains(CharSequence searchCharSequence)
    {
        boolean result = false;

        if (name.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (othersCount.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (otherDetails.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (hometown.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (placeOfStay.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (modeOfTravel.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (timeOfTravel.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (detailsOfTravel.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (contactNo.toLowerCase().contains(searchCharSequence))
            result = true;
        else if (emailID.toLowerCase().contains(searchCharSequence))
            result = true;

        return result;
    }
}
