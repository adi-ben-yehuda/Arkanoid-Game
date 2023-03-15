# Arkanoid

## Introduction
This Arkanoid game contains four levels. The player will win the game if he breaks all the blocks in each level and he will lose if all the balls fall.

## Table of contents
* [General Information](#general-information)
* [Installation](#installation)
* [Contact](#Contact)

## General Information
This game has blocks and balls. When the ball hits the block, the block disappears from the screen. If there are no more blocks on the screen, the player will be moved to the next level. If there are no more balls on the screen, the game is over and the player loses the game. In addition, there is a score in the game. For each block hit, 5 points are added to its score, and for each new level, 10 points are added. 

The levels in the game are:
#### First level:


#### Second level:


#### Third level:


#### Fourth level:



## Installation
Before installing this project, you need to install on your computer:
* Git

After it, run the following commands in the terminal:

```
git clone 
ant compile
ant -Dargs="{text}" run
```
The text will contain the levels that will be shown in the game. There are four levels. If the user enters different numbers or letters, the program will ignore it. 

## Contact
Created by @adi-ben-yehuda - feel free to contact me!
