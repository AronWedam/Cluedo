package com.cluedo.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.cluedo.game.network.NetworkPlayer;

import java.util.ArrayList;
import java.util.List;

public class Cluedo implements Screen, GestureDetector.GestureListener{
    private GameClass game;
    private com.cluedo.game.network.ConnectionService connectionService;
    public Player player;
    private Rectangle piece;
    private List<Player> players;
    private List<Rectangle> pieces;
    private GestureDetector gestureDetector;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMap map;
    public CluedoMap cluedoMap = new CluedoMap();
    private float currentZoom;
    private SpriteBatch Notebookbatch;
    private Notebook notebook;
    private Player currentPlayer;
    private MainScreen mainScreen;
    private RulesScreen rulesScreen;
    private Stage stage;
    private Viewport viewport = new ScreenViewport();
    private InputMultiplexer multiplexer;
    private int moves = 0;
    private BitmapFont font;
    private Toast.ToastFactory toastFactory;
    private Toast toast;
    private boolean setCardsFlag = false;

    public Cluedo(final GameClass game, MainScreen mainScreen) {
        this.game = game;
        this.mainScreen = mainScreen;
        rulesScreen = new RulesScreen(game, mainScreen);

        font = new BitmapFont();
        font.getData().setScale(2,2);

        toastFactory = new Toast.ToastFactory.Builder()
                .font(font)
                .build();

        players = new ArrayList<>();
        pieces = new ArrayList<>();
        //Get the Players of the Current Game
        connectionService = com.cluedo.game.network.ConnectionService.GetInstance();

        map = new TmxMapLoader().load("maps/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        //create gestureDetector
        gestureDetector = new GestureDetector(this);

        //create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //batch for the viewportNotebook method
        Notebookbatch = new SpriteBatch();

        getPlayers();

        //set up stage and Multiplexer to handle Inputs
        stage = new Stage(viewport);
        multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(0,stage);
        multiplexer.addProcessor(1,gestureDetector);
        Gdx.input.setInputProcessor(multiplexer);

        Gdx.app.log("Room", connectionService.getRoom());
        Gdx.app.log("Weapon", connectionService.getWeapon());
        Gdx.app.log("Suspect", connectionService.getSuspect());
    }

    private void SyncNetworkPlayersWithGamePlayers() {
        List<Player> tempPlayers = new ArrayList<>();
        List<Rectangle> tempRectange = new ArrayList<>();

        for(NetworkPlayer currentPlayer : connectionService.getPlayers()) {
            if (currentPlayer.getId().equals(connectionService.GetPlayerId())) {
                piece = new Rectangle();
                piece.x = currentPlayer.getX();
                piece.y = currentPlayer.getY();
                piece.width = 32;
                piece.height = 32;

                //create the player
                player = new Player(new Texture(currentPlayer.getPlayerImage()), cluedoMap, (int) piece.x, (int) piece.y, this);
                this.currentPlayer = player;
                connectionService.setCurrentPlayer(currentPlayer);

                tempPlayers.add(player);
                tempRectange.add(piece);
            } else {
                //create a Rectangle to logically represent one player
                Rectangle rect = new Rectangle();
                rect.x = currentPlayer.getX();
                rect.y = currentPlayer.getY();
                rect.width = 32;
                rect.height = 32;

                //create the player
                Player otherPlayer = new Player(new Texture(currentPlayer.getPlayerImage()), cluedoMap, (int) rect.x, (int) rect.y, this);
                tempPlayers.add(otherPlayer);
                tempRectange.add(rect);
            }
        }
        players = tempPlayers;
        pieces = tempRectange;
    }

    private void getPlayers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectionService.GetGame();

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (connectionService.isGameOver()) {
                            mainScreen.setScreen(new GameOverScreen());
                        }

                        SyncNetworkPlayersWithGamePlayers();

                        if (!setCardsFlag) {
                            currentPlayer.setMyRoomCard();
                            currentPlayer.setMySuspectCard();
                            currentPlayer.setMyWeaponCard();

                            notebook = Notebook.getInstance(currentPlayer);
                            setCardsFlag = true;
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        boolean moved = false;
        //clear the screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set up map and camera
        renderer.setView(camera);
        renderer.render();
        camera.update();

        if (toast != null) {
            toast.render(Gdx.graphics.getDeltaTime());
        }

        if (setCardsFlag) {
            mapViewport();
            mapNotebook();
        }

        game.batch.setProjectionMatrix(camera.combined);

        if (!Gdx.input.justTouched()) {
            getPlayers();
        }

        //Draw the players
        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            Rectangle currentPiece = pieces.get(i);
            currentPlayer.render(camera, game.batch, currentPlayer.getX(), currentPlayer.getY(), currentPiece.width, currentPiece.height);
        }

        //Single Touch enables player Movement for 1 Tile
        if (Gdx.input.justTouched() && Gdx.input.getX(0) > Gdx.graphics.getWidth() / 3 && moves > 0) {
            double x = Gdx.input.getX(0) - (Gdx.graphics.getWidth() / 3);
            double y = Gdx.input.getY(0);

            //Check if Player figure is touched - doesn't work at all
            if (x >= player.getX() - 1 || x <= player.getX() + 1 && y >= player.getY() - 1 || y <= player.getY() + 1) {
                //Save Coordinates of Touched Area
                if (Gdx.input.justTouched()) {
                    Vector3 touchPos = new Vector3();
                    touchPos.set((float) x, Gdx.input.getY(0), 0);
                    camera.unproject(touchPos);

                    //Make move in touched direction
                    if (touchPos.x > player.getX() || touchPos.y > player.getY()) {
                        if (touchPos.x > player.getX()) {
                            player.setPos((int) player.getX() + 32, player.getY());
                            if (touchPos.y > player.getY()) {
                                player.setPos((int) player.getX(), player.getY() + 32);
                            }
                            if (!moved) {
                                moves--;
                                moved = true;
                            }
                        } else if (touchPos.y > player.getY()) {
                            player.setPos((int) player.getX(), player.getY() + 32);
                            if (!moved) {
                                moves--;
                                moved = true;
                            }
                        }
                    }

                    if (touchPos.x < player.getX() || touchPos.y < player.getY()) {
                        if (touchPos.x < player.getX()) {
                            player.setPos((int) player.getX() - 32, player.getY());
                            if (touchPos.y < player.getY()) {
                                player.setPos((int) player.getX(), player.getY() - 32);
                            }
                            if (!moved) {
                                moves--;
                            }
                        } else if (touchPos.y < player.getY()) {
                            player.setPos((int) player.getX(), player.getY() - 32);
                            if (!moved) {
                                moves--;
                            }
                        }
                    }
                }
            }
        } else if (Gdx.input.justTouched() && Gdx.input.getX(0) > Gdx.graphics.getWidth() / 3 && moves == 0) {
            toast = toastFactory.create("You need to roll the dice first", Toast.Length.LONG);
        }

        if (Gdx.input.justTouched() && Gdx.input.getX(0) < Gdx.graphics.getWidth() / 3) {
            double y = Gdx.input.getY(0);
            int row = notebook.table.getRow((float) (Gdx.graphics.getHeight() - y));
            Gdx.app.log("Row", "Row: " + row);

            if (row <= 30 && row >= 0) {
                try {
                    Actor actor = notebook.table.getChild(row);
                    Gdx.app.log("Class", actor.getClass().getName());

                    if (actor instanceof TextButton) {
                        TextButton myButton = (TextButton) actor;
                        String clickedName = myButton.getText().toString();
                        buttonsNotebook(clickedName);
                    }

                    if (actor instanceof CheckBox) {
                        CheckBox myCheckBox = (CheckBox) actor;
                        String clickedCheckbox = myCheckBox.getText().toString();
                        checkBoxesNotebook(clickedCheckbox);
                    }
                } catch (Exception ex) {
                    Gdx.app.log("Exception", ex.getMessage());
                }
            }
        }
    }

