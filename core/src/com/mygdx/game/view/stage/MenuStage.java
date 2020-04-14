package com.mygdx.game.view.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ProtectTheKingdom;
import com.mygdx.game.model.entity.Player;
import com.mygdx.game.model.entity.ShopCell;
import com.mygdx.game.model.entity.Tower;
import com.mygdx.game.view.screen.MenuScreen;


public class MenuStage extends Stage {

    private Label moneyLabel;
    private Label lifeLabel;
    private Player player;

    private ShopCell item1;
    private ShopCell item2;
    private ShopCell item3;
    private ShopCell item4;
    private ShopCell item5;
    private ShopCell item6;

    public MenuStage(Viewport viewport, final ProtectTheKingdom game, Tower tower1, Tower tower2, Tower tower3, Tower tower4, Tower tower5, Tower tower6){
        super(viewport, game.batch);

        this.player = game.player;
        BitmapFont font = new BitmapFont();

        Texture Exit = new Texture("x.png");
        Drawable exitImage = new TextureRegionDrawable(Exit);
        Texture Menu = new Texture("levelsel.png");
        Drawable menuImage = new TextureRegionDrawable(Menu);
        Image Coin = new Image(new Texture("coin.png"));
        Image Heart = new Image(new Texture("heart.png"));

        ImageButton exitButton = new ImageButton(exitImage);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        ImageButton menuButton = new ImageButton(menuImage);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

        moneyLabel = new Label("MONEY", new Label.LabelStyle(font, Color.WHITE));
        lifeLabel = new Label("LIFE", new Label.LabelStyle(font, Color.WHITE));

        item1 = new ShopCell(tower1, font);
        item2 = new ShopCell(tower2, font);
        item3 = new ShopCell(tower3, font);
        item4 = new ShopCell(tower4, font);
        item5 = new ShopCell(tower5, font);
        item6 = new ShopCell(tower6, font);

        Table menu = new Table();
        menu.setFillParent(true);

        menu.add(menuButton).right().colspan(2);
        menu.add(exitButton).right().colspan(2);
        menu.row();
        menu.add(item1).expandX().expandY().colspan(2);
        menu.add(item2).expandX().expandY().colspan(2);
        menu.row();
        menu.add(item3).expandX().expandY().colspan(2);
        menu.add(item4).expandX().expandY().colspan(2);
        menu.row();
        menu.add(item5).expandX().expandY().colspan(2);
        menu.add(item6).expandX().expandY().colspan(2);
        menu.row();
        menu.add(Coin).expandX().right().size(60, 60).colspan(1);
        menu.add(moneyLabel).expandX().left().expandX().colspan(1);
        menu.add(Heart).expandX().right().size(60,60).colspan(1);
        menu.add(lifeLabel).expandX().left().expandX().colspan(1);

        this.addActor(menu);
    }

    @Override
    public void act(){
        moneyLabel.setText(String.format("%04d", player.getMoney()));
        lifeLabel.setText(String.format("%01d", player.getLife()));
        super.act();
    }

    public void deselect() {
        getItem1().setSelected(false);
        getItem2().setSelected(false);
        getItem3().setSelected(false);
        getItem4().setSelected(false);
        getItem5().setSelected(false);
        getItem6().setSelected(false);
    }

    public ShopCell getItem1() {
        return item1;
    }

    public ShopCell getItem2() {
        return item2;
    }

    public ShopCell getItem3() {
        return item3;
    }

    public ShopCell getItem4() {
        return item4;
    }

    public ShopCell getItem5() {
        return item5;
    }

    public ShopCell getItem6() {
        return item6;
    }
}
