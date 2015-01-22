package com.ispira.lamejumper;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LameAnimation {
	private static final int COLS = 7;
	@SuppressWarnings("unused")
	private static final int ROWS = 2;
	
	Animation walkAnimation;
	Animation platformAnimation;
	TextureRegion[][] frames;
	TextureRegion[] walkFrames;
	TextureRegion standby;
	TextureRegion jump;
	TextureRegion fall;
	TextureRegion[] platformFrames;
	
	public LameAnimation(){
		frames = TextureRegion.split(Assets.character, 72, 97);
		walkFrames = new TextureRegion[6];
		for(int i=1; i < COLS; i++){
			walkFrames[i-1] = frames[0][i];
		}
		walkAnimation = new Animation(0.2f, walkFrames);
		standby = frames[0][0];
		jump = frames[1][0];
		fall = frames[1][1];
		platformFrames = new TextureRegion[2];
		platformFrames[0] = new TextureRegion(Assets.char_inplatform, 0, 0, 69, 71);
		platformFrames[1] = standby;
		platformAnimation = new Animation(1f, platformFrames);
	}
}
