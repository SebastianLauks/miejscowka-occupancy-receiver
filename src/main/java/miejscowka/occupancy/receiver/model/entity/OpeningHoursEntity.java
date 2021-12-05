package miejscowka.occupancy.receiver.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "OPENING_HOURS")
public class OpeningHoursEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PLACE_ID")
    private Long placeId;

    @Column(name = "MONDAY_OPENING_HOUR")
    private String mondayOpeningHour;

    @Column(name = "MONDAY_CLOSING_HOUR")
    private String mondayClosingHour;

    @Column(name = "TUESDAY_OPENING_HOUR")
    private String tuesdayOpeningHour;

    @Column(name = "TUESDAY_CLOSING_HOUR")
    private String tuesdayClosingHour;

    @Column(name = "WEDNESDAY_OPENING_HOUR")
    private String wednesdayOpeningHour;

    @Column(name = "WEDNESDAY_CLOSING_HOUR")
    private String wednesdayClosingHour;

    @Column(name = "THURSDAY_OPENING_HOUR")
    private String thursdayOpeningHour;

    @Column(name = "THURSDAY_CLOSING_HOUR")
    private String thursdayClosingHour;

    @Column(name = "FRIDAY_OPENING_HOUR")
    private String fridayOpeningHour;

    @Column(name = "FRIDAY_CLOSING_HOUR")
    private String fridayClosingHour;

    @Column(name = "SATURDAY_OPENING_HOUR")
    private String saturdayOpeningHour;

    @Column(name = "SATURDAY_CLOSING_HOUR")
    private String saturdayClosingHour;

    @Column(name = "SUNDAY_OPENING_HOUR")
    private String sundayOpeningHour;

    @Column(name = "SUNDAY_CLOSING_HOUR")
    private String sundayClosingHour;


    @OneToOne
    @MapsId
    @JoinColumn(name = "PLACE_ID")
    private PlaceEntity place;

    public OpeningHoursEntity() {
    }

    public OpeningHoursEntity(Long placeId, String mondayOpeningHour, String mondayClosingHour, String tuesdayOpeningHour, String tuesdayClosingHour, String wednesdayOpeningHour, String wednesdayClosingHour, String thursdayOpeningHour, String thursdayClosingHour, String fridayOpeningHour, String fridayClosingHour, String saturdayOpeningHour, String saturdayClosingHour, String sundayOpeningHour, String sundayClosingHour, PlaceEntity place) {
        this.placeId = placeId;
        this.mondayOpeningHour = mondayOpeningHour;
        this.mondayClosingHour = mondayClosingHour;
        this.tuesdayOpeningHour = tuesdayOpeningHour;
        this.tuesdayClosingHour = tuesdayClosingHour;
        this.wednesdayOpeningHour = wednesdayOpeningHour;
        this.wednesdayClosingHour = wednesdayClosingHour;
        this.thursdayOpeningHour = thursdayOpeningHour;
        this.thursdayClosingHour = thursdayClosingHour;
        this.fridayOpeningHour = fridayOpeningHour;
        this.fridayClosingHour = fridayClosingHour;
        this.saturdayOpeningHour = saturdayOpeningHour;
        this.saturdayClosingHour = saturdayClosingHour;
        this.sundayOpeningHour = sundayOpeningHour;
        this.sundayClosingHour = sundayClosingHour;
        this.place = place;
    }


    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getMondayOpeningHour() {
        return mondayOpeningHour;
    }

    public void setMondayOpeningHour(String mondayOpeningHour) {
        this.mondayOpeningHour = mondayOpeningHour;
    }

    public String getMondayClosingHour() {
        return mondayClosingHour;
    }

    public void setMondayClosingHour(String mondayClosingHour) {
        this.mondayClosingHour = mondayClosingHour;
    }

    public String getTuesdayOpeningHour() {
        return tuesdayOpeningHour;
    }

    public void setTuesdayOpeningHour(String tuesdayOpeningHour) {
        this.tuesdayOpeningHour = tuesdayOpeningHour;
    }

    public String getTuesdayClosingHour() {
        return tuesdayClosingHour;
    }

    public void setTuesdayClosingHour(String tuesdayClosingHour) {
        this.tuesdayClosingHour = tuesdayClosingHour;
    }

    public String getWednesdayOpeningHour() {
        return wednesdayOpeningHour;
    }

    public void setWednesdayOpeningHour(String wednesdayOpeningHour) {
        this.wednesdayOpeningHour = wednesdayOpeningHour;
    }

    public String getWednesdayClosingHour() {
        return wednesdayClosingHour;
    }

    public void setWednesdayClosingHour(String wednesdayClosingHour) {
        this.wednesdayClosingHour = wednesdayClosingHour;
    }

    public String getThursdayOpeningHour() {
        return thursdayOpeningHour;
    }

    public void setThursdayOpeningHour(String thursdayOpeningHour) {
        this.thursdayOpeningHour = thursdayOpeningHour;
    }

    public String getThursdayClosingHour() {
        return thursdayClosingHour;
    }

    public void setThursdayClosingHour(String thursdayClosingHour) {
        this.thursdayClosingHour = thursdayClosingHour;
    }

    public String getFridayOpeningHour() {
        return fridayOpeningHour;
    }

    public void setFridayOpeningHour(String fridayOpeningHour) {
        this.fridayOpeningHour = fridayOpeningHour;
    }

    public String getFridayClosingHour() {
        return fridayClosingHour;
    }

    public void setFridayClosingHour(String fridayClosingHour) {
        this.fridayClosingHour = fridayClosingHour;
    }

    public String getSaturdayOpeningHour() {
        return saturdayOpeningHour;
    }

    public void setSaturdayOpeningHour(String saturdayOpeningHour) {
        this.saturdayOpeningHour = saturdayOpeningHour;
    }

    public String getSaturdayClosingHour() {
        return saturdayClosingHour;
    }

    public void setSaturdayClosingHour(String saturdayClosingHour) {
        this.saturdayClosingHour = saturdayClosingHour;
    }

    public String getSundayOpeningHour() {
        return sundayOpeningHour;
    }

    public void setSundayOpeningHour(String sundayOpeningHour) {
        this.sundayOpeningHour = sundayOpeningHour;
    }

    public String getSundayClosingHour() {
        return sundayClosingHour;
    }

    public void setSundayClosingHour(String sundayClosingHour) {
        this.sundayClosingHour = sundayClosingHour;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OpeningHoursEntity that = (OpeningHoursEntity) o;
        return placeId.equals(that.placeId) &&
                Objects.equals(mondayOpeningHour, that.mondayOpeningHour) &&
                Objects.equals(mondayClosingHour, that.mondayClosingHour) &&
                Objects.equals(tuesdayOpeningHour, that.tuesdayOpeningHour) &&
                Objects.equals(tuesdayClosingHour, that.tuesdayClosingHour) &&
                Objects.equals(wednesdayOpeningHour, that.wednesdayOpeningHour) &&
                Objects.equals(wednesdayClosingHour, that.wednesdayClosingHour) &&
                Objects.equals(thursdayOpeningHour, that.thursdayOpeningHour) &&
                Objects.equals(thursdayClosingHour, that.thursdayClosingHour) &&
                Objects.equals(fridayOpeningHour, that.fridayOpeningHour) &&
                Objects.equals(fridayClosingHour, that.fridayClosingHour) &&
                Objects.equals(saturdayOpeningHour, that.saturdayOpeningHour) &&
                Objects.equals(saturdayClosingHour, that.saturdayClosingHour) &&
                Objects.equals(sundayOpeningHour, that.sundayOpeningHour) &&
                Objects.equals(sundayClosingHour, that.sundayClosingHour) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), placeId, mondayOpeningHour, mondayClosingHour, tuesdayOpeningHour, tuesdayClosingHour, wednesdayOpeningHour, wednesdayClosingHour, thursdayOpeningHour, thursdayClosingHour, fridayOpeningHour, fridayClosingHour, saturdayOpeningHour, saturdayClosingHour, sundayOpeningHour, sundayClosingHour);
    }
}
