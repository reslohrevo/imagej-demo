package com.zog3.demo.imagejdemo;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageOperator {

	private ImagePlus imp;
	private ImageProcessor ip;

	public ImageOperator(String path) {
		imp = IJ.openImage(path);
		ip = imp.getProcessor();
	}

	public void crop(float xFactor, float yFactor, int xOrigin, int yOrigin) {

		try {

			ip.setRoi(xOrigin, yOrigin, (int) (imp.getWidth() * xFactor), (int) (imp.getHeight() * yFactor));
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

	public void scaleCanvas(float xFactor, float yFactor) {

		ip.scale(xFactor, yFactor);
		imp.setProcessor(ip);

	}

	public void resize(int xSize, int ySize) {

		ip = ip.resize(xSize, ySize);
		imp.setProcessor(ip);

	}

	public void resize(int xSize, int ySize, Boolean aa) {

		ip = ip.resize(xSize, ySize, aa);
		imp.setProcessor(ip);

	}

	public void saveAs(String path) {

		IJ.saveAs(imp, "jpg", path);

	}
	
	public void show() {
		imp.show();
	}

}
