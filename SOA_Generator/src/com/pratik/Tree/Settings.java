/**
 * 
 */
package com.pratik.Tree;

import java.io.File;

/**
 * @author Pratik
 * This folder contains all the settings needed initially for the project
 * such as setting up the output folder
 * setting up the tomcat directory etc.
 * 
 *
 */
public class Settings {
	
	public static String path = "C:\\Users\\Pratik\\workspace\\SOA_Generator\\Output";
	public static String output = path + File.separator;
	public static String cloud = output + "Clouds";
	
	// The clouds folder will store all the 
	public static String cloudOutput = output + "Clouds"+File.separator;
	
	public static String tomcatDir = "E:\\Tomcat\\apache-tomcat-7.0.28-windows-x86\\apache-tomcat-7.0.28\\webapps";

}
