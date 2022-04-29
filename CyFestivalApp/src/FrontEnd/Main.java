package FrontEnd;

import Backend.DataTypes.I_ReadOnlyEvent;
import Backend.DataTypes.I_ReadOnlyPerformanceEvent;
import Backend.Facede.BackendFacedeSingleton;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This class contains main loop and menu options alsa create app object.
 */
public class Main {

    /**
     * This is where the main loop starts and where we initialize main app and festivals
     * @param args We don't use this parameter
     */
    public static void main(String[] args) {
        BackendFacedeSingleton appBackend = BackendFacedeSingleton.instance();

        appBackend.createFestival("Güzelyurt Orange Festival",
                "Güzelyurt",
                "2019-06-25",
                15);
        appBackend.addOrganizatorToFestival(0,
                "Mete",
                "Kaan",
                "mk@gmail.com",
                "+901");
        appBackend.addOrganizatorToFestival(0,
                "Sezin",
                "Seven",
                "sz@gmail.com",
                "+902");
        appBackend.addPerformenceEventToFestival(0,
                "Opening Concert",
                "2019-06-25T22:00",
                "Kick off with a popular pop singer",
                2,
                "Murat Boz");
        appBackend.addPerformenceEventToFestival(0,
                "Folk Dancing Show",
                "2019-06-26T20:00",
                "Popular folk dancers in Güzelyurt",
                1,
                "Ali Hakan", "Ferda Say", "Hale Anil", "Ahmet Yilmaz");


        appBackend.createFestival("Tepebaşı Tulip Festival",
                "Tepebaşı",
                "2019-04-24",
                5);
        appBackend.addOrganizatorToFestival(1,
                "Kenan",
                "Soylu",
                "ks@gmail.com",
                "+903");
        appBackend.addOrganizatorToFestival(1,
                "Selma",
                "Yarin",
                "sy@gmail.com",
                "+904");
        appBackend.addStandardEventToFestival(1,
                "Walking to see Tulips",
                "2019-04-24T10:00",
                "2-hours walk as a group",
                2);
        appBackend.addPerformenceEventToFestival(1,
                "Opening Concert",
                "2019-04-25T14:00",
                "A Concert by a national singer",
                2,
                "Fikri Karayel");

        ArrayList<String> names = appBackend.festivalNames();

        int option = 0;
        int festivalindex = 0;

        Scanner scanner = new Scanner(System.in);

        while( option != -1)
        {
            Main.printFestivalMenu(appBackend.festivalNames());

            try
            {
                festivalindex = scanner.nextInt();
            }
            catch (InputMismatchException ex)
            {
                System.out.println("\t ERROR: Please enter a integer. \n");
                scanner.next(); // Flush scanner buffer.
                continue;
            }

            if (festivalindex == -1) break;
            else if (festivalindex >= names.size() || festivalindex < 0)
            {
                System.out.println("Please select a correct option");
            }
            else
            {
                Main.printEventMenu();
                try
                {
                    option = scanner.nextInt();
                }
                catch (InputMismatchException ex)
                {
                    System.out.println("\t ERROR: Please enter a integer. \n");
                    scanner.next(); // Flush scanner buffer.
                    continue;
                }

                if(option==1) // Print event names only for current festival.
                {
                    System.out.println("Event Names: \n");
                    ArrayList<String> eventNames;
                    eventNames = appBackend.eventNamesByFestivalID(festivalindex);
                    for (String name : eventNames)
                    {
                        System.out.println(name);
                    }
                    System.out.println("---------------------");
                }
                else if (option == 2) // Print event names and dates for current festival.
                {
                    HashMap <String, Map.Entry<LocalDateTime, Integer>>result;
                    result = appBackend.eventNamesAndDatesByFestivalID(festivalindex);
                    Main.printEventNamesWithDates(result);
                }
                else if (option == 3) // Print complete event details (with performers if valid) for current festival.
                {
                    ArrayList<I_ReadOnlyEvent> result = appBackend.eventDetailsByFestivalID(festivalindex);
                    Main.printCompleteEventDetails(result);
                }
                else if (option == 4) // If go back selected.
                {
                    continue;
                }
                else
                {
                    System.out.println("Please select a correct option");
                }
            }
        }
    }

    /**
     * This method prints options for selected festival
     */
    private static void printEventMenu()
    {
        System.out.println("Please choose a version");
        System.out.println("1 - Get event names");
        System.out.println("2 - Get event names and dates");
        System.out.println("3 - Get event names,dates and details");
        System.out.println("4 - Go back");
        System.out.println("To exit enter -1");
        System.out.println("-------------------\n");
        System.out.println("Enter version selection: ");
    }

    /**
     * This method prints available festivals
     * @param names Gets festival names as arraylist from app
     */
    private static void printFestivalMenu(@NotNull ArrayList<String> names)
    {
        System.out.println("Choose a festival");
        for (int i = 0; i < names.size(); i++)
        {
            System.out.format("%d - %s\n",i,names.get(i));
        }
        System.out.println("To exit enter -1");
        System.out.println("------------------");
        System.out.println("Enter festival selection: ");
    }

    /**
     * This method prints events with their date and duration information
     * @param queryResult Get event and associated date and duration info as hash map
     */
    private static void printEventNamesWithDates(@NotNull
                                                 HashMap <String,Map.Entry<LocalDateTime, Integer>> queryResult)
    {
        System.out.println("Event names and dates: \n");

        for(String currentEventName : queryResult.keySet())
        {
            Map.Entry item = queryResult.get(currentEventName);
            LocalDateTime dateTime = (LocalDateTime) item.getKey();
            int duration = (Integer) item.getValue();
            System.out.format("%s will start at %s - %s and last for %d hours. \n",
                              currentEventName,
                              dateTime.toLocalDate().toString(),
                              dateTime.toLocalTime().toString(),
                              duration);
        }
    }

    /**
     * This method prints Event in the festival with all the details
     * @param queryResult Get a read only event object list
     */
    private static void printCompleteEventDetails(@NotNull List<I_ReadOnlyEvent> queryResult)
    {
        for(I_ReadOnlyEvent currentEvent : queryResult)
        {
            String firstLine = "%s will start at %s - %s and last for %d hours. \n";
            String secondLine = "\t Description: %s\n";
            String thirdLine = "\t Performers: %s\n";
            System.out.format(firstLine, currentEvent.getName(),
                    currentEvent.getDate().toLocalDate().toString(),
                    currentEvent.getDate().toLocalTime().toString(),
                    currentEvent.getDuration());
            System.out.format(secondLine, currentEvent.getDescription());

            // Print performers
            if(currentEvent instanceof I_ReadOnlyPerformanceEvent)
            {
                I_ReadOnlyPerformanceEvent performanceEvent = (I_ReadOnlyPerformanceEvent) currentEvent;
                String performers = "";
                List<String> performerList = performanceEvent.getPerformers();

                for(String currentPerformer : performerList)
                {
                    performers = performers.concat("\n\t\t -> " + currentPerformer);
                }

                System.out.printf(thirdLine, performers);
            }
        }
    }
}
