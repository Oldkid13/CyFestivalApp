package Backend.DataTypes;

import java.time.LocalDateTime;

/**
 * <p>
 * This is the main event class.
 * It handles the creation of standard event.
 * Also check equality between StandardEvent objects.
 * </p>
 */

public class StandardEvent implements I_ReadOnlyEvent {
    private String name;
    private LocalDateTime date;
    private String description;
    private int duration;

    /**
     * Class constructor.
     *
     * @param name        name of the event
     * @param date        date of the event. Format "YYYY-MM-DDTHH:MM:SS"
     * @param description description of the event
     * @param duration    duration of the event in hours
     */
    StandardEvent(String name, LocalDateTime date, String description, int duration) {
        setName(name);
        setDate(date);
        setDescription(description);
        setDuration(duration);
    }

    /**
     * Check name and date for equality
     *
     * @param object takes StandardEvent object
     * @return on success true, on failure false
     */
    @Override
    public boolean equals(Object object) {
        boolean result = false;

        if (object instanceof StandardEvent other) {
            boolean isNameEqual = getName().equals(other.getName());
            boolean isDateEqual = getDate().equals(other.getDate());

            if (isNameEqual && isDateEqual) {
                result = true;
            }
        }

        return result;

    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name Event name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date Event Data - YYYY-MM-DDTHH:MM:SS
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @param description Event description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration Event duration in hours
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
