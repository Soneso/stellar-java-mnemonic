import com.soneso.stellarmnemonics.WalletException;
import com.soneso.stellarmnemonics.derivation.Ed25519Derivation;
import com.soneso.stellarmnemonics.Wallet;
import com.soneso.stellarmnemonics.mnemonic.Mnemonic;
import com.soneso.stellarmnemonics.mnemonic.MnemonicException;
import org.junit.Assert;
import org.junit.Test;
import org.stellar.sdk.KeyPair;

import javax.xml.bind.DatatypeConverter;

public class MnemonicKeyPairGeneration {

    @Test
    public void test12WordBIPAndMasterKeyGeneration() throws WalletException {

        String mnemonic = "illness spike retreat truth genius clock brain pass fit cave bargain toe";
        byte[] bip39Seed = Mnemonic.createSeed(mnemonic, null);

        String bip39SeedAsHexString = DatatypeConverter.printHexBinary(bip39Seed).toLowerCase();
        Assert.assertEquals("e4a5a632e70943ae7f07659df1332160937fad82587216a4c64315a0fb39497ee4a01f76ddab4cba68147977f3a147b6ad584c41808e8238a07f6cc4b582f186", bip39SeedAsHexString);

        Ed25519Derivation masterPrivateKey = Ed25519Derivation.fromSecretSeed(bip39Seed);
        Ed25519Derivation purpose = masterPrivateKey.derived(44);
        Ed25519Derivation coinType = purpose.derived(148);

        String coinTypeHexString = DatatypeConverter.printHexBinary(coinType.getPrivateKey()).toLowerCase();
        Assert.assertEquals("e0eec84fe165cd427cb7bc9b6cfdef0555aa1cb6f9043ff1fe986c3c8ddd22e3", coinTypeHexString);

        Ed25519Derivation account0 = coinType.derived(0);
        KeyPair keyPair0 = KeyPair.fromSecretSeed(account0.getPrivateKey());

        Assert.assertEquals("GDRXE2BQUC3AZNPVFSCEZ76NJ3WWL25FYFK6RGZGIEKWE4SOOHSUJUJ6", keyPair0.getAccountId());
        Assert.assertEquals("SBGWSG6BTNCKCOB3DIFBGCVMUPQFYPA2G4O34RMTB343OYPXU5DJDVMN", new String(keyPair0.getSecretSeed()));

        Ed25519Derivation account4 = coinType.derived(4);
        KeyPair keyPair4 = KeyPair.fromSecretSeed(account4.getPrivateKey());

        Assert.assertEquals("GBCUXLFLSL2JE3NWLHAWXQZN6SQC6577YMAU3M3BEMWKYPFWXBSRCWV4", keyPair4.getAccountId());
        Assert.assertEquals("SCPCY3CEHMOP2TADSV2ERNNZBNHBGP4V32VGOORIEV6QJLXD5NMCJUXI", new String(keyPair4.getSecretSeed()));

        Ed25519Derivation account8 = coinType.derived(8);
        KeyPair keyPair8 = KeyPair.fromSecretSeed(account8.getPrivateKey());

        Assert.assertEquals("GDJTCF62UUYSAFAVIXHPRBR4AUZV6NYJR75INVDXLLRZLZQ62S44443R", keyPair8.getAccountId());
        Assert.assertEquals("SCD5OSHUUC75MSJG44BAT3HFZL2HZMMQ5M4GPDL7KA6HJHV3FLMUJAME", new String(keyPair8.getSecretSeed()));
    }

