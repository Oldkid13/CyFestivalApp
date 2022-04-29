package Backend.Facede;

import Backend.DataTypes.Festival;
import Backend.DataTypes.I_ReadOnlyEvent;
import Backend.DataTypes.StandardEvent;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * This class;
 * get inputs from interface,
 * check user inputs,
 * send signals to festival class and
 * contains the list of festivals
 */
public class BackendFacedeSingleton implements I_DataReadFacede, I_DataAddFacede
{
    private static BackendFacedeSingleton selfInstance;
    private ArrayList<Festival> festivals;

    /**
     * Class constructor.
     * Initialize festival list
     */
    private BackendFacedeSingleton()
    {
        festivals = new ArrayList<>();
    }

    /**
     * We are using singleton pattern for this class so this method responsible for handling the class instance
     * @return instance of this class
     */
    public static BackendFacedeSingleton instance()
    {
        if(Objects.isNull(selfInstance))
        {
            selfInstance = new BackendFacedeSingleton();
        }

        return selfInstance;
    }

    @Override
    public ArrayList<String> eventNamesByFestivalID(int festivalID)
    {
        Festival currentFestival;

        try
        {
            currentFestival = festivals.get(festivalID);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();

        for(StandardEvent currentEvent : currentFestival.getEvents())
        {
            result.add(currentEvent.getName());
        }

        return result;
    }

    @Override
    public HashMap<String, Map.Entry<LocalDateTime, Integer>> eventNamesAndDatesByFestivalID(int festivalID)
    {
        Festival currentFestival;

        try
        {
            currentFestival = festivals.get(festivalID);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return null;
        }

        HashMap<String, Map.Entry<LocalDateTime, Integer>> result = new HashMap<>();

        for(StandardEvent currentEvent : currentFestival.getEvents())
        {
            Map.Entry<LocalDateTime, Integer> currentPair = Map.entry(currentEvent.getDate(), currentEvent.getDuration());
            result.put(currentEvent.getName(), currentPair);
        }

        return result;
    }

    @Override
    public ArrayList<I_ReadOnlyEvent> eventDetailsByFestivalID(int festivalID)
    {
        Festival currentFestival;

        try
        {
            currentFestival = festivals.get(festivalID);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return null;
        }

        ArrayList<I_ReadOnlyEvent> result = new ArrayList<>(currentFestival.getEvents());

        return result;
    }

    @Override
    public ArrayList<String> festivalNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        for(Festival festival:festivals)
        {
            names.add(festival.getName());
        }
        return names;
    }

    @Override
    public  int createFestival(@NotNull String name,
                               @NotNull String place,
                               @NotNull String dateFormat,
                               int duration)
    {
        if(name.isEmpty() || name.isBlank() ||
           place.isEmpty() || place.isBlank() ||
           duration <= 0)
        {
            return -1;
        }

        LocalDate date;
        try
        {
            date = LocalDate.parse(dateFormat);
        }
        catch (DateTimeParseException ex)
        {
            return -1;
        }

        Festival item = new Festival(name, place, date, duration);
        festivals.add(item);

        return festivals.size() - 1;
    }

    @Override
    public  boolean addOrganizatorToFestival(int festivalIndex,
                                             @NotNull String firstName,
                                             @NotNull String lastName,
                                             @NotNull String email,
                                             @NotNull String phoneNumber)
    {
        if(firstName.isEmpty() || firstName.isBlank() ||
           lastName.isEmpty() || lastName.isBlank() ||
           email.isEmpty() || email.isBlank() ||
           phoneNumber.isEmpty() || phoneNumber.isBlank())
        {
            return false;
        }

        Festival parentFestival;

        try
        {
            parentFestival = festivals.get(festivalIndex);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return false;
        }

        boolean result = parentFestival.addOrganizator(firstName, lastName, email, phoneNumber);

        return result;
    }

    @Override
    public  boolean addStandardEventToFestival(int festivalIndex,
                                               @NotNull String name,
                                               @NotNull String dateFormat,
                                               @NotNull String description,
                                               int duration)
    {
        if(name.isEmpty() || name.isBlank() ||
           dateFormat.isEmpty() || dateFormat.isBlank() ||
           description.isEmpty() || dateFormat.isBlank() ||
           duration <= 0)
        {
            return false;
        }

        LocalDateTime date;

        try
        {
            date = LocalDateTime.parse(dateFormat);
        }
        catch (DateTimeParseException ex)
        {
            return false;
        }

        Festival parentFestival;

        try
        {
            parentFestival = festivals.get(festivalIndex);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return false;
        }

        if(date.toLocalDate().isAfter(parentFestival.getEndDate()) ||
           date.toLocalDate().isBefore(parentFestival.getStartDate()))
        {
            return false;
        }

        boolean result = parentFestival.addStandardEvent(name,date,description,duration);
        return result;
    }

    @Override
    public boolean addPerformenceEventToFestival(int festivalIndex,
                                                  @NotNull String name,
                                                  @NotNull String dateFormat,
                                                  @NotNull String description,
                                                  int duration,
                                                  @NotNull String... performers)
    {
        if(name.isEmpty() || name.isBlank() ||
           dateFormat.isEmpty() || dateFormat.isBlank() ||
           description.isEmpty() || dateFormat.isBlank() ||
           duration <= 0 ||
           performers.length == 0)
        {
            return false;
        }

        LocalDateTime date;

        try
        {
            date = LocalDateTime.parse(dateFormat);
        }
        catch (DateTimeParseException ex)
        {
            return false;
        }

        Festival parentFestival;

        try
        {
            parentFestival = festivals.get(festivalIndex);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return false;
        }

        if(date.toLocalDate().isAfter(parentFestival.getEndDate()) ||
           date.toLocalDate().isBefore(parentFestival.getStartDate()))
        {
            return false;
        }

        ArrayList<String> performersAsObj = new ArrayList<String>();
        performersAsObj.addAll(Arrays.asList(performers));

        boolean result = parentFestival.addPerformanceEvent(name,date,description,duration,performersAsObj);
        return result;
    }
    @Override
    public boolean addPerformerToPerformanceEvent(int festivalIndex,
                                                  @NotNull  String eventName,
                                                  @NotNull String eventDate,
                                                  @NotNull  String performerName)
    {
        Festival currentFestival;

        try
        {
            currentFestival = festivals.get(festivalIndex);
        }
        catch (IndexOutOfBoundsException ex)
        {
            return false;
        }

        LocalDateTime convertedDate;

        try
        {
            convertedDate = LocalDateTime.parse(eventDate);
        }
        catch (DateTimeParseException exp)
        {
            return false;
        }

        boolean result = currentFestival.appendPerformerToPerformanceEvent(eventName, convertedDate, performerName);

        return result;
    }
}
