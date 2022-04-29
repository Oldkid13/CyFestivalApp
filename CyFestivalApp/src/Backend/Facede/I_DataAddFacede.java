package Backend.Facede;

import org.jetbrains.annotations.NotNull;

/**
 * This interface give access to facades data wright methods
 */
public interface I_DataAddFacede
{
    /**
     * This method;
     * check inputs,
     * create a festivel object,
     * add festival object to the festival list
     *
     * @param name name of the festival
     * @param place the place where the festival will be held
     * @param dateFormat string representation of LocalDate festival start date --- YYYY-MM-DD
     * @param duration duration of the festival in days
     * @return on failure -1, on success index of last element in the festival list
     */
    int createFestival(@NotNull String name,
                       @NotNull String place,
                       @NotNull String dateFormat,
                       int duration);

    /**
     * This method check inputs and add an organizator to a festival after creating the festival.
     *
     * @param festivalIndex index of the festival you want to edit
     * @param firstName first and middle name of the organizator
     * @param lastName surname of the organizator
     * @param email email address of the organizator
     * @param phoneNumber phone number of the organizator
     * @return on success true, on failure false
     */
    boolean addOrganizatorToFestival(int festivalIndex,
                                     @NotNull String firstName,
                                     @NotNull String lastName,
                                     @NotNull String email,
                                     @NotNull String phoneNumber);

    /**
     * This methot check inputs and seng signals to create a standard event in festivals vent list
     *
     * @param festivalIndex index of the festival you want to edit
     * @param name name of the event
     * @param dateFormat string representation of start date of the event
     * @param description description of the vent
     * @param duration duration of the event in hours
     * @return on success true, on failure false
     */
    boolean addStandardEventToFestival(int festivalIndex,
                                       @NotNull String name,
                                       @NotNull String dateFormat,
                                       @NotNull String description,
                                       int duration);

    /**
     * This methot check inputs and seng signals to create a performance event in festivals vent list
     *
     * @param festivalIndex index of the festival you want to edit
     * @param name name of the event
     * @param dateFormat string representation of start date of the event
     * @param description description of the vent
     * @param duration duration of the event in hours
     * @param performers a string list of the performers names joining to event
     * @return on success true, on failure false
     */
    boolean addPerformenceEventToFestival(int festivalIndex,
                                          @NotNull String name,
                                          @NotNull String dateFormat,
                                          @NotNull String description,
                                          int duration,
                                          @NotNull String... performers);

    /**
     * This method add an already created performance events performance list another performer.
     *
     * @param festivalIndex index of the festival you want to edit
     * @param eventName event name
     * @param eventDate event date
     * @param performerName name of the performer you want to add
     * @return on success true, on failure false
     */
    boolean addPerformerToPerformanceEvent(int festivalIndex,
                                           @NotNull  String eventName,
                                           @NotNull String eventDate,
                                           @NotNull  String performerName);


}
