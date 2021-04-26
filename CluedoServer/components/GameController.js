var express = require('express');
const uuid = require('uuid');
var router = express.Router();

//Array that holds up to 5 users which will play together
let players = [];
//Array of all current Games running
let currentGames = [];

// define the home page route
router.get('/', function (req, res) {
  res.send('Game home page');
});

//Route for connecting to a game
router.post('/', function (req, res) {
  players.push({ id: uuid.v4(), userName: req.body.Username });

  if (players.length === 5) {
    let newGame = { gameId: uuid.v4(), players: players };
    res.status(200).json(newGame);
    currentGames.push(newGame);
    players = [];
  } else {
    res.send(`Waiting for ${5 - players.length} players...`);
  }
});

module.exports = router;
