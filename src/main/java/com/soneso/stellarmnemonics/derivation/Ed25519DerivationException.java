package com.soneso.stellarmnemonics.derivation;

import com.soneso.stellarmnemonics.WalletException;

public class Ed25519DerivationException extends WalletException {

    public Ed25519DerivationException(String message) {
        super(message);
    }
}
