package com.veoow.signchain.blockchaincore.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignatureRecord {
  private String userId;
  private String publicKey;
  private String signature;
  private LocalDateTime signedAt;

  public SignatureRecord(String userId, String publicKey, String signature) {
    this.userId = userId;
    this.publicKey = publicKey;
    this.signature = signature;
    this.signedAt = LocalDateTime.now();
  }
}