    private void buttonsNotebook(String clickedName){
        switch (clickedName) {
            case "Finish Move":
                if (connectionService.getCurrentPlayer() != null && connectionService.getCurrentPlayer().getMaywalk()) {
                    Thread finishMoveThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            connectionService.FinishMove();
                        }
                    });
                    finishMoveThread.start();
                    try {
                        finishMoveThread.join();
                    } catch (InterruptedException e) {
                        Gdx.app.log("Thread-Exception", e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
                break;
            case "Accusation":
                mainScreen.setScreen(new AccusationScreen(game, mainScreen, this));
                break;
            case "Help":
                mainScreen.setScreen(rulesScreen);
                break;
            case "Dice":
                mainScreen.setScreen(new DiceScreen(game, mainScreen, this));
                break;
            default:
                break;
        }
    }

    private void checkBoxesNotebook(String clickedCheckbox){
        switch (clickedCheckbox) {
            //PERSON
            case "MissScarlett":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBMissScarlett);
                } else {
                    notebook.checkCheckBox(notebook.cBMissScarlett);
                }
                break;
            case "ColonelMustard":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBColonelMustard);
                } else {
                    notebook.checkCheckBox(notebook.cBColonelMustard);
                }
                break;
            case "MrsWhite":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBMrsWhite);
                } else {
                    notebook.checkCheckBox(notebook.cBMrsWhite);
                }
                break;
            case "Reverend":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBReverend);
                } else {
                    notebook.checkCheckBox(notebook.cBReverend);
                }
                break;
            case "MrsPeacock":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBMrsPeacock);
                } else {
                    notebook.checkCheckBox(notebook.cBMrsPeacock);
                }
                break;
            case "ProfessorPlum":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBProfessorPlum);
                } else {
                    notebook.checkCheckBox(notebook.cBProfessorPlum);
                }
                break;

            //ROOM
            case "Entrance":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomEntrance);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomEntrance);
                }
                break;
            case "Bathroom":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomBathroom);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomBathroom);
                }
                break;
            case "Dining":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomDining);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomDining);
                }
                break;
            case "Kitchen":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomKitchen);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomKitchen);
                }
                break;
            case "Bedroom":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomBedroom);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomBedroom);
                }
                break;
            case "Musicroom":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomMusicroom);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomMusicroom);
                }
                break;
            case "Guestroom":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomGuest);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomGuest);
                }
                break;
            case "Study":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomStudy);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomStudy);
                }
                break;
            case "Library":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBRoomLibrary);
                } else {
                    notebook.checkCheckBox(notebook.cBRoomLibrary);
                }
                break;
            //ROOM
            case "Knife":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponKnife);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponKnife);
                }
                break;
            case "Rope":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponRope);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponRope);
                }
                break;
            case "Gun":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponGun);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponGun);
                }
                break;
            case "Poison":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponPoison);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponPoison);
                }
                break;
            case "Pipe":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponPipe);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponPipe);
                }
                break;
            case "Candle":
                if (notebook.isChecked) {
                    notebook.uncheckCheckBox(notebook.cBWeaponCandle);
                } else {
                    notebook.checkCheckBox(notebook.cBWeaponCandle);
                }
                break;
            default:
                break;
        }
    }

    private void mapViewport(){
        Gdx.gl.glViewport(0,0, (int) (Gdx.graphics.getWidth()/1.5),
                Gdx.graphics.getHeight());
    }

    private void mapNotebook() {
        notebook.yourSuspectCard();
        notebook.yourRoomCards();
        notebook.yourWeaponCard();

        notebook.getPane().setBounds(0, 0, Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight());


        Notebookbatch.begin();
        notebook.getPane().draw(Notebookbatch, 1);
        notebook.table.draw(Notebookbatch, 1);
        Notebookbatch.end();


        Gdx.gl.glViewport(Gdx.graphics.getWidth() / 3, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        map.dispose();

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        currentZoom = camera.zoom;
        Gdx.app.log("INFO", "x:" + x + "y:" + y);
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX/2, deltaY/2);
        camera.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("INFO", "panStop");
        currentZoom = camera.zoom;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        camera.zoom = (initialDistance / distance) * currentZoom;
        camera.update();
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getMoves() {
        return moves;
    }
}
