package com.zog3.demo.imagejdemo;

public class ImageJDemo {

	public static void main(String[] args) {
		System.out.println("Starting application");
		String destFilename = "tmp/output.jpg";
		String imagePath = "src/main/resources/image.jpg";
		float widthFactor = 0.75f;
		float heightFactor = 0.75f;
		int originX = 1;
		int originY = 1;
		int resizeX = 400;
		int resizeY = 400;
		
		ImageOperator image = new ImageOperator(imagePath);
		
		try {
			System.out.println("Cropping");
			image.crop(widthFactor, heightFactor, originX, originY);
			System.out.println("Resizing");
			image.resize(resizeX, resizeY, true);
			System.out.println("Saving");
			image.saveAs(destFilename);
			System.out.println("Displaying image");
			image.show();
			
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid geometry");
			System.exit(1);
		}
		

	}

}
