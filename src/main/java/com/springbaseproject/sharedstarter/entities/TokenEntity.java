package com.springbaseproject.sharedstarter.entities;

import com.springbaseproject.sharedstarter.constants.TokenTypes;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tokens")
public class TokenEntity {
  @Id
  @GeneratedValue
  private Long id;
  @Column(unique = true, length = 1000)
  private String token;
  @Enumerated(EnumType.STRING)
  private TokenTypes tokenType;
  private boolean expired;
  private boolean revoked;
  private Long accountId;
}