    @Test
    public void test24WordBIPAndMasterKeyGeneration() throws WalletException {
        String mnemonic = "bench hurt jump file august wise shallow faculty impulse spring exact slush thunder author capable act festival slice deposit sauce coconut afford frown better";
        byte[] bip39Seed = Mnemonic.createSeed(mnemonic, null);

        String bip39SeedAsHexString = DatatypeConverter.printHexBinary(bip39Seed).toLowerCase();
        Assert.assertEquals("937ae91f6ab6f12461d9936dfc1375ea5312d097f3f1eb6fed6a82fbe38c85824da8704389831482db0433e5f6c6c9700ff1946aa75ad8cc2654d6e40f567866", bip39SeedAsHexString);


        Ed25519Derivation masterPrivateKey = Ed25519Derivation.fromSecretSeed(bip39Seed);
        Ed25519Derivation purpose = masterPrivateKey.derived(44);
        Ed25519Derivation coinType = purpose.derived(148);

        String coinTypeHexString = DatatypeConverter.printHexBinary(coinType.getPrivateKey()).toLowerCase();
        Assert.assertEquals("df474e0dc2711089b89af6b089aceeb77e73120e9f895bd330a36fa952835ea8", coinTypeHexString);

        Ed25519Derivation account0 = coinType.derived(0);
        KeyPair keyPair0 = KeyPair.fromSecretSeed(account0.getPrivateKey());

        Assert.assertEquals("GC3MMSXBWHL6CPOAVERSJITX7BH76YU252WGLUOM5CJX3E7UCYZBTPJQ", keyPair0.getAccountId());
        Assert.assertEquals("SAEWIVK3VLNEJ3WEJRZXQGDAS5NVG2BYSYDFRSH4GKVTS5RXNVED5AX7", new String(keyPair0.getSecretSeed()));

        Ed25519Derivation account4 = coinType.derived(4);
        KeyPair keyPair4 = KeyPair.fromSecretSeed(account4.getPrivateKey());

        Assert.assertEquals("GAXG3LWEXWCAWUABRO6SMAEUKJXLB5BBX6J2KMHFRIWKAMDJKCFGS3NN", keyPair4.getAccountId());
        Assert.assertEquals("SBIZH53PIRFTPI73JG7QYA3YAINOAT2XMNAUARB3QOWWVZVBAROHGXWM", new String(keyPair4.getSecretSeed()));

        Ed25519Derivation account8 = coinType.derived(8);
        KeyPair keyPair8 = KeyPair.fromSecretSeed(account8.getPrivateKey());

        Assert.assertEquals("GDHX4LU6YBSXGYTR7SX2P4ZYZSN24VXNJBVAFOB2GEBKNN3I54IYSRM4", keyPair8.getAccountId());
        Assert.assertEquals("SCGMC5AHAAVB3D4JXQPCORWW37T44XJZUNPEMLRW6DCOEARY3H5MAQST", new String(keyPair8.getSecretSeed()));
    }

    @Test
    public void test12WordWalletKeyPairGeneration() throws WalletException {
        String mnemonic = "illness spike retreat truth genius clock brain pass fit cave bargain toe";

        KeyPair keyPair0 = Wallet.createKeyPair(mnemonic, null, 0);

        Assert.assertEquals("GDRXE2BQUC3AZNPVFSCEZ76NJ3WWL25FYFK6RGZGIEKWE4SOOHSUJUJ6", keyPair0.getAccountId());
        Assert.assertEquals("SBGWSG6BTNCKCOB3DIFBGCVMUPQFYPA2G4O34RMTB343OYPXU5DJDVMN", new String(keyPair0.getSecretSeed()));

        KeyPair keyPair2 = Wallet.createKeyPair(mnemonic, null, 2);

        Assert.assertEquals("GAY5PRAHJ2HIYBYCLZXTHID6SPVELOOYH2LBPH3LD4RUMXUW3DOYTLXW", keyPair2.getAccountId());
        Assert.assertEquals("SDAILLEZCSA67DUEP3XUPZJ7NYG7KGVRM46XA7K5QWWUIGADUZCZWTJP", new String(keyPair2.getSecretSeed()));

        KeyPair keyPair4 = Wallet.createKeyPair(mnemonic, null, 4);

        Assert.assertEquals("GBCUXLFLSL2JE3NWLHAWXQZN6SQC6577YMAU3M3BEMWKYPFWXBSRCWV4", keyPair4.getAccountId());
        Assert.assertEquals("SCPCY3CEHMOP2TADSV2ERNNZBNHBGP4V32VGOORIEV6QJLXD5NMCJUXI", new String(keyPair4.getSecretSeed()));

        KeyPair keyPair8 = Wallet.createKeyPair(mnemonic, null, 8);

        Assert.assertEquals("GDJTCF62UUYSAFAVIXHPRBR4AUZV6NYJR75INVDXLLRZLZQ62S44443R", keyPair8.getAccountId());
        Assert.assertEquals("SCD5OSHUUC75MSJG44BAT3HFZL2HZMMQ5M4GPDL7KA6HJHV3FLMUJAME", new String(keyPair8.getSecretSeed()));
    }

