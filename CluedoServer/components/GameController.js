var express = require('express');
const { v4: uuidv4 } = require('uuid');
const Stopwatch = require('statman-stopwatch');
const stopwatch = new Stopwatch();
var router = express.Router();

//Array that holds up to 5 users which will play together
let players = [
  { id: '1234', username: 'Alex' },
  { id: '2346', username: 'Mira' },
  { id: '1246', username: 'Sarah' },
  { id: '5232', username: 'Aron' },
];

//Array of all current Games running
let currentGames = [{ id: '5431', players: players }];
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
router.get('/:id', function (req, res) {
  let id = req.params.id;
  let retGame = currentGames.filter((x) => x.id === id);

  if (retGame.length > 0) res.status(200).send(retGame[0]);
  else res.send(404).send();
});

module.exports = router;
