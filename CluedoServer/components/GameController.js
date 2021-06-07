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
let firstPlayer = true;

// define the home page route
router.get('/', function (req, res) {
  res.status(200).send(currentGame);
});

//Route for the players to register to a game
router.post('/register', function (req, res) {
  let playerId = uuidv4();
  if (playerImagesCounter >= playerImages.length) playerImagesCounter = 0;
  if (players.length >= 1) firstPlayer = false;

  players.push({
    id: playerId,
    username: req.body.Username,
    x: positionCounter * 32,
    y: 0,
    playerImage: playerImages[playerImagesCounter],
    maywalk: firstPlayer,
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
      if (players[i].maywalk === true) {
        players[i].x = x;
        players[i].y = y;
        currentPlayer = players[i];
      }
    }
  }
  console.log('New Pos');
  console.log(players);

  res.status(200).send();
});

router.post('/finishMove', function (req, res) {
  let index;

  for (let i = 0; i < players.length; i++) {
    if (players[i].id === req.body.playerId) {
      index = i;

      //Its the last player
      if (i === players.length - 1) {
        players[0].maywalk = true;
      } else {
        players[i + 1].maywalk = true;
      }

      break;
    }
  }

  players[index].maywalk = false;

  res.status(200).send();
});

//Resetting the new Gamestate
router.get('/reset', function (req, res) {
  players = [];
  currentGame = undefined;
  positionCounter = 0;
  playerImagesCounter = 0;
  firstPlayer = true;
  stopwatch.stop();
  res.send(200).send();
});

module.exports = router;
