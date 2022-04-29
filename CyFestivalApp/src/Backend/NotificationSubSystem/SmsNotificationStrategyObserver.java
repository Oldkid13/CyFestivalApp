package Backend.NotificationSubSystem;

import Backend.DataTypes.Organizator;

public class SmsNotificationStrategyObserver extends AbstractNotificationStrategyObserver
{
    @Override
    public void printNotification(String message, Organizator organizator)
    {
        System.out.println("To : " + organizator.getPhoneNumber());
        System.out.println(message);
    }

    @Override
    public void printTransactionStartForStandardEvent()
    {
        System.out.println("<----- Started sending SMS for Standard Event ----->");
    }

    @Override
    public void printTransactionEndForStandardEvent()
    {
        System.out.println("<----- Successfully sent SMS for Standard Event ----->\n");
    }

    @Override
    public void printTransactionStartForPerformanceEvent()
    {
        System.out.println("<----- Started sending SMS for Performance Event ----->");
    }

    @Override
    public void printTransactionEndForPerformanceEvent()
    {
        System.out.println("<----- Successfully sent SMS for Performance Event ----->\n");
    }
}
