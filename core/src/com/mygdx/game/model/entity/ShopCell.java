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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private boolean info = false;
    private boolean showInfo = false;
    private Image select;
    private Table infos;
    private long timeInfo;
    private long selectedTime;

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

        Label name = new Label("Name", new Label.LabelStyle(font, Color.WHITE));
        Label priceLabel = new Label("Price:", new Label.LabelStyle(font, Color.WHITE));
        Label damageLabel = new Label("Damage:", new Label.LabelStyle(font, Color.WHITE));
        Label fireRateLabel = new Label ("Rate:", new Label.LabelStyle(font, Color.WHITE));
        Label rangeLabel = new Label("Range:", new Label.LabelStyle(font, Color.WHITE));
        Label damage = new Label(String.format("%d",tower.getDamage()), new Label.LabelStyle(font, Color.WHITE));
        Label fireRate = new Label(String.format("%.2f",(double)60f/tower.getFireRate()), new Label.LabelStyle(font, Color.WHITE));
        Label range = new Label(String.format("%d",(int)tower.getRange()), new Label.LabelStyle(font, Color.WHITE));
        Label cost = new Label(String.format("%d",tower.getPrice()), new Label.LabelStyle(font, Color.WHITE));

        name.setText(tower.getName());

        infos = new Table();
        infos.setFillParent(true);

        infos.add(name).expandX().expandY().colspan(2).pad(50,0,20,0);
        infos.row();
        infos.add(damageLabel).expandY().left();
        infos.add(damage).expandY().right();
        infos.row();
        infos.add(fireRateLabel).expandY().left();
        infos.add(fireRate).expandY().right();
        infos.row();
        infos.add(rangeLabel).expandY().left();
        infos.add(range).expandY().right();
        infos.row();
        infos.add(priceLabel).expandY().left();
        infos.add(cost).expandY().right();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isSelected()) {
            this.addActor(select);
            selectedTime = System.currentTimeMillis();
        }
        else {
            this.removeActor(select);
            selectedTime = System.currentTimeMillis();
        }
        if (showInfo && System.currentTimeMillis()-timeInfo>5000){
            setInfo(false);
        }
        if (isInfo() && !showInfo) {
            this.showInfo();
            timeInfo = System.currentTimeMillis();
            showInfo = true;
            if (System.currentTimeMillis()-selectedTime<500) {
                setSelected(!selected);
            }
        }
        else if (!isInfo() && showInfo){
            this.hideInfo();
            showInfo = false;
        }
    }

    private void showInfo(){
        this.removeActor(btn);
        this.removeActor(nameLabel);
        this.removeActor(costLabel);
        this.addActor(infos);
    }

    private void hideInfo(){
        this.removeActor(infos);
        this.addActor(btn);
        this.addActor(nameLabel);
        this.addActor(costLabel);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
}
