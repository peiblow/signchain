package com.veoow.signchain.blockchaincore.model;

import com.veoow.signchain.blockchaincore.util.HashGenerator;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Block {
  private int index;
  private long timestamp;
  private List<Transaction> transactions;
  private String previousHash;
  private String hash;
  private int nonce;

  public Block(int index, List<Transaction> transactions, String previousHash) {
    this.index = index;
    this.timestamp = Instant.now().toEpochMilli();
    this.transactions = transactions;
    this.previousHash = previousHash;
    this.nonce = 0;
    this.hash = calculateHash();
  }

  public String calculateHash() {
    String data = index + timestamp + transactions.toString() + previousHash + nonce;
    return HashGenerator.sha256(data);
  }

  public void mineBlock(int difficulty) {
    String target = "0".repeat(difficulty);
    while (!hash.startsWith(target)) {
      nonce++;
      hash = calculateHash();
    }
  }

  // Getters e Setters omitidos para clareza
}

