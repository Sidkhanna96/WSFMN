package com.wsfmn.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.wsfmn.exceptions.HabitCommentTooLongException;
import com.wsfmn.exceptions.HabitEventCommentTooLongException;
import com.wsfmn.exceptions.HabitEventNameException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A HabitEvent is created when a user does one of their Habits.
 */
public class HabitEvent{

    private String title;                  //Title of habit event, will be the same its habit
    private String title_search;           //Title to be search on ES,
    private String owner;                  //Set the owner for the habit event
    private String photoStringEncoding;    //encoded string for the photo.
    private String photoPath;              // photo path of the picture taken
    private String comment;                // Optional comment for the habit event
    private String id;                     // Id used for ES, will be set once stored.
    private Habit habit;                   // Have the habit included in the habit event
    private Geolocation geolocation;       // Store geolocations, coordinates and address.
    private Date date = null;              // Our own version of Date, to be used for many functions.
    private java.util.Date actualdate;     // Actual date. Coverts our Date class in to java.util.Date


    /**
     * A blank HabitEvent
     */
    public HabitEvent(){
        this.title = "";
        this.title_search = "";
        this.date = new Date();
        IDGenerator idGenerator = new IDGenerator();
        this.id = idGenerator.nextString();
    }

    /**
     * A HabitEvent without a location
     *
     * @param habit
     * @param title
     * @param comment
     * @param photoStringEncoding
     * @param photoPath
     * @param date
     * @throws HabitCommentTooLongException
     * @throws HabitEventCommentTooLongException
     * @throws ParseException
     */
    public HabitEvent(Habit habit, String title, String comment, String photoStringEncoding,
                      String photoPath, Date date)
            throws HabitCommentTooLongException, HabitEventCommentTooLongException, ParseException {

        this.habit = habit;
        this.title = title;
        setComment(comment);
        this.photoStringEncoding = photoStringEncoding;
        IDGenerator idGenerator = new IDGenerator();
        this.id = idGenerator.nextString();
        this.date = date;
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date adate = formatter.parse(this.date.toDateString());
        this.actualdate = adate;
        this.geolocation = null;
        this.photoPath = photoPath;
    }

    /**
     * A complete HabitEvent
     *
     * @param habit that was done
     * @param title of that Habit
     * @param comment for this HabitEvent
     * @param photoStringEncoding a massive String that is actually an image
     * @param photoPath to the user's local copy of the photo
     * @param date of the HabitEvent
     * @param geolocation of the HabitEvent
     * @throws HabitCommentTooLongException
     * @throws HabitEventCommentTooLongException
     * @throws ParseException
     */
    public HabitEvent(Habit habit, String title, String comment, String photoStringEncoding,
                      String photoPath, Date date, Geolocation geolocation)
            throws HabitCommentTooLongException, HabitEventCommentTooLongException, ParseException {

        this.habit = habit;
        this.title = title;
        setComment(comment);
        this.photoStringEncoding = photoStringEncoding;
        IDGenerator idGenerator = new IDGenerator();
        this.id = idGenerator.nextString();
        this.date = date;
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date adate = formatter.parse(this.date.toDateString());
        this.actualdate = adate;
        this.geolocation = geolocation;
        this.photoPath = photoPath;
    }

    /**
     * Get the date of when the HabitEvent was created
     * @return Date: Date of the HabitEvent
     */
    public void setDate(Date date){
        this.date = date;
    }


    /**
     * Get the date Using our date method.
     * @return
     */
    public Date getDate(){
        return this.date;
    }


    /**
     * get the actual Date using java.util.Date
     * @return
     * @throws ParseException
     */
    public java.util.Date getActualDate() throws ParseException {


        return this.actualdate;
    }

    /**
     * The string-encoding of the photo for this HabitEvent.
     * @return
     */
    public String getPhotoStringEncoding(){
        return photoStringEncoding;
    }

    /**
     * Setting the String encoded for the Habitevent
     * @param photoStringEncoding
     */
    public void setPhotoStringEncoding(String photoStringEncoding){
        this.photoStringEncoding = photoStringEncoding;
    }

    /**
     * Get the photo path from the picture taken on the phone/emulator.
     * @return
     */
    public String getPhotoPath(){return photoPath;}

    /**
     * Set the photo path into Habitevent
     * @param photoPath
     */
    public void setPhotoPath(String photoPath){this.photoPath = photoPath;}

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
     * Get the title of the habit event.
     * @return
     */
    public String getTitle() {
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
     * Get the title used for online searching.
     * @return online search title
     */
    public String getSearchTitle() {
        return title_search;
    }

    /**
     * Set the searchable title based upon title.
     */
    public void setSearchTitle() {
        title_search = title.toLowerCase().replaceAll("\\s+", "").replaceAll("[^A-Za-z0-9]", "");
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
     * Set the geolocations after selecting a location.
     * This will add a location and a address.
     * @param geolocation
     */
    public void setGeolocation(Geolocation geolocation){
        this.geolocation = geolocation;
    }

    /**
     * Get back the geolocation.
     * Coordinates and address.
     * @return
     */
    public Geolocation getGeolocation(){
        return geolocation;
    }

    /**
     * Get the owner of habit event.
     * @return
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setting the owner of the habit event.
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
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

    /**
     * Able to make the object into a string to be printable
     * @return
     */
    @Override
    public String toString(){
        return title + "    " + this.date;
    }

    /**
     * Code Reuse: https://stackoverflow.com/questions/7620401/how-to-convert-byte-array-to-bitmap
     * @return
     */
    public Bitmap getImageBitmap() {
        if(photoStringEncoding!=null) {
            byte[] decodedString = Base64.decode(this.photoStringEncoding, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedImage;
        }
        return null;
    }
}