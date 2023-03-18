# Arkanoid

## Introduction
This Arkanoid game contains four levels. The player will win the game if he breaks all the blocks in each level and he will lose if all the balls fall.

## Table of contents
* [General Information](#general-information)
* [Installation](#installation)
* [Contact](#Contact)

## General Information
This game has blocks, balls, and a main block on the bottom. In order to move the main block press the right or left arrows on the keyboard. When the ball hits the block, the block disappears from the screen. If there are no more blocks on the screen, the player will be moved to the next level. When there are no more levels, meaning the player wins all levels, the player wins the game. Another situation is that if a ball hits the bottom of the screen (not the main block), the ball will disappear from the game. When there are no more balls on the screen, the game will end and the player loses the game. 

In addition, there is a score in the game. For each block hit, 5 points are added to his score, and for each new level, 100 points are added.
In both modes, when the player wins or loses a game, a screen is displayed with the player's final score in that game.

<img width="600" alt="image" src="https://user-images.githubusercontent.com/75027826/225318444-5e3a6050-cf3e-4f52-8d69-7d06890a3bb4.png">
<img width="591" alt="image" src="https://user-images.githubusercontent.com/75027826/225319451-9f59bedb-301f-4f23-8db4-9648cb0d240c.png">

The levels in the game are:
#### First level:
<img width="599" alt="image" src="https://user-images.githubusercontent.com/75027826/225316202-5aa57b0f-40d6-43d1-83a9-1410dd940208.png">

#### Second level:
<img width="596" alt="image" src="https://user-images.githubusercontent.com/75027826/225320505-2ca9054b-7a59-4474-a48c-9d2d6dcfcd2a.png">

#### Third level:
<img width="598" alt="image" src="https://user-images.githubusercontent.com/75027826/225318770-0fdeee7c-b0f8-4673-88d3-d08cfc056e9b.png">

#### Fourth level:
<img width="597" alt="image" src="https://user-images.githubusercontent.com/75027826/225319247-d1423a9b-36d6-4fe5-afe8-b27b562a1e1a.png">

In the command line, before starting the game, the player has to write the order of the levels and which levels will appear in the game.

## Installation
Before installing this project, you need to install on your computer:
* Git

After it, run the following commands in the terminal:

```
git clone https://github.com/adi-ben-yehuda/Game.git
ant compile
ant -Dargs="{text}" run
```
The text will contain the levels that will be shown in the game. There are four levels. If the user enters different numbers or letters, the program will ignore it. 

## Contact
Created by @adi-ben-yehuda - feel free to contact me!
