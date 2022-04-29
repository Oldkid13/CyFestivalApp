package Backend.DataTypes;

import java.util.List;

/**
 * Extension of Read only event for performance event
 * This additionally add access to performers getter
 */
public interface I_ReadOnlyPerformanceEvent extends I_ReadOnlyEvent {
    /**
     * This function change Arraylist Performers to List performers
     *
     * @return an unmodifiable string list of performers in the event
     */
    List<String> getPerformers();
}
