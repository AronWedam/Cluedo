import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.cluedo.game.GameOverScreen;

import org.junit.Assert;
import org.junit.Test;

public class TestGameOverScreen {

    GameOverScreen gameOver = new GameOverScreen();

    Stage stage = new Stage();

    //TODO bei zu testenden Methoden die sich auf Dateien beziehen wird immer eine Nullpointerexception geworfen.

    @Test
    public void testSetup() {
        createTable();
        Assert.assertEquals(stage, gameOver.getStage());
    }

    private void createTable(){

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.align(Align.top);


        //Add Text and Buttons to the table
        mainTable.add("GAME OVER").align(Align.center);
        mainTable.row().colspan(2);
        mainTable.add("A correct accusation was pronounced.").align(Align.left);
        mainTable.row().colspan(2);

        stage.addActor(mainTable);
    }
}
