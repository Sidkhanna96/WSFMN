package com.wsfmn.habittracker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


/**
 * Created by nicholasmayne on 2017-10-16.
 */



public class  OnlineController {

    private static JestDroidClient client;


    /**
     * Created by romansky on 10/20/16. Edited by nmayne 10/22/17.
     */
    public static class StoreHabitsOnline extends AsyncTask<Habit, Void, Void> {

        @Override
        protected Void doInBackground(Habit... habits) {
            verifySettings();

            for (Habit habit : habits) {
                Index index = new Index.Builder(habit).index("testing").type("habit").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded())
                    {
                        habit.setId(result.getId().toString());
                    }
                    else
                    {
                        Log.i("Error","Elasticsearch was not able to add the habit");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Habit Tracker failed to build and send the habits");
                }

            }
            return null;
        }
    }

    /**
     * Created by romansky on 10/20/16. Edited by nmayne 10/22/17.
     */
    public static class GetHabitsOnline extends AsyncTask<String, Void, HabitList> {
        @Override
        protected HabitList doInBackground(String... search_parameters) {
            verifySettings();

            HabitList habits = new HabitList();

            String query =
                            "{\n" +
                            " \"query\": { \"term\": {\"title\":\"" + search_parameters[0] + "\"} },\n" +
                            " \"sort\": [\n" +
                            "  { \"date\": \"desc\" }\n" +
                            " ]\n" +
                            "}";

            Search search = new Search.Builder(query)
                    .addIndex("testing")
                    .addType("habit")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded())
                {
                    List<Habit> foundHabits = result.getSourceAsObjectList(Habit.class);
                    habits.addAllHabits(foundHabits);
                }
                else
                {
                    Log.i("Error","The search query failed");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return habits;
        }
    }

    /**
     * Created by romansky on 10/20/16. Edited by nmayne 10/22/17.
     */
    public static class StoreHabitEventsOnline extends AsyncTask<HabitEvent, Void, Void> {

        @Override
        protected Void doInBackground(HabitEvent... habitEvents) {
            verifySettings();

            for (HabitEvent habitEvent : habitEvents) {
                Index index = new Index.Builder(habitEvent).index("testing").type("habit").build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if(result.isSucceeded())
                    {
                        habitEvent.setId(result.getId().toString());
                    }
                    else
                    {
                        Log.i("Error","Elasticsearch was not able to add the habit");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Habit Tracker failed to build and send the habits");
                }

            }
            return null;
        }
    }


















    /**
     * Created by romansky on 10/20/16. Edited by nmayne 10/22/17.
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}