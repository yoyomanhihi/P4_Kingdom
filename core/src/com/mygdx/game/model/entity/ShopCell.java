package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ShopCell extends Group {
    private Tower tower;
    private Label nameLabel;
    private int cost;
    private Label costLabel;
    private Texture texture;
    private ImageButton btn;
    private BitmapFont font;

    private float MENU_WIDTH = Gdx.graphics.getWidth()/5.0f;

    public ShopCell(Tower tw, BitmapFont fnt){
        this.tower = tw;
        cost = tower.getPrice();
        texture = tower.getTexture();
        font = fnt;
        font.getData().setScale(2f);

        this.setWidth(MENU_WIDTH / 2.2f);
        this.setHeight(this.getWidth()*4/3f);

        Drawable image = new TextureRegionDrawable(texture);
        image.setMinHeight(100);
        image.setMinWidth(100);
        btn = new ImageButton(image);
        btn.setPosition(this.getWidth() / 2f - btn.getWidth() / 2f,this.getHeight() / 2f - btn.getHeight() / 2f);

        nameLabel = new Label(tower.getName(), new Label.LabelStyle(font, Color.WHITE));
        nameLabel.setPosition(this.getWidth() / 2f - nameLabel.getWidth() / 2f,(this.getHeight() / 2f) - (btn.getHeight() / 2f) - nameLabel.getHeight());

        costLabel = new Label(String.format("%d", cost), new Label.LabelStyle(font, Color.WHITE));
        costLabel.setPosition(this.getWidth() / 2f - costLabel.getWidth() / 2f,(this.getHeight() / 2f) - (btn.getHeight() / 2f) - nameLabel.getHeight() - costLabel.getHeight());

        this.addActor(btn);
        this.addActor(nameLabel);
        this.addActor(costLabel);
    }
}
