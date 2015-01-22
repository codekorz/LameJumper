package com.ispira.lamejumper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRender {
	static final float WIDTH = 16;
	static final float HEIGHT = 26;
	
	WorldUtil world;
	OrthographicCamera cam;
	SpriteBatch batch;
	LameAnimation lame;
	BirdAnimation bird;
	CoinAnimation coin;
	ShapeRenderer sr;
	
	int side = 1;
	
	public WorldRender(SpriteBatch batch, WorldUtil world){
		this.world = world;
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.position.set(WIDTH/2, HEIGHT/2, 0);
		this.batch = batch;
		lame = new LameAnimation();
		bird = new BirdAnimation();
		coin = new CoinAnimation();
		sr = new ShapeRenderer();
	}
	public void render() {
		if(world.lame.position.y > cam.position.y) cam.position.y = world.lame.position.y;
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		batch.enableBlending();
		batch.begin();
		renderPlatforms();
		renderCharacter();
		renderBirds();
		renderCoins();
		batch.end();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		for(Bird b : world.birds){
			sr.rect(world.lame.position.x, world.lame.position.y, world.lame.bounds.width, world.lame.bounds.height);
			sr.rect(b.bounds.x, b.bounds.y, b.bounds.width, b.bounds.height);	
		sr.rect(world.lame.position.x, world.lame.position.y, world.lame.bounds.width, world.lame.bounds.height);
		sr.end();
	}
	private void renderCoins(){
		TextureRegion coinFrame;
		for(Coin c : world.coins){
			coinFrame = coin.coinAnimation.getKeyFrame(c.stateTime, true);
			batch.draw(coinFrame, c.position.x, c.position.y, Coin.WIDTH, Coin.HEIGHT);
		}
	}
	private void renderBirds(){
		TextureRegion birdFrame;
		for(Bird b : world.birds){
			if(b.state == Bird.STATE_DEAD) birdFrame = bird.dieFrames;
			else birdFrame = bird.flyAnimation.getKeyFrame(b.stateTime, true);
			int side = 1;
			if(b.type == Bird.TYPE_RIGHT) side = -1;
			batch.draw(birdFrame, b.position.x-Bird.WIDTH/2, b.position.y-Bird.HEIGHT/2,
					Bird.WIDTH/2, Bird.HEIGHT/2,
					Bird.WIDTH, Bird.HEIGHT, side, 1, 0);
		}
	}
	private void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.bgRegion, cam.position.x - WIDTH/2, cam.position.y - HEIGHT/2,
				WIDTH, HEIGHT);
		batch.end();
	}
	private void renderPlatforms() {
		for(Platform p : world.platforms){
			batch.draw(Assets.platform, p.position.x-Platform.WIDTH/2, p.position.y-Platform.HEIGHT/2, 
					Platform.WIDTH, Platform.HEIGHT);
		}
	}

	private void renderCharacter() {
		TextureRegion currentFrame;
		switch(world.lame.state){
		case Lame.STATE_JUMP:
			currentFrame = lame.jump;
			break;
		case Lame.STATE_INGROUND:
			currentFrame = lame.platformAnimation.getKeyFrame(world.lame.stateTime, false);
			break;
		default:
			currentFrame = lame.fall;
			break;
		}	
		if(world.lame.velocity.x > 0) side = 1;
		else if(world.lame.velocity.x < 0) side = -1;
	 	batch.draw(currentFrame, world.lame.position.x, world.lame.position.y,
	 			(currentFrame.getRegionWidth()/30)/2, (currentFrame.getRegionHeight()/30)/2, 
	 			(currentFrame.getRegionWidth()/30), (currentFrame.getRegionHeight()/30), side * 1, 1, 0);

	}
	
}
