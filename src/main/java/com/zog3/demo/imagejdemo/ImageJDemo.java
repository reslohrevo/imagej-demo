package com.zog3.demo.imagejdemo;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class ImageJDemo {

	public static void main(String[] args) {
		System.out.println("Starting application");

		ArgumentParser parse = ArgumentParsers.newFor("ImageJDemo").build();
		parse.addArgument("filename") //
				.action(Arguments.store());
		parse.addArgument("--crop") //
				.action(Arguments.store());
		parse.addArgument("--resize") //
				.action(Arguments.storeTrue());
		parse.addArgument("--scale") //
				.action(Arguments.store()) //
				.type(Double.class);
		parse.addArgument("destination") //
				.action(Arguments.store());

		try {
			Namespace argument = parse.parseArgs(args);
			System.out.println(argument.getDouble("scale"));
			ImageOperator image = new ImageOperator(argument.getString("filename"));
			
			if (argument.get("crop") != null) {
				image.crop(argument.getString("crop"));
			}
			if (argument.getDouble("scale") != null) {
				image.scaleCanvas(argument.getDouble("scale"));
			}
			if (argument.getBoolean("resize")) {
				image.resize();
			}

			System.out.println("Displaying image");
			image.show();

		} catch (IllegalArgumentException e) {
			System.out.println("Invalid geometry");
			System.exit(1);
		} catch (ArgumentParserException e) {
			parse.handleError(e);
		}

	}

}