    @Test
    public void test24WordWalletKeyPairGeneration() throws WalletException {
        String mnemonic = "bench hurt jump file august wise shallow faculty impulse spring exact slush thunder author capable act festival slice deposit sauce coconut afford frown better";

        KeyPair keyPair0 = Wallet.createKeyPair(mnemonic, null, 0);

        Assert.assertEquals("GC3MMSXBWHL6CPOAVERSJITX7BH76YU252WGLUOM5CJX3E7UCYZBTPJQ", keyPair0.getAccountId());
        Assert.assertEquals("SAEWIVK3VLNEJ3WEJRZXQGDAS5NVG2BYSYDFRSH4GKVTS5RXNVED5AX7", new String(keyPair0.getSecretSeed()));

        KeyPair keyPair2 = Wallet.createKeyPair(mnemonic, null, 2);

        Assert.assertEquals("GDYF7GIHS2TRGJ5WW4MZ4ELIUIBINRNYPPAWVQBPLAZXC2JRDI4DGAKU", keyPair2.getAccountId());
        Assert.assertEquals("SD5CCQAFRIPB3BWBHQYQ5SC66IB2AVMFNWWPBYGSUXVRZNCIRJ7IHESQ", new String(keyPair2.getSecretSeed()));

        KeyPair keyPair4 = Wallet.createKeyPair(mnemonic, null, 4);

        Assert.assertEquals("GAXG3LWEXWCAWUABRO6SMAEUKJXLB5BBX6J2KMHFRIWKAMDJKCFGS3NN", keyPair4.getAccountId());
        Assert.assertEquals("SBIZH53PIRFTPI73JG7QYA3YAINOAT2XMNAUARB3QOWWVZVBAROHGXWM", new String(keyPair4.getSecretSeed()));

        KeyPair keyPair8 = Wallet.createKeyPair(mnemonic, null, 8);

        Assert.assertEquals("GDHX4LU6YBSXGYTR7SX2P4ZYZSN24VXNJBVAFOB2GEBKNN3I54IYSRM4", keyPair8.getAccountId());
        Assert.assertEquals("SCGMC5AHAAVB3D4JXQPCORWW37T44XJZUNPEMLRW6DCOEARY3H5MAQST", new String(keyPair8.getSecretSeed()));
    }

    @Test
    public void test24WordWalletKeyPairGenerationWithPassphrase() throws WalletException {
        String mnemonic = "cable spray genius state float twenty onion head street palace net private method loan turn phrase state blanket interest dry amazing dress blast tube";

        KeyPair keyPair0 = Wallet.createKeyPair(mnemonic, "p4ssphr4se", 0);

        Assert.assertEquals("GDAHPZ2NSYIIHZXM56Y36SBVTV5QKFIZGYMMBHOU53ETUSWTP62B63EQ", keyPair0.getAccountId());
        Assert.assertEquals("SAFWTGXVS7ELMNCXELFWCFZOPMHUZ5LXNBGUVRCY3FHLFPXK4QPXYP2X", new String(keyPair0.getSecretSeed()));

        KeyPair keyPair2 = Wallet.createKeyPair(mnemonic, "p4ssphr4se", 2);

        Assert.assertEquals("GCLAQF5H5LGJ2A6ACOMNEHSWYDJ3VKVBUBHDWFGRBEPAVZ56L4D7JJID", keyPair2.getAccountId());
        Assert.assertEquals("SAF2LXRW6FOSVQNC4HHIIDURZL4SCGCG7UEGG23ZQG6Q2DKIGMPZV6BZ", new String(keyPair2.getSecretSeed()));

        KeyPair keyPair4 = Wallet.createKeyPair(mnemonic, "p4ssphr4se", 4);

        Assert.assertEquals("GA6NHA4KPH5LFYD6LZH35SIX3DU5CWU3GX6GCKPJPPTQCCQPP627E3CB", keyPair4.getAccountId());
        Assert.assertEquals("SA5TRXTO7BG2Z6QTQT3O2LC7A7DLZZ2RBTGUNCTG346PLVSSHXPNDVNT", new String(keyPair4.getSecretSeed()));

        KeyPair keyPair8 = Wallet.createKeyPair(mnemonic, "p4ssphr4se", 8);

        Assert.assertEquals("GDS5I7L7LWFUVSYVAOHXJET2565MGGHJ4VHGVJXIKVKNO5D4JWXIZ3XU", keyPair8.getAccountId());
        Assert.assertEquals("SAIZA26BUP55TDCJ4U7I2MSQEAJDPDSZSBKBPWQTD5OQZQSJAGNN2IQB", new String(keyPair8.getSecretSeed()));
    }
}
