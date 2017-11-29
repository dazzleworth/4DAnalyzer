public class DateUtils {

  public static void bindDate( org.eclipse.swt.widgets.DateTime dtObject1, org.eclipse.swt.widgets.DateTime dtObject2) {
    dtObject1.setDay(dtObject2.getDay());
    dtObject1.setMonth(dtObject2.getMonth());
    dtObject1.setYear(dtObject2.getYear());
  }

  public static org.joda.time.DateTime makeJodaFromSWT(
                                org.eclipse.swt.widgets.DateTime widget) {
    return new org.joda.time.DateTime(widget.getYear(),
                        widget.getMonth() + 1,
                        widget.getDay(),
                        widget.getHours(),
                        widget.getMinutes(),
                        widget.getSeconds());
  }

  public static void updateSWTwithJoda(
                                org.eclipse.swt.widgets.DateTime widget,
                                org.joda.time.DateTime dateTime) {
    widget.setYear(dateTime.getYear());
    widget.setMonth(dateTime.getMonthOfYear());
    widget.setDay(dateTime.getDayOfMonth());
    widget.setHours(dateTime.getHourOfDay());
    widget.setMinutes(dateTime.getMinuteOfHour());
    widget.setSeconds(dateTime.getSecondOfMinute());
  }
}