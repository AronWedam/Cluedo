var express = require('express');
const { v4: uuidv4 } = require('uuid');
const Stopwatch = require('statman-stopwatch');
const stopwatch = new Stopwatch();
var router = express.Router();

//Array that holds up to 5 users which will play together
let players = [];
let playerImages = [
  'chars/Col_Mustard.png',
  'chars/Mr_Green.png',
  'chars/Mrs_Peacock.png',
  'chars/Mrs_Scarlet.png',
  'chars/Mrs_White.png',
  'chars/Prof_Plum.png',
];

//Array of all current Games running
let currentGame = undefined;
let stopwatchRunning = false;
let positionCounter = 0;
let playerImagesCounter = 0;

// define the home page route
router.get('/', function (req, res) {
  res.status(200).send(currentGame);
});

//Route for the players to register to a game
router.post('/register', function (req, res) {
  let playerId = uuidv4();
  if (playerImagesCounter > playerImages.length) playerImagesCounter = 0;

  players.push({
    id: playerId,
    username: req.body.Username,
    x: positionCounter * 32,
    y: 0,
    playerImage: playerImages[playerImagesCounter],
  });

  if (!stopwatchRunning) {
    stopwatch.start();
    stopwatchRunning = true;
  }

  playerImagesCounter++;
  positionCounter++;
  console.log('Register');
  console.log(players);
  res.status(200).send({ playerId: playerId });
});

//Route for the players to check if the game
router.get('/checkGameState', function (req, res) {
  if (
    players.length >= 1 &&
    players.length <= 6 &&
    stopwatch.read() / 1000 >= 10
  ) {
    console.log('here');
    currentGame = { gameId: uuidv4(), players: players };
    console.log(currentGame);
    res.status(200).json(currentGame);
  } else {
    res.status(212).json({ Info: 'Still waiting for other players.' });
  }
});

//Posting the new position and updating the player
router.post('/playerMoved', function (req, res) {
  let x = req.body.x;
  let y = req.body.y;
  console.log('Moved');
  console.log(req.body);
  console.log(players);

  for (let i = 0; i < players.length; i++) {
    if (players[i].id === req.body.playerId) {
      players[i].x = x;
      players[i].y = y;
      currentPlayer = players[i];
    }
  }
  console.log('New Pos');
  console.log(players);

  res.status(200).send();
});

//Resetting the new Gamestate
router.get('/reset', function (req, res) {
  players = [];
  currentGame = undefined;
  positionCounter = 0;
  stopwatch.stop();
  res.send(200).send();
});

//Getting the new Gamestate
router.get('/getGame/:id', function (req, res) {
  let id = req.params.id;
  let retGame = currentGames.filter((x) => x.id === id);

  if (retGame.length > 0) res.status(200).send(retGame[0]);
  else res.send(404).send();
});

module.exports = router;
