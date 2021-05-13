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
  let playerId = uuidv4();
  players.push({ id: playerId, userName: req.body.Username, x: 0, y: 0 });

  if (!stopwatchRunning) {
    stopwatch.start();
    stopwatchRunning = true;
  }

  res.status(200).send(playerId);
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

//Posting the new position and updating the player
router.post('/playerMoved', function (req, res) {
  let x = req.body.x;
  let y = req.body.y;

  for (let i = 0; i < players.length; i++) {
    if (players[i].id === req.body.playerId) {
      players[i].x = x;
      players[i].y = y;
      currentPlayer = players[i];
    }
  }

  console.log(currentPlayer);
  res.status(200).send();
});

//Getting the new Gamestate
router.get('/gameChanged', function (req, res) {
  res.status(200).send(players);
});

module.exports = router;
