package CalendarUPV;


import android.util.Log;


import java.text.SimpleDateFormat;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;


import org.jsoup.Jsoup;


import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventICAL
{

    //private final Pattern DATE_PATTERN = Pattern.compile("(\\d{4,})(\\d\\d)(\\d\\d)(?:T([0-1]\\d|2[0-3])([0-5]\\d)([0-5]\\d)(Z)?)?");

    private final Pattern BUILDING_PATTERN = Pattern.compile("([0-9][A-Za-z])");

    private final SimpleDateFormat simpleDateFormatDateTime = new SimpleDateFormat("EEE, dd MMM yyyy - HH:mm", Locale.getDefault());
    private final SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault());

    private final SimpleDateFormat simpleDateFormatOriginal = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault());

    private boolean isAllDay;

    private long id;

    private long dtend;
    private long dtstart;
    private String dtendFormat;
    private String dtstartFormat;
    private String dtendOriginal;
    private String dtstartOriginal;
    private String summary;
    private String uid;
    private String description;
    private String location;
    private String building;

    public EventICAL(Component component){
        this.parseComponent(component);
    }


    public EventICAL(){}

    private void parseComponent(Component component){

        try {

            this.uid = component.getProperty("UID").getValue();
            this.summary = Jsoup.parse(component.getProperty("SUMMARY").getValue()).text();
            this.description = Jsoup.parse(component.getProperty("DESCRIPTION").getValue()).text();
            this.location = Jsoup.parse(component.getProperty("LOCATION").getValue()).text();
            this.building = this.parseBuilding(this.location);

            this.dtend = simpleDateFormatOriginal.parse(component.getProperty("DTEND").getValue()).getTime();
            this.dtstart = simpleDateFormatOriginal.parse(component.getProperty("DTSTART").getValue()).getTime();

            this.dtendOriginal = component.getProperty("DTEND").getValue();
            this.dtstartOriginal = component.getProperty("DTSTART").getValue();

            if(this.isAllDay) {
                this.dtstartFormat = simpleDateFormatDate.format(new DateTime(this.dtstart));
                this.dtendFormat = simpleDateFormatDate.format(new DateTime(this.dtend));
            }else{
                this.dtstartFormat = simpleDateFormatDateTime.format(new DateTime(this.dtstart));
                this.dtendFormat = simpleDateFormatDateTime.format(new DateTime(this.dtend));
            }

            //the last one...
            this.isAllDay = this.isAllDay();

        } catch (Exception e) {
            Log.w(((Object) this).getClass().getName(), "Exception", e);
        }
    }

    private boolean isAllDay(){

        //Matcher matcherStart = DATE_PATTERN.matcher(this.dtstartFormat);
        //Matcher matcherEnd = DATE_PATTERN.matcher(this.dtendFormat);

        if (this.dtstartOriginal.contains("T000000") && this.dtendOriginal.contains("T000000")){
            return true;
        }

        return false;
    }

    private String parseBuilding(String string){

        Matcher matcher = this.BUILDING_PATTERN.matcher(string);

        if(matcher.find()){
            return matcher.group(1);
        }
        return string;
    }

    public void setAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public boolean getAllDay() {
        return this.isAllDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDtend() {
        return dtend;
    }

    public void setDtend(long dtend) {
        this.dtend = dtend;
    }

    public long getDtstart() {
        return dtstart;
    }

    public void setDtstart(long dtstart) {
        this.dtstart = dtstart;
    }

    public String getDtendFormat() {
        return dtendFormat;
    }

    public void setDtendFormat(String dtendFormat) {
        this.dtendFormat = dtendFormat;
    }

    public String getDtstartFormat() {
        return dtstartFormat;
    }

    public void setDtstartFormat(String dtstartFormat) {
        this.dtstartFormat = dtstartFormat;
    }

    public String getDtendOriginal() {
        return dtendOriginal;
    }

    public void setDtendOriginal(String dtendOriginal) {
        this.dtendOriginal = dtendOriginal;
    }

    public String getDtstartOriginal() {
        return dtstartOriginal;
    }

    public void setDtstartOriginal(String dtstartOriginal) {
        this.dtstartOriginal = dtstartOriginal;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.building = this.parseBuilding(this.location);
    }

    public String getBuilding() {
        return building;
    }


    @Override
    public String toString() {
        return "EventICAL{" +
                "isAllDay=" + isAllDay +
                ", id=" + id +
                ", dtend=" + dtend +
                ", dtstart=" + dtstart +
                ", dtendFormat='" + dtendFormat + '\'' +
                ", dtstartFormat='" + dtstartFormat + '\'' +
                ", dtendOriginal='" + dtendOriginal + '\'' +
                ", dtstartOriginal='" + dtstartOriginal + '\'' +
                ", summary='" + summary + '\'' +
                ", uid='" + uid + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}