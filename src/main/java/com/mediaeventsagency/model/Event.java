package com.mediaeventsagency.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;

import javax.print.attribute.standard.Media;
import java.sql.Date;
import java.sql.Types;
import java.util.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column
    private String title;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    private String media;

    @Column
    private int nrOfTickets;

    @Column
    private Date date;
}

