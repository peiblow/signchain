package com.veoow.signchain.blockchaincore.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Transaction {
  private String id;
  private String documentHash;
  private String documentName;
  private LocalDateTime createdAt;
  private List<SignatureRecord> signatures;

  public Transaction(String documentHash, String documentName) {
    this.id = UUID.randomUUID().toString();
    this.documentHash = documentHash;
    this.documentName = documentName;
    this.createdAt = LocalDateTime.now();
  }
}
