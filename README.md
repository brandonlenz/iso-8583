## ISO-8583
A common financial transaction messaging spec used by many brands and acquirers for terminal-host communication.

Brands and acquirers generally implement different flavors of ISO-8583 to build their proprietary messaging specifications (protocols), however the core parsing logic and ideas are common throughout.

|                              Link                                          |      Description           |
|----------------------------------------------------------------------------|----------------------------|
|[ISO-8583 Specification](https://www.iso.org/obp/ui/#iso:std:iso:8583:-1:en)| Official ISO Specification |
|[ISO-8583 Wikipedia](https://en.wikipedia.org/wiki/ISO_8583)                | ISO-8583 Wikepdia article  |

### ISO-8583 Library
 
 This Library is designed to facilitate manipulation of ISO-8583 messages using in Java, initially (at least) for education on the specification.
 
 Core features include:
    
 * Parsing of ISO-8583 hex dumps
 * Creation and building of ISO-8583 messages
 * Validation of created/parsed ISO-8583 messages
 
 At the moment this library’s easiest "point-of-entry" is to run the simple CLI interface in the [Demo](src/main/java/com/brandonlenz/iso8583/Demo.java) class. 
 This simple application can be used to demo the library as well as the messaging specification.

 The next major step will be building a web front-end making use of this library in order to allow intuitive manipulation of host messages.
 
 #### Notes:
 
 This library is by no means suited for production use, however the goal is to continually improve and extend the library's capabilities.
 
 Overall, I hope to foster a balance of abstraction and maintainability while building/expanding this library, without sacrificing a high level of test coverage.
 
