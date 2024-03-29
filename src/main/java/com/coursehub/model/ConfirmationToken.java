package com.coursehub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_generator"
    )
    @SequenceGenerator(
            sequenceName = "confirmation_token_seq",
            name = "confirmation_token_generator",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private LocalDateTime ConfirmedAt;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;
}
