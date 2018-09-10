## General

This is a simple GUI application to demonstrate the use of the **One-time pad** encryption technique for
encrypting / decrypting data (text and files).

Application has been built on **November 2008** using **Netbeans IDE 6.5**.

It uses the **Swing Application Framework** and was compiled with **Java 6**.

## Usage

Just run the provided `.jar` file :

```bash
java -jar "dist/ProjetLangageFormel.jar"
```

## Technical details

Security is done by using a **Verman** type scheme with a **Linear Feadback Shift Register** (LFSR) based on a **pseudo-random generator**.

Following **LFSR** have been implemented :

1. ```
   Xn = Xn-5 ⊕ Xn-6 ⊕ Xn-15 ⊕ Xn-16
   ```

   Short secret key (the seed) is constituted of `X0, X1, ..., X15` values

2. ```
   Vn = Vn-5 ⊕ Vn-13 ⊕ Vn-17 ⊕ Vn-25
   Xn = Xn-7 ⊕ Xn-15 ⊕ Xn-19 ⊕ Xn-31
   Yn = Yn-5 ⊕ Yn-9 ⊕ Yn-29 ⊕ Yn-33
   Zn = Zn-3 ⊕ Zn-11 ⊕ Zn-35 ⊕ Zn-39
   ```

   In this case, the secret key consists of `25 + 31 + 33 + 39` bits, and the secret bit generated
   after each iteration is given by `b = Vn ⊕ Xn ⊕ Yn ⊕ Zn`.

Thus, a sequence of bits of the same length as the message to be encrypted will be generated.
And as the data is in binary form, the method is reduced to a particularly simple calculation and very effective in practice.
The message to be encrypted is presented as a sequence of bits, and the key is another sequence of bits, of the same length.
The bits of the plain text are treated one by one, by combining each one with the bit that has the same rank in the key, and then applying a **XOR** operation.

Decryption is done by combining the encrypted data with the bits of the key.
The XOR operation being simple in computer science, these treatments can be performed at very high speed.

Here is a picture representing the secret bit generation for the **128 bits LFSR** :

![Secret bit generation picture](doc/secret_bit_generation.png?raw=true "Secret bit generation")

## Architecture

Application architecture :

![Application architecture schema](doc/architecture.png?raw=true "Architecture")

All interesting stuff is in file `ProjetCryptoView.java` (yes I don't used any MVC pattern at that time!)

## Licence

WTFPL license : http://www.wtfpl.net/