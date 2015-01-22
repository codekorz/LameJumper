package com.ispira.lamejumper.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.ispira.lamejumper.Assets;
import com.ispira.lamejumper.Lame;
import com.ispira.lamejumper.LameJumper;
import com.ispira.lamejumper.WorldRender;
import com.ispira.lamejumper.WorldUtil;

public class GameScreen extends ScreenAdapter {
	static final float WIDTH = 480;
	static final float HEIGHT = 800;
	
	LameJumper game;
	WorldUtil worldutil;
	WorldRender renderer;
	
	OrthographicCamera camera;
	Vector3 touchPoint;
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_OVER = 3;
	int state;
	String scoreString;
	
	Rectangle pauseRectangle;
	Rectangle resumeRectangle;
	Rectangle restartRectangle;
	Rectangle exitRectangle;
	
	public GameScreen(LameJumper game){
		this.game = game;
		camera = new OrthographicCamera(WIDTH,HEIGHT);
		camera.position.set(WIDTH/2,HEIGHT/2,0);		
		touchPoint = new Vector3();	
		worldutil = new WorldUtil();
		renderer = new WorldRender(game.batch, worldutil);
		state = GAME_RUNNING;
		scoreString = "SCORE: ";
		pauseRectangle = new Rectangle(WIDTH-65, 1, 64, 64);
		resumeRectangle = new Rectangle(135, 470-(86/2), 209, 86);
		restartRectangle = new Rectangle(110, HEIGHT/2-(86/2), 260, 86);
		exitRectangle = new Rectangle(175, 330-(86/2), 137, 86);
	}
	
	public void update(float delta){
		switch(state){
		case GAME_RUNNING:
			gameRunning(delta);
			break;
		case GAME_PAUSED:
			gamePaused();
			break;
		}

	}
	public void gamePaused(){
		if(Gdx.input.justTouched()){
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(resumeRectangle.contains(touchPoint.x, touchPoint.y)){
				state = GAME_RUNNING;
			}
			if(restartRectangle.contains(touchPoint.x, touchPoint.y)){
				game.setScreen(new GameScreen(game));
				return;
			}
			if(exitRectangle.contains(touchPoint.x, touchPoint.y)){
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}
	public void gameRunning(float delta){
		if(Gdx.input.justTouched()){
			camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			if(pauseRectangle.contains(touchPoint.x, touchPoint.y)){
				state = GAME_PAUSED;
				return;
			}
		}
		worldutil.update(delta);
		if(worldutil.lame.state == Lame.STATE_DEAD) state = GAME_PAUSED;
	}
	public void draw(){
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.enableBlending();
		game.batch.begin();
		switch(state){
		case GAME_RUNNING:
			renderScore();
			renderPauseBt();
			break;
		case GAME_PAUSED:
			renderPauseMenu();
			break;
		}

		game.batch.end();
		
	}
	private void renderPauseMenu(){
		game.batch.draw(Assets.resumeBt, 135, 470-(86/2), 209, 86);
		//game.batch.draw(Assets.restartBt, 110, HEIGHT/2-(86/2), 260, 86);
		//game.batch.draw(Assets.exitBt, 175, 330-(86/2), 137, 86);
		
	}
	private void renderPauseBt() {
		game.batch.draw(Assets.pauseBt, WIDTH-65, 1, 64, 64);
	}
	
	private void renderScore(){
		Assets.font.setScale(0.6f);
		Assets.font.draw(game.batch, scoreString+worldutil.score, 10, 800-10);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();	
		
	}
	@Override
	public void pause() {
		if(state == GAME_RUNNING) state = GAME_PAUSED;
	}
}
