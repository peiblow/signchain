package com.veoow.signchain.blockchaincore.dto;

import java.util.List;

public record TransactionRequest(
      String documentHash,
      String documentName,
      List<SignatureRequest> signatures
) {}
