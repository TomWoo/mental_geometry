package mental_geometry;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;
	
	public ArrayList getFrames() {
		return frames;
	}

	public void setFrames(ArrayList frames) {
		this.frames = frames;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public long getAnimTime() {
		return animTime;
	}

	public void setAnimTime(long animTime) {
		this.animTime = animTime;
	}

	public long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void addFrame(BufferedImage image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}
			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;
			}
		}
	}
	
	public synchronized BufferedImage getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}
	
	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}
	
	private class AnimFrame {
		BufferedImage image;
		long endTime;
		public AnimFrame(BufferedImage image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
	
}
