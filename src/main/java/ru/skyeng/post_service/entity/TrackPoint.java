package ru.skyeng.post_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "track_point")
public class TrackPoint {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postal_item_id")
    private PostalItem postalItem;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ItemTrackStatus status;

    @ManyToOne
    @JoinColumn(name = "post_office_id")
    private PostOffice postOffice;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private LocalDateTime date;

    public String toHistoryValue() {
        switch (status) {
            case REGISTERED -> {
                return FORMATTER.format(date) + ": registered";
            }
            case ARRIVED_AT_POST_OFFICE -> {
                return FORMATTER.format(date) + ": arrived at post office - " + postOffice.getName();
            }
            case LEFT_POST_OFFICE -> {
                return FORMATTER.format(date) + ": left post office - " + postOffice.getName();
            }
            default -> {
                return FORMATTER.format(date) + ": delivered to recipient - " + postalItem.getRecipientName();
            }
        }
    }
}
