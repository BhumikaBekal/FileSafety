# A Java web application to encrypt and decrypt files

* AES algorithm is used for encryption and decryption. Instead of using a traditional method to generate a key , we have used hierarchical tree key generation algorithm to generate a key.
* Along with the datafile (the file to be encrypted) the user provides a key file (the file to generate the key).
* The data file is encrypted using the key generated by the key file using hierarchical tree key generation algorithm.
* If the user wants to decrypt the file,he has to provide the same key file which was provided during encryption.
* Once the decryption is done he can download the file.
![encryptionpage](https://user-images.githubusercontent.com/67831294/125596659-cc2ae2ce-800f-449b-978a-97b45c022538.png)

![successfulencryption](https://user-images.githubusercontent.com/67831294/125596772-1cfc8d21-a58c-4ce0-aa9a-fa60fc82b137.png)

![decryptionpage](https://user-images.githubusercontent.com/67831294/125596737-7c46ce49-a6b3-441c-8452-240ccce74e01.png)

![downloadpage](https://user-images.githubusercontent.com/67831294/125596787-ea8d41e3-88fc-45b2-98bf-3d90e0fefc3b.png)
