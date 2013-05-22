package com.pratik.FileOperations;

import java.io.File;
import java.io.IOException;

import com.sugi.util.Helper;

public class FileOperation {
	
	public static void checkAndCreateFolder(String path){
		
		File outdir = new File(path);
		boolean dirExists = outdir.exists();
		if(!dirExists)
			outdir.mkdir();
		
	}
	
	public static void createDataNode(String outdir)
	{
		
		
		File srcFolder = new File("sqlrest");
    	File destFolder = new File(outdir);
 
    	//make sure source exists
    	if(!srcFolder.exists()){
 
           System.out.println("Directory does not exist.");
           //just exit
           System.exit(0);
 
        }else{
 
           try{
        	Helper.copyFolder(srcFolder,destFolder);
           }catch(IOException e){
        	e.printStackTrace();
        	//error, just exit
                System.exit(0);
           }
        }
	}

}
