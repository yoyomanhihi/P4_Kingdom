package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ShopCell extends Group {
    private Tower tower;
    private Label nameLabel;
    private int cost;
    private Label costLabel;
    private Texture globalTexture;
    private ImageButton btn;
    private BitmapFont font;
    private boolean selected = false;
    private Image select;

    private float MENU_WIDTH = Gdx.graphics.getWidth()/5.0f;

    public ShopCell(Tower tw, BitmapFont fnt){
        this.tower = tw;
        cost = tower.getPrice();
        globalTexture = tower.getGlobalTexture();
        font = fnt;
        font.getData().setScale(2f);

        this.setWidth(MENU_WIDTH / 2.2f);
        this.setHeight(this.getWidth()*4/3f);

        if (tower.getID() <= 4) {
            Pixmap pixmap200 = new Pixmap(Gdx.files.internal("weapon_sprites/Weapon"+tower.getID()+".png"));
            Pixmap pixmap100 = new Pixmap(70, 150, pixmap200.getFormat());
            pixmap100.drawPixmap(pixmap200,
                    0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                    0, 0, pixmap100.getWidth(), pixmap100.getHeight()
            );
            globalTexture = new Texture(pixmap100);
            pixmap200.dispose();
            pixmap100.dispose();
        }

        if (tower.getID() == 5) {
            Pixmap pixmap200 = new Pixmap(Gdx.files.internal("snowflake.png"));
            Pixmap pixmap100 = new Pixmap(100, 100, pixmap200.getFormat());
            pixmap100.drawPixmap(pixmap200,
                    0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
                    0, 0, pixmap100.getWidth(), pixmap100.getHeight()
            );
            globalTexture = new Texture(pixmap100);
            pixmap200.dispose();
            pixmap100.dispose();
        }

        Drawable image = new TextureRegionDrawable(globalTexture);

        Texture selectTexture = new Texture("yellow.png");
        select = new Image(selectTexture);
        select.setHeight(30);
        select.setWidth(30);
        select.setPosition(this.getWidth()/2f-select.getWidth()/2f, this.getHeight()-select.getHeight());

        btn = new ImageButton(image);
        if (tower.getID()<5) btn.setScale(2f);
        btn.setPosition(this.getWidth() / 2f - btn.getWidth() / 2f,this.getHeight() / 2f - btn.getHeight() / 2f);

        nameLabel = new Label(tower.getName(), new Label.LabelStyle(font, Color.WHITE));
        nameLabel.setPosition(this.getWidth() / 2f - nameLabel.getWidth() / 2f,(this.getHeight() / 2f) - (btn.getHeight() / 2f) - nameLabel.getHeight());

        costLabel = new Label(String.format("%d", cost), new Label.LabelStyle(font, Color.WHITE));
        costLabel.setPosition(this.getWidth() / 2f - costLabel.getWidth() / 2f,(this.getHeight() / 2f) - (btn.getHeight() / 2f) - nameLabel.getHeight() - costLabel.getHeight());

        this.addActor(btn);
        this.addActor(nameLabel);
        this.addActor(costLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isSelected()) {
            this.addActor(select);
        }
        else this.removeActor(select);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
