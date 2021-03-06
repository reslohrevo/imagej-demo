package com.zog3.demo.imagejdemo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageOperator {

	private ImagePlus imp;
	private ImageProcessor ip;
	private static final int RESIZE_WIDTH = 1000;

	public ImageOperator(String path) {
		if (Files.notExists(Paths.get(path))) {
			throw new IllegalArgumentException("Bad file path: File " + path + " not found");
		}
		imp = IJ.openImage(path);
		ip = imp.getProcessor();
	}
	
	public ImageOperator() {
		// No argument constructor for unit testing.
	}

	public void crop(String rawInputString) {

		Pattern r = Pattern.compile("^(\\d+)(?i)x(?-i)(\\d+)\\+(\\d+)\\+(\\d+)$");
		Matcher m = r.matcher(rawInputString);
		int x = 0, y = 0, width = 0, height = 0;
		if (m.find()) {
			x = Integer.parseInt(m.group(3));
			y = Integer.parseInt(m.group(4));
			width = Integer.parseInt(m.group(1));
			height = Integer.parseInt(m.group(2));
		} else {
			throw new IllegalArgumentException("Illegal crop string: " + rawInputString);
		}

		try {
			ip.setRoi(x, y, width, height);
			ip = ip.crop();
			imp.setProcessor(ip);
			ip.resetRoi();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}

	public int getHeight() {
		return imp.getHeight();
	}

	public int getWidth() {
		return imp.getWidth();
	}

	public void scaleCanvas(double factor) {
		ip.scale(factor, factor);
		imp.setProcessor(ip);
	}

	public void resize() {
		ip = ip.resize(RESIZE_WIDTH, calculateHeight(getWidth(), getHeight()));
		imp.setProcessor(ip);
	}

	private int calculateHeight(int width, int height) {
		float scaleFactor = RESIZE_WIDTH / (float) width;
		int resizeHeight = (int) (scaleFactor * height);
		return resizeHeight;
	}

	public void saveAs(String path) {
		IJ.saveAs(imp, "jpg", path);
	}

	public void show() {
		imp.show();
	}

}
