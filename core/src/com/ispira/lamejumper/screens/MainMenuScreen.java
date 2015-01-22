package com.ispira.lamejumper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.ispira.lamejumper.Assets;
import com.ispira.lamejumper.LameJumper;

public class MainMenuScreen extends ScreenAdapter {

	LameJumper game;
	OrthographicCamera camera;
	Vector3 touchPoint;
	
	Rectangle soundButton;
	Rectangle playButton;
	Rectangle helpButton;
	Rectangle highscoreButton;
	
	public static boolean soundState = true;
	private final float WIDTH = 480;
	private final float HEIGHT = 800;
	
	public MainMenuScreen(LameJumper game){
		this.game = game;
		
		camera = new OrthographicCamera(WIDTH,HEIGHT);
		camera.position.set(WIDTH/2,HEIGHT/2,0);
		
		soundButton = new Rectangle(400,0, 80, 80);
		playButton = new Rectangle((480 / 2 - 104 / 2), 400, 104, 46);
		helpButton = new Rectangle((480 / 2 - 104 / 2), 300, 104, 46);
		highscoreButton = new Rectangle((480/2 - 104/2), 200, 204, 46);
		touchPoint = new Vector3();
	}
	

	@Override
	public void render(float delta) {
		update();
		draw();
	}
	
	public void update(){
		if (Gdx.input.justTouched()) {
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			// playButton
			if(playButton.contains(touchPoint.x, touchPoint.y)){
				game.setScreen(new GameScreen(game));
			}
			
			// check sound
			if(soundButton.contains(touchPoint.x, touchPoint.y)){
				soundState = !soundState;
				if(soundState)	Assets.music.play(); 
					else Assets.music.pause();
			}
		}
	}
	public void draw(){
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.disableBlending();
		game.batch.begin();
		game.batch.draw(Assets.bgRegion, 0, 0, 480, 800);
		game.batch.end();
		
		game.batch.enableBlending();  
		game.batch.begin();
		game.batch.draw(Assets.logo, (480 / 2 - 450 / 2), (800-300), 450, 200);
		game.batch.draw(Assets.playBt, (480 / 2 - 104 / 2), 400, 104, 46);
		game.batch.draw(Assets.helpBt, (480/2 - 104/2), 300, 104, 46);
		game.batch.draw(Assets.highscoreBt, (480/2 - 204/2), 200, 204, 46);
		game.batch.draw(soundState ? Assets.soundOn : Assets.soundOff, 400, 0, 80, 80);
		game.batch.end();
	}
	
	@Override
	public void pause(){
		
	}

}