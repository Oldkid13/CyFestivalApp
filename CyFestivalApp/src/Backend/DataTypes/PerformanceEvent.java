package Backend.DataTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

/**
 * This class is child of StandardEvent
 */
public class PerformanceEvent extends StandardEvent implements I_ReadOnlyPerformanceEvent {
    private ArrayList<String> performers;

    /**
     * Class constructor
     *
     * @param name        name of the event
     * @param date        date of the event. Format "YYYY-MM-DDTHH:MM:SS"
     * @param description description of the event
     * @param duration    duration of the event as integer
     * @param performers  List of performers coming to event
     */
    PerformanceEvent(String name,
                     LocalDateTime date,
                     String description,
                     int duration,
                     ArrayList<String> performers) {
        super(name, date, description, duration);
        this.performers = new ArrayList<String>();
        this.performers.addAll(performers);
    }

    /**
     * Ada an additional performer to this object
     *
     * @param performer Performer name
     * @return on success true, on failure false
     */
    boolean addPerformer(String performer) {
        if (!performers.contains(performer)) {
            this.performers.add(performer);
            return true;
        }

        return false;
    }

    @Override
    public List<String> getPerformers() {
        List<String> result = unmodifiableList(this.performers);
        return result;
    }

}
