> game machine (name/title, manufacturer, description, type, media, initial launch year, initital price, image)
> game (name/title, publisher, description, original developer [company or people names], original games machine it was developed for, year of first release, cover art/image)
> game port (origianl game, ported to game machine, port developer [company or people names], origianl game machine it was made for, year of port release, cover art/image)

- add new games machine to the system
- add a new game to the system
- add a new game port for a game (origianl game is ported to run on alternate game machine)
- edit and delete game machine, game and game port information (deleteding a game deletes its ports)
- search or filter game machines by (partial) name, type, year, manufacturer, media, description etc.
- search or filter games and ports by (partial) name, publisher, developer, game machine, year of release, description etc.
- listings of search results should be sorted depending on the chosen search parameters (provide sorting of results in at least 3 different ways for both, can be listed together but originals should be highlighted somehow)
- Ability to select and view full details for specific machine or game/port by selecting it from a search results list (hashing used here)

> machines details will provide the name/title, manufacturer, description, type, media, launch year, and RRP/price. It will also display an image/photo of the games machine. It will also show a list of all games (incl. ports) made for the games machine sorted in alphabetical order

> games and port details should show the game/port’s: name/title, publisher, developer, description, the games machine it was developed for, and the release year. It will also display the cover art image of the game/port. It will also show a list of all other versions/ports for the game made for all games machines showing both the machine
names/titles along with the version/port release years and sorted in ascending release year order. Original games should be highlighted.

- interactive drill down (searching games machines for “cartridge” should provide a list of all cartridge-based games machines; one games machine could then be clicked on to see more information specifically on that machine (as outlined above); that detail could include a list of games/ports developed for that machine; clicking on a game/port in that list opens up full details on the game/port (as outlined above), including a list of all other versions/ports that have been made of that game for all games machines; one of those games/ports could be clicked on to see the full details of the relevant games machine along with a list of all games/ports released for it (as outlined above), and so on.
- persistence

--------------------------------------------------------------------

- cannot use existing java collections or data structures
- can reuse code from CA 1
- can only use regualar arrays for hash tables
- cannot use any sorting or searching methods provided by java
- cannot use bubble sort
- cannot depend on JavaFX for sorting
- have to use hashing, in at least 3 ways/places
- JavaFX GUI
- one key point of this CA exercise is to demonstrate knowledge and proficiency with hashing and sorting in particular (this will be talked about in the demos)
