package Backend.NotificationSubSystem;

import Backend.DataTypes.Organizator;
import Backend.DataTypes.PerformanceEvent;
import Backend.DataTypes.StandardEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This abstract class creates a message template
 */
public abstract class AbstractNotificationStrategyObserver
{
    /**
     * This class handle standard events and prepare message for them
     * @param event
     * @param organizators
     */
    public final void notifyOrganizatorForStandardEvent(@NotNull StandardEvent event, @NotNull List<Organizator> organizators)
    {
        printTransactionStartForStandardEvent();
        printTransactionBodyForStandardEvent(event, organizators);
        printTransactionEndForStandardEvent();
    }

    /**
     * * This class handle performance events and prepare message for them
     * @param event
     * @param organizators
     */
    public final void notifyOrganizatorForPerformanceEvent(@NotNull PerformanceEvent event, @NotNull List<Organizator> organizators)
    {
        printTransactionStartForPerformanceEvent();
        printTransactionBodyForPerformanceEvent(event, organizators);
        printTransactionEndForPerformanceEvent();
    }

    /**
     * Template for standard event notification start message
     */
    public abstract void printTransactionStartForStandardEvent();

    /**
     * Template for standard event notification end message
     */
    public abstract void printTransactionEndForStandardEvent();

    /**
     * Template for sending message to organizator
     * @param message notification message
     * @param organizator The organizator object that receive the message
     */
    public abstract void printNotification(String message, Organizator organizator);

    /**
     * Template for performance event notification start message
     */
    public abstract void printTransactionStartForPerformanceEvent();

    /**
     * Template for performance event notification end message
     */
    public abstract void printTransactionEndForPerformanceEvent();

    /**
     * Notification message template for standard event
     * @param event Event object for getting message details
     * @param organizators Organizator list for reaching send info
     */
    public final void printTransactionBodyForStandardEvent(@NotNull StandardEvent event,
                                                           @NotNull List<Organizator> organizators)
    {
        String notification = "";
        notification += "\tName : " + event.getName() + "\n" +
                "\tDescription : " + event.getDescription() + "\n" +
                "\tDate : " + event.getDate().toString() + ", " +
                String.valueOf(event.getDuration()) + " hour" + "\n";
        for (Organizator organizator:organizators)
        {
            printNotification(notification, organizator);
        }
    }

    /**
     * Notification message template for standard event
     * @param event Event object for getting message details
     * @param organizators Organizator list for reaching send info
     */
    public final void printTransactionBodyForPerformanceEvent(@NotNull PerformanceEvent event,
                                                              @NotNull List<Organizator> organizators)
    {
        String notification = "";
        notification += "\tName : " + event.getName() + "\n" +
                "\tDescription : " + event.getDescription() + "\n" +
                "\tDate : " + event.getDate().toString() + ", " +
                String.valueOf(event.getDuration()) + " hour" + "\n" +
                "\tPerformers : " + String.join(",",event.getPerformers()) + "\n";
        for (Organizator organizator:organizators)
        {
            printNotification(notification, organizator);
        }
    }
}
