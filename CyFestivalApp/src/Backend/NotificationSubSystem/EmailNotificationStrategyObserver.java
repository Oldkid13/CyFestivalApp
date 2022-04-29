package Backend.NotificationSubSystem;

import Backend.DataTypes.Organizator;

public class EmailNotificationStrategyObserver extends AbstractNotificationStrategyObserver
{
    @Override
    public void printNotification(String message, Organizator organizator)
    {
        System.out.println("\tTo : " + organizator.getEmail());
        System.out.println(message);
    }

    @Override
    public void printTransactionStartForStandardEvent()
    {
        System.out.println("<----- Started sending E-Mail for Standard Event ----->");
    }

    @Override
    public void printTransactionEndForStandardEvent()
    {
        System.out.println("<----- Successfully sent E-Mail for Standard Event ----->\n");
    }

    @Override
    public void printTransactionStartForPerformanceEvent()
    {
        System.out.println("<----- Started sending E-Mail for Performance Event ----->");
    }

    @Override
    public void printTransactionEndForPerformanceEvent()
    {
        System.out.println("<----- Successfully sent E-Mail for Performance Event ----->\n");
    }
}
