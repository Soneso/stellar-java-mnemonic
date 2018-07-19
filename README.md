# stellar-java-mnemonic

The Soneso open source Java library to create mnemonics for the Stellar Network. 

See [Key Derivation Methods for Stellar Accounts](https://github.com/stellar/stellar-protocol/blob/master/ecosystem/sep-0005.md)

## Installation

### Jar file

Add the current jar file from the distribution folder to your Java/Android Project.

### Source code

Copy and attach source code to your project.

## Dependencies

The library needs the [Stellar Java SDK] (https://github.com/stellar/java-stellar-sdk)

## Quick Start


#### Deterministic generation

Generate mnemonic
```java
char[] mnemonic = Wallet.generate24WordMnemonic();
String[] words = String.valueOf(mnemonic).split(" ");
// bench hurt jump file august wise shallow faculty impulse spring exact slush thunder author capable act festival slice deposit sauce coconut afford frown better
```

Generate key pairs
```java
char[] mnemonic = "bench hurt jump file august wise shallow faculty impulse spring exact slush thunder author capable act festival slice deposit sauce coconut afford frown better".toCharArray();
KeyPair keyPair0 = Wallet.createKeyPair(mnemonic, null, 0);
KeyPair keyPair1 = Wallet.createKeyPair(mnemonic, null, 1);
```

Generate key pairs with passphrase
```java
char[] mnemonic = "cable spray genius state float twenty onion head street palace net private method loan turn phrase state blanket interest dry amazing dress blast tube".toCharArray();
char[] passphrase = "p4ssphr4se".toCharArray();
KeyPair keyPair0 = Wallet.createKeyPair(mnemonic, passphrase, 0);
``` 

BIP and master key generation
```java
char[] mnemonic = "bench hurt jump file august wise shallow faculty impulse spring exact slush thunder author capable act festival slice deposit sauce coconut afford frown better".toCharArray();
byte[] bip39Seed = Mnemonic.createSeed(mnemonic, null);

Ed25519Derivation masterPrivateKey = Ed25519Derivation.fromSecretSeed(bip39Seed);
Ed25519Derivation purpose = masterPrivateKey.derived(44);
Ed25519Derivation coinType = purpose.derived(148);

Ed25519Derivation account0 = coinType.derived(0);
KeyPair keyPair0 = KeyPair.fromSecretSeed(account0.getPrivateKey());

Ed25519Derivation account4 = coinType.derived(4);
KeyPair keyPair4 = KeyPair.fromSecretSeed(account4.getPrivateKey());
```      

Please also see [test classes](https://github.com/Soneso/stellar-java-mnemonic/tree/master/src/test/java) for more examples.


## License

java-stellar-mnemonic is licensed under an Apache-2.0 license. See the [LICENSE](https://github.com/Soneso/stellar-java-mnemonic/blob/master/LICENSE) file for details.

## Donations
Send lumens to: GBD7Z2JSVGD2CWNMULKEROA75E6QXCAIERITPICSV77VMRDXNWIXNGLL
