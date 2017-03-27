# StarScape
A recent personal project and possibly the final project of CSC112. A small game/utility that draws upon astronomical data from NASA to create a map of the surrounding stars and planets, combined with a ship and random events that provide a rogue-like experience.
Uses SimpleJSON for parsing the two JSON files.

Requires the two JSON files to run.

The data for exo.json is taken from the NASA Exoplanet Archive at http://exoplanetarchive.ipac.caltech.edu/

The data for hydata.json is taken from the HYG Database, which one can check out over at the astronexus/HYG-Database repository.

--
Commands:

go x,z

   Travese the x/z plane x units right/left and z units up/down. The y dimension is discarded, for now.

info key

   Print out the common name of the star indicated by it's key, or a-z/A-Z letter. If a common name dos not exist, than its HIP entry is provided.

exit

   Terminates the program
