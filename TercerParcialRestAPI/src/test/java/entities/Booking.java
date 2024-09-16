package entities;

import lombok.Getter;
import lombok.Setter;

public class Booking {
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private int totalPrice;
    @Getter @Setter
    private Boolean depositPaid;
    @Getter @Setter
    private String checkIn;
    @Getter @Setter
    private String checkOut;
    @Getter @Setter
    private String additionalneeds;




}
