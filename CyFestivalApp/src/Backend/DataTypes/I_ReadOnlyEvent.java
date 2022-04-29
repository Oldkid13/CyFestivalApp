package Backend.DataTypes;

import java.time.LocalDateTime;

/**
 * This interface limits standard event access to getters
 */
public interface I_ReadOnlyEvent {
    /**
     * @return Event name in string
     */
    String getName();

    /**
     * @return Event date in LocalDateTime : YYYY-MM-DDTHH:MM:SS
     */
    LocalDateTime getDate();

    /**
     * @return Event description in string
     */
    String getDescription();

    /**
     * @return integer duration value represent duration in hours
     */
    int getDuration();
}
