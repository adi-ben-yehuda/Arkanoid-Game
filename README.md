# Arkanoid

## Introduction
This Arkanoid game contains four levels. The player will win the game if he breaks all the blocks in each level and he will lose if all the balls fall.

## Table of contents
* [General Information](#general-information)
* [Installation](#installation)
* [Contact](#Contact)

## General Information
This game has blocks and balls. When the ball hits the block, the block disappears from the screen. If there are no more blocks on the screen, the player will be moved to the next level. When there are no more levels, meaning the player wins all levels, the player wins the game. Another situation is that there are no more balls on the screen. In this situation, the game is over and the player loses the game. 
In addition, there is a score in the game. For each block hit, 5 points are added to his score, and for each new level, 100 points are added.
In both modes, when the player wins or loses a game, a screen is displayed with the player's final score in that game.

<img width="600" alt="image" src="https://user-images.githubusercontent.com/75027826/225318444-5e3a6050-cf3e-4f52-8d69-7d06890a3bb4.png">
<img width="317" alt="image" src="https://user-images.githubusercontent.com/75027826/225319006-8443c329-a501-4270-a41e-5a7ccfa6dfd2.png">


The levels in the game are:
#### First level:
<img width="599" alt="image" src="https://user-images.githubusercontent.com/75027826/225316202-5aa57b0f-40d6-43d1-83a9-1410dd940208.png">


#### Second level:
<img width="594" alt="image" src="https://user-images.githubusercontent.com/75027826/225318935-928c58c7-2e9a-43c4-93ac-216a7aee9231.png">


#### Third level:
<img width="598" alt="image" src="https://user-images.githubusercontent.com/75027826/225318770-0fdeee7c-b0f8-4673-88d3-d08cfc056e9b.png">


#### Fourth level:
<img width="597" alt="image" src="https://user-images.githubusercontent.com/75027826/225319247-d1423a9b-36d6-4fe5-afe8-b27b562a1e1a.png">



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
