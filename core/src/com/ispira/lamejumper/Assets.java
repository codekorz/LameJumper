package com.ispira.lamejumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static Texture background;
	public static TextureRegion bgRegion;
	
	public static Music music;
	
	public static Texture items;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion playBt;
	public static TextureRegion helpBt;
	public static TextureRegion highscoreBt;
	public static TextureRegion platform;
	public static TextureRegion pauseBt;
	public static TextureRegion restartBt;
	public static TextureRegion resumeBt;
	public static TextureRegion exitBt;
	
	public static Texture character;
	public static Texture char_inplatform;
	
	public static Texture bird;
	public static Texture coin;
	
	public static TextureRegion button;
	public static BitmapFont font;
	
	public static void load(){
		// load background
		background = new Texture(Gdx.files.internal("images/background.png"));
		bgRegion = new TextureRegion(background, 0, 0, 480, 800);
		
		// load items 
		items = new Texture(Gdx.files.internal("images/items.png"));
		soundOn = new TextureRegion(items, 0, 0, 80, 80);
		soundOff = new TextureRegion(items, 80, 0, 80, 80);
		pauseBt = new TextureRegion(items, 160, 8, 64, 64);
		helpBt = new TextureRegion(items, 0, 80, 104, 46);
		playBt = new TextureRegion(items, 104, 80, 104, 46);
		highscoreBt = new TextureRegion(items, 0, 126, 204, 46);
		platform = new TextureRegion(items, 0, 172, 95, 20);
		resumeBt = new TextureRegion(items, 275, 0, 209, 86);
		restartBt = new TextureRegion(items, 253, 86, 259, 86);
		exitBt = new TextureRegion(items, 375, 172, 137, 86);
		logo = new TextureRegion(items, 0, 312, 450, 200);
		
		character = new Texture(Gdx.files.internal("images/lame-spritesheet.png"));
		char_inplatform = new Texture(Gdx.files.internal("images/p1_duck.png"));
	
		bird = new Texture(Gdx.files.internal("images/bird.png"));
		coin = new Texture(Gdx.files.internal("images/coin.png"));
		
		
		// load font
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
		
		// load music
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		music.setLooping(true);
		//music.play();
	}	
}
