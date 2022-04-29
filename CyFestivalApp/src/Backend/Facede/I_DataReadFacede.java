package Backend.Facede;

import Backend.DataTypes.I_ReadOnlyEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This interface give access to facades data read methods
 */
public interface I_DataReadFacede
{
    /**
     * This method returns the names of event in the given festival as an array list
     * @param festivalID index of festival you want to reach
     * @return ArrayList of event names
     */
    ArrayList<String> eventNamesByFestivalID(int festivalID);

    /**
     * This method returns event name, event date and event duration from given festival
     * @param festivalID index of festival you want to reach
     * @return a hashmap that contains event name and (event date, duration) tuple
     */
    HashMap<String, Map.Entry<LocalDateTime, Integer>> eventNamesAndDatesByFestivalID(int festivalID);

    /**
     * This method return a read only event object from given festival
     * @param festivalID index of the festival you want to reach
     * @return an event object that you can only access its getters
     * @see I_ReadOnlyEvent
     */
    ArrayList<I_ReadOnlyEvent> eventDetailsByFestivalID(int festivalID);

    /**
     * This function gets Festival names
     * @return festival names as string ArrayList
     */
    ArrayList<String> festivalNames();
}
