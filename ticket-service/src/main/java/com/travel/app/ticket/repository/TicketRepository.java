package com.travel.app.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.app.ticket.pojo.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

        Ticket findByTicketNumber(String ticketNumber);
        void deleteByTicketNumber(String ticketNumber);

}
