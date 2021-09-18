# Blackjack

A program that simulates a blackjack game between a guy called Sam and a dealer.

## Made with

- [Java](https://www.java.com/en/)
    - [Junit](https://junit.org/junit5/)
    - [AssertJ](https://assertj.github.io/doc/)
    - [Mockito](https://site.mockito.org/)
- [Maven](https://maven.apache.org/)

## Running

Run the app with a file path as a command line argument to play with a fixed pre shuffled card deck. Each card must be defined as

    <suit><value>

Each card is seperated by comma(,), and the following file content

    CA, D4, H7, SJ, S5, CK, S9, D10

yields a card deck consisting of

<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/ka.png" alt="Ace of clubs" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/l4.png" alt="Four of diamonds" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/s7.png" alt="Seven of hearts" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/p5.png" alt="Five of spades" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/kk.png" alt="King of clubs" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/p9.png" alt="Nine of spades" width="60">
<img src="https://www.improvemagic.com/wp-content/uploads/2020/11/l10.png" alt="Ten of diamonds" width="60">

When not providing a predefined card deck, a new card deck with 52 cards will be shuffled and used.

## Authors

- **Lars-Erik Holte**