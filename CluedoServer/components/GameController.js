var express = require('express');
const { v4: uuidv4 } = require('uuid');
const Stopwatch = require('statman-stopwatch');
const stopwatch = new Stopwatch();
var router = express.Router();

//Array that holds up to 5 users which will play together
let players = [];
//Array of all current Games running
let currentGames = [];
let stopwatchRunning = false;

// define the home page route
router.get('/', function (req, res) {
  res.send('Game home page');
});

//Route for the players to register to a game
router.post('/register', function (req, res) {
  players.push({ id: uuidv4(), userName: req.body.Username });

  if (!stopwatchRunning) {
    stopwatch.start();
    stopwatchRunning = true;
  }

  res.status(200).send();
});

//Route for the players to check if the game
router.get('/checkGameState', function (req, res) {
  if (
    players.length >= 2 &&
    players.length <= 6 &&
    stopwatch.read() / 1000 <= 10
  ) {
    let newGame = { gameId: uuidv4(), players: players };
    currentGames.push(newGame);
    res.status(200).json(newGame);
  } else {
    res.status(212).json({ Info: 'Still waiting for other players.' });
  }
});

module.exports = router;
