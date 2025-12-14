package com.springbaseproject.sharedstarter.entities;

import com.springbaseproject.sharedstarter.constants.TokenTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {
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
