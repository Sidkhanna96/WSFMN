package com.wsfmn.habittracker;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.Assert;

import java.util.Date;

/**
 * Created by musaed on 2017-10-21.
 */

public class HabitTest extends ActivityInstrumentationTestCase2 {

    public HabitTest(){
        super(Habit.class);
    }

    public void testGetTitle(){
        Habit habit = null;

        try {
            habit = new Habit("title", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }

        assertEquals(habit.getTitle(), "title");
    }

    public void testSetTitle(){
        Habit habit = null;
        try{
            habit = new Habit("a title that is more than twenty characters", new Date());
        }
        catch(HabitTitleTooLongException e) {
            assertNull("Title Constraint Enforcement Failed", habit);
        }

        try{
            habit = new Habit("title", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }


        assertEquals(habit.getTitle(), "title");
    }

    public void testGetReason(){
        Habit habit = null;

        try{
            habit = new Habit("title", "reason", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }
        catch(HabitReasonTooLongException e){
            //null
        }

        assertEquals(habit.getReason(), "reason");
    }

    public void testSetReason(){
        Habit habit = null;
        try{
            habit = new Habit("title", "a reason that contains more than thirty characters", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }
        catch(HabitReasonTooLongException e){
            assertNull("Reason Constraint Enforcement Failed", habit);
        }

        try{
            habit = new Habit("title", "reason", new Date());
        }
        catch(HabitTitleTooLongException e){
        }
        catch(HabitReasonTooLongException e){
        }


        assertEquals(habit.getReason(), "reason");
    }

    public void testGetId(){
        Habit habit = null;
        try{
            habit = new Habit("title", "reason", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }
        catch(HabitReasonTooLongException e){

        }

        assertNull("Habit ID was not null", habit.getId());
    }

    public void testSetId(){
        Habit habit = null;
        try{
            habit = new Habit("title", "reason", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }
        catch(HabitReasonTooLongException e){

        }

        habit.setId("My Unique ID");
        assertNotNull("Habit ID was null", habit.getId());
        assertEquals("Habit ID was not equal to the one set.", habit.getId(), "My Unique ID");
    }

    public void testToString() {
        Habit habit = null;
        Date date = new Date();
        try{
            habit = new Habit("title", "reason", new Date());
        }
        catch(HabitTitleTooLongException e){
            //null
        }
        catch(HabitReasonTooLongException e){

        }

        assertTrue("Habit as a string was incorrect", habit.toString().contains("title"+" "+date));
    }

}
