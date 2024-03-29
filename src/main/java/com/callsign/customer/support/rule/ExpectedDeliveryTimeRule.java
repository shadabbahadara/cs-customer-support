package com.callsign.customer.support.rule;

import com.callsign.customer.support.model.Delivery;
import com.callsign.customer.support.model.Ticket;
import com.callsign.customer.support.model.TicketPriority;
import com.callsign.customer.support.model.TicketStatus;
import com.callsign.customer.support.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Shadab Khan
 * @since 11/01/2022
 */
@Component
@RequiredArgsConstructor
public class ExpectedDeliveryTimeRule implements Rule {
    private final TicketService ticketService;

    @Override
    public boolean condition(Delivery delivery) {
        long expectedDeliveryTime = delivery.getExpectedDeliveryTime().getTime() - System.currentTimeMillis();
        long estimatedDeliveryTime = delivery.getTimeToReachDestination() + delivery.getTimeToPrepareFood();
        return estimatedDeliveryTime > expectedDeliveryTime;
    }

    @Override
    public void action(Delivery delivery) {
        Ticket ticket = Ticket.builder()
                .priority(TicketPriority.MEDIUM)
                .status(TicketStatus.OPEN)
                .description("estimated delivery time is greater than expected delivery time")
                .delivery(delivery)
                .build();
        ticketService.createTicket(ticket);
        delivery.setMonitored(true);
    }
}
