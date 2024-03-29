package com.callsign.customer.support.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Shadab Khan
 * @since 09/01/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deliveryId;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private Timestamp expectedDeliveryTime;
    @Column(name = "current_distance_from_destination_in_meters")
    private int distanceFromDestination;
    //  milliseconds
    private long timeToReachDestination;
    private int riderRating;
    @Column(name = "restaurant_mean_time_to_prepare_food")
    //  milliseconds
    private long timeToPrepareFood;
    private boolean monitored;
}
