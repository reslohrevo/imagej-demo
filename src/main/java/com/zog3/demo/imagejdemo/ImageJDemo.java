package com.zog3.demo.imagejdemo;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ImageJDemo {

	public static void main(String[] args) {
		System.out.println("Starting application");
		String destFilename = "/tmp/imagej/output.jpg";
		String imagePath = "src/main/resources/image.jpg";
		String crop = "101x102+103+104";
		float widthFactor = 0.75f;
		float heightFactor = 0.75f;
		int originX = 1;
		int originY = 1;

		ArgumentParser parse = ArgumentParsers.newFor("ImageJDemo").build();
		parse.addArgument("filename") //
				.action(Arguments.store());
		parse.addArgument("--crop") //
				.action(Arguments.store());
		parse.addArgument("--resize") //
				.action(Arguments.storeTrue());
		parse.addArgument("--scale") //
				.action(Arguments.store()) //
				.type(Double.class)
				.nargs(2);
		parse.addArgument("destination") //
				.action(Arguments.store());

		Namespace argument = null;

		try {
			argument = parse.parseArgs(args);
		} catch (ArgumentParserException e) {
			parse.handleError(e);
		}

		ImageOperator image = new ImageOperator(argument.getString("filename"));

		try {
			if (argument.get("crop") != null) {
				image.crop(argument.getString("crop"));
			}
			if (!argument.getList("scale").isEmpty()) {
				image.scaleCanvas((Double) argument.getList("scale").get(0), (Double) argument.getList("scale").get(1));
			}
			if (argument.getBoolean("resize")) {
				image.resize();
			}

			System.out.println("Displaying image");
			image.show();

		} catch (IllegalArgumentException e) {
			System.out.println("Invalid geometry");
			System.exit(1);
		}

	}

}
