package com.wsfmn.model;

import android.graphics.Bitmap;

import com.wsfmn.exceptions.HabitCommentTooLongException;
import com.wsfmn.exceptions.HabitEventCommentTooLongException;
import com.wsfmn.exceptions.HabitEventNameException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by skhanna1 on 10/16/17.
 */

public class HabitEvent{
    /**
     * Variables For the Habit Event that the user will enter or be created
     * when a user creates a new Habit Event
     */
    private String title;
    private Habit habit;
    private String comment;
    String id;
    Date date = null;
    private java.util.Date actualdate;
    //Path of the file Where image is stored
    String CurrentPhotoPath;
    Bitmap imageBitmap;

    //change by wei, change location parts
    private Geolocation geolocation;


    public HabitEvent(){
        this.title = "";
    }

    /**
     * Constructor for the Habit Event
     * @param habit
     * @param title
     * @param comment
     * @param CurrentPhotoPath
     *
     */
    public HabitEvent(Habit habit, String title, String comment, String CurrentPhotoPath, Date date) throws HabitCommentTooLongException,
            HabitEventCommentTooLongException, ParseException {
        this.habit = habit;
        this.title = title;
        setComment(comment);
        this.CurrentPhotoPath = CurrentPhotoPath;
        this.id = null;
        this.date = date;
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date adate = formatter.parse(this.date.toDateString());
        this.actualdate = adate;


        this.geolocation = geolocation;
        this.imageBitmap = null;
    }

    /**
     * Get the date of when the HabitEvent was created
     * @return Date: Date of the HabitEvent
     */
    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public java.util.Date getActualDate() throws ParseException {


        return this.actualdate;
    }

    /**
     * Get the path of the file where image is stored for the habit Event
     * @return mCurrentPhotoPath: filename of the image
     */
    public String getCurrentPhotoPath(){
        return CurrentPhotoPath;
    }

    /**
     * Get the Habit the user selects for the HabitEvent
     * @return Habit
     */
    public Habit getHabitFromEvent(){
        return habit;
    }

    /**
     * Get the Habit title for the Habit Event
     * @return habit Title
     */
    public String getHabitTitle(){
        return this.habit.getTitle();
    }

    /**
     * /*Changes the Habit for the HabitEvent
     * @param habit
     */
    public void setHabit(Habit habit){
        this.habit = habit;
    }

    /**
     * Get Id for ElasticSearch
     * @return Id
     */
    public String getId() {return id;}

    /**
     * Set Id for ElasticSearch
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the Habit for the HabitEvent
     * @return habit
     */
    public Habit getHabit() {
        return habit;
    }

    /**
     * Get the Title of the Habit Event
     * @return title of the HabitEvent
     * @throws HabitEventNameException
     */
    public String getHabitEventTitle() throws HabitEventNameException{
        /*Checks the title length of the HabitEvent*/
        if(title.length() > 35 || title.length()<1){
            /*Throws Exception if violates the condition*/
            throw new HabitEventNameException();
        }
        return title;
    }

    /**
     * Change Title of the HabitEvent
     * @param title
     * @throws HabitEventNameException
     */
    public String setTitle(String title)throws HabitEventNameException{
        /*Checks the length of the title*/
        if(title.length() > 35 || title.length()<1){
            /*Throws this exception*/
            throw new HabitEventNameException();
        }
        return this.title = title;
    }

    /**
     * Get the comment for HabitEvent that user created
     * @return comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * /*Changing the Comment of Habit Event
     * @param comment
     * @throws HabitEventCommentTooLongException
     */
    public void setComment(String comment) throws HabitEventCommentTooLongException {
        /*Checking if comment size does not exceed 20 characters*/
        if(comment != null) {
            if (comment.length() > 20) {
            /* Throw HabitEventCommentTooLongException*/
                throw new HabitEventCommentTooLongException();
            }
        }
        this.comment = comment;
    }

    /**
     *
     * @param geolocation
     */
    public void setGeolocation(Geolocation geolocation){
        this.geolocation = geolocation;
    }

    /**
     *
     * @return
     */
    public Geolocation getGeolocation(){
        return geolocation;
    }

    /**
     *  Compares two String Dates
     *
     * @param otherDate the other date that compare with the calling objects' ate
     * @return int 0 if equal, -1 if the calling object's date is smaller than otherDate,
     *  1 otherwise.
     */
    public int compareDate(Date otherDate){
        return date.compareDate(otherDate);
    }

    @Override
    public String toString(){
        return title + "    " + this.date;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
}