# The Augment

A Slay the Spire mod that adds a new character -- The Augment, a veteran wizard who uses Focus both for a limited selection of orbs and for Magic attacks.  They also interact with card rewards and their own deck in unique ways.

# TODO

## Next up

* Character art

## Implement

* Enemy behavior changes on block/remove buffs

## Technical

* Entropy could work with temp buffs by peeking at action queuestack
* See if Siphoning Claw can be made to work with Claw Pack from Packmaster
* Validate Source Code and Hacker's Almanac against non-battle card rewards
* Migrate SpirePatches to use Locators
* See if the X-cost attacks are being overstacked with MagicWeaponPower (and, theoretically, Arthame + Chemical X)
* DrawCardAction's endTurnDraw boolean can be used to apply the fix I want for KiP and potentially Divine
* Check how Entropy interacts with Mirror Image and temporary Artifact

## Balance
* Nerf Stunning Strike (and possibly buff Magic Weapon)
* Starstorm should end your turn (though not for balance reasons)
* Knowledge is Power shouldn't trigger on draw-for-turn
* Consider making Steelwind Strike magic
* Potentially rename Future Strike to Glancing Blow and ditch the latter since it's just a Quick Slash upgrade
* Consider whether Iron Fist should/test if it already does scale with Dex
* Nerf Disengage slightly
* Consider whether Purge Thought should cost 1 like it originally did
* Consider whether Sanctuary should be Exhaust or Spontaneous
* Consider whether Guiding Light retains too many cards too easily
* Consider making Frail cards apply Frail first (this would be a buff)
* Nerf Pillage's damage into the ground
* Definitely swap the rarities on some common card and Stunning Strike
* See if Delayed Blast Fireball needs a buff

## Other considerations
* Consider changing timing of passive effect of Aether orbs
* Replace generic names, including D&D spells and Magic keyword
* Potentially relocate added description text for Enchant and Eidolon actions, and see if they get annihilated by cards whose descriptions update

## Bugs

* Necrotic Beam's damage is not doubled on display
* Stunning Strike triggers twice if the first hit is fatal