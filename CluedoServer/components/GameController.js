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

let suspects = [
  'MissScarlett',
  'ColonelMustard',
  'MrsWhite',
  'Reverend',
  'MrsPeacock',
  'ProfessorPlum',
];

let rooms = [
  'Entrance',
  'Bedroom',
  'Dining',
  'Kitchen',
  'Guestroom',
  'Musicroom',
  'Bathroom',
  'Study',
  'Library',
];

let weapons = ['Knife', 'Rope', 'Gun', 'Poison', 'Pipe', 'Candle'];

let finishCombination = undefined;

let spawnPositions = [
  {
    x: 10 * 32,
    y: 0,
  },
  {
    x: 21 * 32,
    y: 0,
  },
  {
    x: 0,
    y: 10 * 32,
  },
  {
    x: 0,
    y: 21 * 32,
  },
  {
    x: 21 * 32,
    y: 28 * 32,
  },
  {
    x: 28 * 32,
    y: 7 * 32,
  },
];

//Array of all current Games running
let currentGame = undefined;
let stopwatchRunning = false;
let playerImagesCounter = 0;
let firstPlayer = true;
let isGameOver = false;

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
    x: spawnPositions[playerImagesCounter].x,
    y: spawnPositions[playerImagesCounter].y,
    playerImage: playerImages[playerImagesCounter],
    maywalk: firstPlayer,
  });

  if (!stopwatchRunning) {
    stopwatch.start();
    stopwatchRunning = true;
  }

  console.log('Register');
  console.log(players);
  res.status(200).send({ playerId: playerId });
});

function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

//Route for the players to check if the game
router.get('/checkGameState', function (req, res) {
  if (
    players.length >= 1 &&
    players.length <= 6 &&
    stopwatch.read() / 1000 >= 10
  ) {
    randomWeapon = weapons[getRandomInt(weapons.length - 1)];
    randomRoom = rooms[getRandomInt(rooms.length - 1)];
    randomSuspect = suspects[getRandomInt(suspects.length - 1)];
    if (!finishCombination)
      finishCombination = {
        weapon: randomWeapon,
        room: randomRoom,
        suspect: randomSuspect,
      };
    if (!currentGame)
      currentGame = {
        gameId: uuidv4(),
        players: players,
        finishCombination: finishCombination,
        isGameOver: isGameOver,
      };

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

router.post('/finishGame', function (req, res) {
  currentGame.isGameOver = true;
  res.status(200).send();
  resetEverything();
});

//Resetting the new Gamestate
router.get('/reset', function (req, res) {
  resetEverything();
  res.send(200).send();
});

function resetEverything() {
  players = [];
  currentGame = undefined;
  playerImagesCounter = 0;
  firstPlayer = true;
  isGameOver = false;
  stopwatch.stop();
}

module.exports = router;
