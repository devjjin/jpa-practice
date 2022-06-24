package jpa.practice.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

    //order 1:1
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToOne(mappedBy = "delivery")
    private Order order;

}
