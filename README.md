# Yatzy
Yatzy is a java application based on the the famous dice game "Yahtzee".

## Rules & Gameplay <a href="http://www.yahtzee.org.uk/rules.html" target="_blank">{*see source*}</a>
- **Object of the game:**<br/>
The object of Yahtzee is to obtain the highest score from throwing **5** dice.
The game consists of **13** rounds. In each round, you roll the dice and then score the roll in one of **13** categories. 
You must score once in each category. 
The score is determined by a different rule for each category.
The game ends once all **13** categories have been scored.

- **Game Start:**<br/>
To start with, roll all the dice. After rolling you can either score the current roll (see below), or re-roll any or all of the dice.
You may only roll the dice a total of **3** times. After rolling **3** times you must choose a category to score.
You may score the dice at any point in the round, i.e. it doesn't have to be after the 3rd roll.

- **Scoring:**<br/>
To score your combination of **5** dice, you click one of the **13** boxes, or write it on the scorecard (scoresheet). There are two sections to the score table - the Upper Section and the Lower Section.
Once a box has been scored, it cannot be scored again for the rest of the game, so choose wisely.

- **Upper Section Scoring:**<br/>
If you score in the upper section of the table, your score is the total of the specified die face.
So if you roll:
**5, 2, 5, 6, 5** and score in the Fives category, your total for the category would be **15**, because there are three fives, which are added together.
If the One, Three or Four Categories were selected for scoring with this roll, you would score a zero.
If placed in the Two or Six category, you would score **2** and **6** respectively.
Bonus: If the total of Upper scores is **63** or more, add a bonus of **50**. Note that **63** is the total of three each of 1s, 2s, 3s, 4s, 5s and 6s.

- **Lower Section Scoring:**<br/>
In the lower scores, you score either a set amount, or zero if you don't satisfy the category requirements.
 * **3 and 4 of a kind:**<br/> For **3** of a kind you must have at least **3** of the same die faces. You score the total of all the dice. For **4** of a kind you would need **4** die faces the same.<br/>
 * **Small and Large Straight:**<br/> 
A Straight is a sequence of consecutive die faces, where a small straight is **4** consecutive faces, and a large straight **5** consecutive faces.
Small straights score **30** and a large **40** points.<br/>
So if you rolled:
**2, 3, 2, 5, 4**
you could score **30** in small straight or **0** in large straight.<br/>
 * **Full House:**<br/> 
A Full House is where you have **3** of a kind and **2** of a kind. Full houses score **25** points.
i.e.:
**3, 3, 2, 3, 2**
would score **25** in the Full House category.<br/>
 * **Yahtzee:**<br/> 
A Yahtzee is **5** of a kind and scores **50** points, although you may elect NOT to score it as a yahtzee, instead choosing to take it as a top row score and safeguard you bonus.<br/> 
 * **Chance:**<br/> 
You can roll anything and be able to put it in the Chance category. You score the total of the die faces.<br/>

- **Scratch or Dump scores:**</br> 
You can score any roll in any category at any time, even if the resulting score is zero. Eg, you
can take **2, 3, 3, 4, 6** in the **5**'s category. It will place a **cross** at the chosen category meaning that the score of that is **0** (**null**). This could be used near the end of a game to lose a poor roll against a difficult-to-get category that you've failed to fill (eg, long straight or yahtzee).

## Supported Platforms
This project is currently not supporting Mobile Devices.

- Desktop PCs
 * Windows 
 * Linux 
 * Mac OS X
