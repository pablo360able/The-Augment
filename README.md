# The Augment

A Slay the Spire mod that adds a new character -- The Augment, a veteran wizard who uses Focus both for a limited selection of orbs and for Magic attacks.  They also interact with card rewards and their own deck in unique ways.

# TODO

## Next up

* Uncommon Skills: Realize, Inspiration, Recenter, Sanctuary, Multiattack, Feign Disaster, Light from Above, Take Cover, Enchant, Memorize
* Rare Skills: Suppress, Jailbreak
* Powers: Guiding Light, Artillery, Defensive Stance, Upcast, Investiture, Efficiency
* Relics: All except Tidldibab
* Potions: All
* Magic Missiles have to show up in tooltips

## Implement

* Most art (energy = gold star?)
* Most cards
* Most relics
* Concentration keyword

## Technical

* Entropy could work with temp buffs by peeking at action queuestack
* Test whether UnscheduleKipAction can be replaced by peeking at action queuestack
* See if Siphoning Claw can be made to work with Claw Pack from Packmaster

## Balance
* Nerf Stunning Strike (and possibly buff Magic Weapon)
* Starstorm should end your turn (though not for balance reasons)
* Knowledge is Power shouldn't trigger on draw-for-turn
* Consider making Steelwind Strike magic
* Potentially rename Future Strike to Glancing Blow and ditch the latter since it's just a Quick Slash upgrade
* Consider whether Iron Fist should/test if it already does scale with Dex
* Consider making Eidolon Form's upgrade give CD1C
* Nerf Disengage slightly
* Consider whether Purge Thought should cost 1 like it originally did

## Other considerations
* Consider changing timing of passive effect of Aether orbs
* Replace generic names, including D&D spells and Magic keyword

## Bugs