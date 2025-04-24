package com.veoow.signchain.apigateway.controller;

import com.veoow.signchain.blockchaincore.dto.TransactionRequest;
import com.veoow.signchain.blockchaincore.model.SignatureRecord;
import com.veoow.signchain.blockchaincore.model.Transaction;
import com.veoow.signchain.blockchaincore.service.BlockchainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
  private final BlockchainService blockchainService;

  @PostMapping
  public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
    Transaction transaction = new Transaction(request.documentHash(), request.documentName());

    List<SignatureRecord> signatures = request.signatures().stream()
          .map(sig -> new SignatureRecord(sig.userId(), sig.publicKey(), sig.signature()))
          .collect(Collectors.toList());

    transaction.setSignatures(signatures);
    blockchainService.addBlock(List.of(transaction));

    return ResponseEntity.ok("Transação registrada na blockchain com sucesso.");
  }

  @GetMapping("/chain")
  public ResponseEntity<?> getBlockchain() {
    return ResponseEntity.ok(blockchainService.getChain());
  }

  @GetMapping("/valid")
  public ResponseEntity<Boolean> isChainValid() {
    return ResponseEntity.ok(blockchainService.isChainValid());
  }
}
