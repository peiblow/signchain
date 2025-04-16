package com.veoow.signchain.blockchaincore.service;

import com.veoow.signchain.blockchaincore.model.Block;
import com.veoow.signchain.blockchaincore.model.Transaction;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainService {
  @Getter
  private List<Block> chain;
  private int difficulty = 4;

  public BlockchainService() {
    this.chain = new ArrayList<>();
    Block genesis = new Block(0, new ArrayList<>(), "0");
    genesis.mineBlock(difficulty);
    chain.add(genesis);
  }

  public Block getLatestBlock() {
    return chain.get(chain.size() - 1);
  }

  public void addBlock(List<Transaction> transactions) {
    Block newBlock = new Block(chain.size(), transactions, getLatestBlock().getHash());
    newBlock.mineBlock(difficulty);
    chain.add(newBlock);
  }

  public boolean isChainValid() {
    for (int i = 1; i < chain.size(); i++) {
      Block current = chain.get(i);
      Block previous = chain.get(i - 1);

      if (!current.getHash().equals(current.calculateHash())) return false;
      if (!current.getPreviousHash().equals(previous.getHash())) return false;
    }
    return true;
  }
}
