# One-time pad encryption

Basic **Java Swing** application to demonstrate **one-time pad** encryption.

![Version](https://img.shields.io/badge/Version-1.0.0-2AAB92.svg)
![Static Badge](https://img.shields.io/badge/Last%20update-12%20Nov%202008-blue)
![Version](https://img.shields.io/badge/Java-6-red.svg)

---

# Table of Contents

* [About the project](#about-the-project)
* [Usage](#usage)
* [Technical details](#technical-details)
* [Architecture](#architecture)
* [Performances test](#performances-test)
* [License](#license)

# About the project

<img alt="Java logo" src="doc/logo-java.svg" height="92"/>

This is a simple GUI application (in French) to demonstrate the use of the **One-time pad** encryption technique for
encrypting / decrypting data (text and files).

Application has been built on **November 2008** using **Netbeans IDE 6.5**.

It uses the **Swing Application Framework** and was compiled with **Java 6**.

# Usage

Run the provided `.jar` file :

```bash
java -jar "dist/ProjetCrypto.jar"
```

Then just use the UI :

![Main interface picture](doc/main_interface.png?raw=true "Main interface")

![Details interface picture](doc/details_interface.png?raw=true "Details interface")

From the `Option` menu, you can configure :

- byte packet size (1024, 2048, 4096) : byte packet size for file processing
- random or fixed seed (alternated 0 and 1)
- write seed or not : if yes, seed will be written in the mask before generating next bits
- LFSR (see technical details below)

When dealing with files, I use the following naming convention :

- original file : _filename_.ext
- encrypted file : crypte\__filename_.ext
- key file : cle\__filename_.ext
- decrypted file : decrypte\__filename_.ext

# Technical details

Security is done by using a **Verman** type scheme with a **Linear Feadback Shift Register** (LFSR) based on a **pseudo-random generator**.

Following **LFSR** have been implemented :

1. ```
   Xn = Xn-5 ⊕ Xn-6 ⊕ Xn-15 ⊕ Xn-16
   ```

   Short secret key (the seed) is constituted of `X0, X1, ..., X15` values (16 bits)

2. ```
   Vn = Vn-5 ⊕ Vn-13 ⊕ Vn-17 ⊕ Vn-25
   Xn = Xn-7 ⊕ Xn-15 ⊕ Xn-19 ⊕ Xn-31
   Yn = Yn-5 ⊕ Yn-9 ⊕ Yn-29 ⊕ Yn-33
   Zn = Zn-3 ⊕ Zn-11 ⊕ Zn-35 ⊕ Zn-39
   ```

   In this case, the secret key consists of `25 + 31 + 33 + 39 = 128` bits, and the secret bit generated
   after each iteration is given by `b = Vn ⊕ Xn ⊕ Yn ⊕ Zn`.

3. ```
   Xn = Xn-5 ⊕ Xn-13 ⊕ Xn-17 ⊕ Xn-25 ⊕ Xn-32 ⊕ Xn-40 ⊕ Xn-44 ⊕ Xn-56 ⊕ Xn-61 ⊕ Xn-65 ⊕ Xn-85 ⊕ Xn-89 ⊕ Xn-92 ⊕ Xn-100 ⊕ Xn-124 ⊕ Xn-128
   ```

   Short secret key (the seed) is constituted of `X0, X1, ..., X127` values (128 bits)

Thus, a sequence of bits of the same length as the message to be encrypted will be generated.
And as the data is in binary form, the method is reduced to a particularly simple calculation and very effective in practice.
The message to be encrypted is presented as a sequence of bits, and the key is another sequence of bits, of the same length.
The bits of the plain text are treated one by one, by combining each one with the bit that has the same rank in the key, and then applying a **XOR** operation.

Decryption is done by combining the encrypted data with the bits of the key.
The XOR operation being simple in computer science, these treatments can be performed at very high speed.

Here is a picture representing the secret bit generation for the **128 bits LFSR** (n°2) :

![Secret bit generation picture](doc/secret_bit_generation.png?raw=true "Secret bit generation")

# Architecture

Application architecture :

![Application architecture schema](doc/architecture.png?raw=true "Architecture")

All interesting stuff is located in file `ProjetCryptoView.java` (yes I don't used any **MVC** pattern at that time!)

# Performances test

Here are some tests about file encryption (text, image, sound, video) under different computers (1,2 and 4 cores) to evaluate the performances :

1. Computer 1 (laptop) :
    - AMD Athlon M 2600+\@2,0ghz
    - 1 x 256Mo PC2700@133mhz
    - 30Go IDE 5400tr/min 2Mo
2. Computer 2 :
    - Intel Core 2 duo E6700@3,3ghz
    - Corsair 2 x 1Go PC6400PRO@413mhz 4-4-4-12
    - 2 x Maxtor SATA II 160Go 7200tr/min 8Mo raid0
3. Computer 3 :
    - Intel Quad Core Q6600@2,9ghz
    - Corsair 2 x 1Go PC6400C4@406mhz 5-5-5-18
    - 2 x Maxtor SATA II 500Go 7200tr/min 32Mo raid1

Files :

- Name : `test1.txt`, Type : `text txt`, Size : `790 bytes`
- Name : `test2.jpg`, Type : `image jpg`, Size : `46 467 bytes`
- Name : `test3.mp3`, Type : `sound mp3`, Size : `3 637 961 bytes`
- Name : `test4.avi`, Type : `video avi`, Size : `310 971 920 bytes`

Computer 1 :

| File                  | LFSR n°1 - 1024 bytes | LFSR n°1 - 2048 bytes | LFSR n°1 - 4096 bytes | LFSR n°2 - 1024 bytes | LFSR n°2 - 2048 bytes | LFSR n°2 - 4096 bytes |
|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|
| test1.txt (790 o)     | 00:00:00:080          | 00:00:00:040          | 00:00:00:020          | 00:00:00:010          | 00:00:00:020          | 00:00:00:020          |
| test2.jpg (46,47 Ko)  | 00:00:00:231          | 00:00:00:180          | 00:00:00:341          | 00:00:00:421          | 00:00:00:430          | 00:00:00:431          |
| test3.mp3 (3,64 Mo)   | 00:00:06:099          | 00:00:07:370          | 00:00:08:562          | 00:00:10:435          | 00:00:12:938          | 00:00:16:053          |
| test4.avi (310,97 Mo) | 00:10:50:726          | 00:12:28:306          | 00:14:03:764          | 00:16:51:624          | 00:24:21:078          | 00:29:52:878          |

Computer 2 :

| File                  | LFSR n°1 - 1024 bytes | LFSR n°1 - 2048 bytes | LFSR n°1 - 4096 bytes | LFSR n°2 - 1024 bytes | LFSR n°2 - 2048 bytes | LFSR n°2 - 4096 bytes |
|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|
| test1.txt (790 o)     | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          |
| test2.jpg (46,47 Ko)  | 00:00:00:032          | 00:00:00:050          | 00:00:00:062          | 00:00:00:063          | 00:00:00:062          | 00:00:00:078          |
| test3.mp3 (3,64 Mo)   | 00:00:01:375          | 00:00:01:782          | 00:00:02:078          | 00:00:02:344          | 00:00:02:938          | 00:00:03:640          |
| test4.avi (310,97 Mo) | 00:02:15:875          | 00:02:36:265          | 00:02:58:688          | 00:03:33:532          | 00:04:28:391          | 00:05:57:500          |

Computer 3 :

| File                  | LFSR n°1 - 1024 bytes | LFSR n°1 - 2048 bytes | LFSR n°1 - 4096 bytes | LFSR n°2 - 1024 bytes | LFSR n°2 - 2048 bytes | LFSR n°2 - 4096 bytes |
|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|-----------------------|
| test1.txt (790 o)     | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          | 00:00:00:000          |
| test2.jpg (46,47 Ko)  | 00:00:00:016          | 00:00:00:062          | 00:00:00:078          | 00:00:00:047          | 00:00:00:063          | 00:00:00:109          |
| test3.mp3 (3,64 Mo)   | 00:00:01:563          | 00:00:02:062          | 00:00:02:735          | 00:00:02:859          | 00:00:03:906          | 00:00:04:828          |
| test4.avi (310,97 Mo) | 00:02:38:112          | 00:02:59:754          | 00:03:38:968          | 00:04:08:622          | 00:05:10:354          | 00:06:58:720          |

# License

[General Public License (GPL) v3](https://www.gnu.org/licenses/gpl-3.0.en.html)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU
General Public License as published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not,
see <http://www.gnu.org/licenses/>.