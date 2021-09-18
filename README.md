# Blackjack

A program that simulates a blackjack game between a guy called Sam and a dealer.

## Made with

- [Java](https://www.java.com/en/)
    - [Junit](https://junit.org/junit5/)
    - [AssertJ](https://assertj.github.io/doc/)
    - [Mockito](https://site.mockito.org/)
- [Maven](https://maven.apache.org/)

## Running

*Make the jar.file by running the following command standing in the root directory:*

    mvn clean package

### - Run with a shuffled card deck with 52 cards

Run the created jar.file by running the following command:

    java -jar blackjack-1.0.0-SNAPSHOT.jar

### - Run the app with a file path as a command line argument to play with a fixed pre shuffled card deck.

If the file containing the deck you want to use is located on your desktop, the command will look something similar to
this:

    java -jar blackjack-1.0.0-SNAPSHOT.jar "/home/develop/Desktop/testfile.txt"

Each card in the "testfile.txt" must be formatted in the following way:

    <suit><value>

Each card is seperated by comma(,), and the following file content

    SA, D4, CK, H10

yields a card deck consisting of

[![Ace of spades](https://www.improvemagic.com/wp-content/uploads/2020/11/pa.png)](https://www.improvemagic.com/wp-content/uploads/2020/11/pa.png)
[![Four of diamonds](https://www.improvemagic.com/wp-content/uploads/2020/11/l4.png)](https://www.improvemagic.com/wp-content/uploads/2020/11/l4.png)
[![King of clubs](https://www.improvemagic.com/wp-content/uploads/2020/11/kk.png)](https://www.improvemagic.com/wp-content/uploads/2020/11/kk.png)
[![Ten of hearts](https://www.improvemagic.com/wp-content/uploads/2020/11/s10.png)](https://www.improvemagic.com/wp-content/uploads/2020/11/s10.png)

## Authors

- **Lars-Erik Holte**
