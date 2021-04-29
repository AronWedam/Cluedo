const express = require('express');
const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());

var games = require('./components/GameController');
app.use('/games', games);

//For testing the API
app.get('/', (req, res) => {
  res.send('Hello World from the SE2 Cluedo App!');
});

app.listen(port, () => {
  console.log(`App now listening at http://localhost:${port}`);
});
