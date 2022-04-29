package Backend.NotificationSubSystem;

import java.util.ArrayList;

/**
 * This class responsible for creating available notification methods
 */
public class NotificationStrategyObserverFactory
{
    /**
     * This function create an instance of available notification observers
     * @return a list of available observers instances
     */
    public static ArrayList<AbstractNotificationStrategyObserver> createAvailableNotifierList()
    {
        ArrayList<AbstractNotificationStrategyObserver> result = new ArrayList<>();
        result.add(new EmailNotificationStrategyObserver());
        result.add(new SmsNotificationStrategyObserver());

        return result;
    }
}
