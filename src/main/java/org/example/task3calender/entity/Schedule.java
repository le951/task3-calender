package org.example.task3calender.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@Getter
@Check(constraints = "start_at <= end_at")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private LocalDateTime lastUpdatedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime removedAt;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true, length = 1000)
    private String detail;

//    @Builder
    public Schedule(User user, LocalDateTime start_at, LocalDateTime end_at, String title, String detail){
        this.user = user;
        this.startAt = start_at;
        this.endAt = end_at;
        this.title = title;
        this.detail = detail;
    }


    public void remove(){
        if(removedAt == null)
            removedAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = createdAt;

        if (startAt.isAfter(endAt)) endAt = startAt;

    }

    @PreUpdate
    protected void onUpdate(){
        this.lastUpdatedAt = LocalDateTime.now();

        if (startAt.isAfter(endAt)) endAt = startAt;
    }

}

