package com.planit.utils;

import com.google.gson.Gson;
import com.planit.Event;
import com.planit.Participant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Steven Curran on 25/03/2014.
 */
public class EventReader {

    private static Gson gson = new Gson();

    public static Event read(JSONObject object) {
        Event e = new Event();
        try {
            e.setId(object.getString("eventId"));
            e.setTitle(object.getString("name"));
            e.setStartDate(new Date(object.getLong("startDate")));
            e.setEndDate(new Date(object.getLong("endDate")));
            //do participant stuff here
            JSONArray attendees = object.getJSONArray("userEvents");
            ArrayList<Participant> ps = new ArrayList<>();

            for (int i = 0; i < attendees.length(); i++) {
                JSONObject o = attendees.getJSONObject(i);
                Participant p = gson.fromJson(o.toString(), Participant.class);
                ps.add(p);
            }

            e.setParticipants(ps);


        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        return e;
    }


}
