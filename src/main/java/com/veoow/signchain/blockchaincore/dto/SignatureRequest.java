package com.veoow.signchain.blockchaincore.dto;

public record SignatureRequest(
      String userId,
      String publicKey,
      String signature
) {}
