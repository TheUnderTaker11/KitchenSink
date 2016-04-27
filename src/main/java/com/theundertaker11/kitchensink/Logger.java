package com.theundertaker11.kitchensink;

import org.apache.logging.log4j.Level;
import net.minecraftforge.fml.common.FMLLog;


public class Logger {
	
	/*
	 * This Class is from JoeTato's mod, I take no credit
	 * 
	 */

	public static void log(Level level, String format, Object... data) {

		FMLLog.log(level, "[@QuantumFlux] " + format, data);
	}

	public static void fatal(String format, Object... data) {
		log(Level.FATAL, format, data);
	}

	public static void error(String format, Object... data) {
		log(Level.ERROR, format, data);
	}

	public static void warning(String format, Object... data) {
		log(Level.WARN, format, data);
	}

	public static void info(String format, Object... data) {
		log(Level.INFO, format, data);
	}

	public static void debug(String format, Object... data) {
		log(Level.DEBUG, format, data);
	}

	public static void trace(String format, Object... data) {
		log(Level.TRACE, format, data);
	}
}
