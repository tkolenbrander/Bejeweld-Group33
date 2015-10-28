SwekJeweled
==
Bejeweled game created by Group 33
--
###Created for the Software Engineering Methods course [TI2206] at the TU Delft

The SwekJeweled team consists of the following members:
* Bart van Oort
* Thomas Kolenbrander
* Ruben Vrolijk
* Steven Meijer
* Wytze Elhorst

SwekJeweled is a variation on the normal Bejeweled game. In this game there are two modes: Normal and Time Trial. 
In the Normal mode, the player has to make chains of the same colored gems in order to gain points. The bigger the chain and the bigger the combo, the higher the score is that the player receives. The game is over when there are no more possible moves. 
In the Time Trial mode, the player races against the clock to get as much points as possible before the time is up. The player gets two minutes and an additional thirty seconds for each large chain. A large chain is defined as a chain that is at least four gems long.

Features:
* Normal mode
* Time Trial mode
* Saving and loading of the game (only in normal mode)
* Smooth  animations for swapping and falling gems
* Exploding power gem

Future features:
* Settings menu where the player can e.g. set the difficulty of the game
* More power gems

Known bugs:
* Occasionally, the indication that a gem is clicked is not visible to the player.
* Sometimes, when the player tries to animate a gem when other gems are still falling down, the gems that are falling are displayed one square above the place where they really are. Updating the gem by animating the gem fixes the wrongly displayed gem. This bug is purely visual.