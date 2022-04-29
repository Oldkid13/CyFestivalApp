package Backend.DataTypes;

import Backend.NotificationSubSystem.AbstractNotificationStrategyObserver;
import Backend.NotificationSubSystem.NotificationStrategyObserverFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the main data type class
 * This class is responsible with creating and handling data types and their interactions with other packages
 */
public class Festival {
    private String name;
    private String place;
    private LocalDate startDate;

    private LocalDate endDate;
    private int duration;

    private ArrayList<Organizator> organizators;
    private ArrayList<StandardEvent> events;
    private ArrayList<AbstractNotificationStrategyObserver> notifiers;

    /**
     * Class Constructor. This will create a festival with organizators and events
     *
     * @param name      name of the festival
     * @param place     the location of the festival
     * @param startDate festivals start date
     * @param duration  total duration of the festival in days
     *                  Constructor also creates an end date for the festival with help of duration and start date.
     */
    public Festival(String name,
                    String place,
                    LocalDate startDate,
                    int duration) {
        setName(name);
        setPlace(place);
        setStartDate(startDate);
        setEndDate(startDate.plusDays(duration));
        setDuration(duration);
        organizators = new ArrayList<>();
        events = new ArrayList<>();
        notifiers = NotificationStrategyObserverFactory.createAvailableNotifierList();
    }

    /**
     * This method finds index for newly added unique event.
     * This helps to store events in chronological order
     *
     * @param event a Standard or Performance event
     * @return index of the new event ,If event already exist return -1
     */
    private int findIndexForNewEvent(StandardEvent event) {
        int result = -1;

        if (events.isEmpty()) {
            result = 0;
        } else if (!events.contains(event)) {
            boolean shouldInsertToLastIndex = true;
            for (int i = 0; i < events.size(); i++) {
                if (event.getDate().isBefore(events.get(i).getDate())) {
                    result = i;
                    shouldInsertToLastIndex = false;
                    break;
                }
            }

            if (shouldInsertToLastIndex) {
                result = events.size();
            }
        }

        return result;
    }

    /**
     * Algorithm template for adding Standard and Performance events to the event list
     *
     * @param event a Standard or Performance event
     * @return on success true, on failure false
     */
    private boolean addEventAlgorithmTemplate(StandardEvent event)
    {
        int index = findIndexForNewEvent(event);
        if (index != -1) {
            events.add(index, event);

            return true;
        }

        return false;
    }

    /**
     * This method create and a standard event in to event list
     * Also Notify organizators if event added successfully
     *
     * @param name        name for StandardEvent
     * @param date        event date for StandardEvent
     * @param description a small description for StandardEvent
     * @param duration    event duration in hours for StandardEvent
     * @return on success true, on failure false
     */
    public boolean addStandardEvent(String name, LocalDateTime date, String description, int duration) {
        StandardEvent item = new StandardEvent(name, date, description, duration);
        boolean result = addEventAlgorithmTemplate(item);

        if (result) {
            for (AbstractNotificationStrategyObserver currentNotifier : notifiers) {
                currentNotifier.notifyOrganizatorForStandardEvent(item, getOrganizators());
            }
        }

        return result;
    }

    /**
     * crate and add a performance event to event list
     * Also Notify organizators if event added successfully
     *
     * @param name        name for PerformanceEvent
     * @param date        event date for PerformanceEvent
     * @param description a small description for PerformanceEvent
     * @param duration    event duration in hours for PerformanceEvent
     * @param performers  attended performer list for PerformanceEvent
     * @return on success true, on failure false
     */
    public boolean addPerformanceEvent(String name, LocalDateTime date, String description, int duration, ArrayList<String> performers) {
        PerformanceEvent item = new PerformanceEvent(name, date, description, duration, performers);
        boolean result = addEventAlgorithmTemplate(item);

        if (result) {
            for (AbstractNotificationStrategyObserver currentNotifier : notifiers) {
                currentNotifier.notifyOrganizatorForPerformanceEvent(item, getOrganizators());
            }
        }

        return result;
    }

    /**
     * Append a performer to already existing event
     *
     * @param eventName     name of the event you want to edit
     * @param eventDate     date of the event you want to edit
     * @param performerName name of the new performer
     * @return on success true, on failure false
     */
    public boolean appendPerformerToPerformanceEvent(String eventName, LocalDateTime eventDate, String performerName) {
        for (StandardEvent currentEvent : getEvents()) {
            if (currentEvent instanceof PerformanceEvent &&
                    currentEvent.getName().equals(eventName) &&
                    currentEvent.getDate().isEqual(eventDate)) {
                ((PerformanceEvent) currentEvent).addPerformer(performerName);
                return true;
            }
        }

        return false;
    }

    /**
     * Add a new organizator to festival if not already exist
     *
     * @param name        first and middle name for organizator
     * @param lastName    surname for organizator
     * @param email       email address for organizator
     * @param phoneNumber phone number for organizator
     * @return on success true, on failure false
     */
    public boolean addOrganizator(String name, String lastName, String email, String phoneNumber) {
        boolean result = false;

        Organizator item = new Organizator(name, lastName, email, phoneNumber);

        if (!organizators.contains(item)) {
            organizators.add(item);
            result = true;
        }

        return result;
    }

    /**
     * @return festival name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name festival name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return name of the festivals location.
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place name of the festivals location.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return Festivals start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return Festival end date. This is automatically generated.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate Festival end date. This is automatically generated.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @param startDate Festivals start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Festivals duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration Festivals duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return an unmodifiable list of organizators
     */
    public List<Organizator> getOrganizators() {
        List<Organizator> result = Collections.unmodifiableList(organizators);
        return result;
    }

    /**
     * @return an unmodifiable list of events
     */
    public List<StandardEvent> getEvents() {
        List<StandardEvent> result = Collections.unmodifiableList(events);
        return result;
    }
}
