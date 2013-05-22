package com.pratik.clouds.heroku;
import java.io.File;
import com.pratik.Tree.Settings;

import com.pratik.FileOperations.FileOperation;
import com.pratik.drawGraph.*;

/**
 * @author Pratik
 *
 */
public class Heroku extends AbstractCloud {
	

	
	@Override
	public
	String createAddress(String number) {
		// TODO Auto-generated method stub
		String address = "http://"+"syncws"+number+this.cloudAddress();
		return address;
		
	}

	public Heroku() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	String createCloudFoundryAddressForDataWS(String vertex) {
		// TODO Auto-generated method stub
		String address = "DATAWS" + vertex + cloudAddress();
		return address;
		
	}

	@Override
	public String cloudAddress() {
		// TODO Auto-generated method stub
		String cloudAddress="heroku.com";
		return cloudAddress;
	}

	@Override
	public String cloudName() {
		// TODO Auto-generated method stub
		return "Heroku";
	}

	@Override
	public void createAndDeploySingleDataNode() {
		// TODO Auto-generated method stub
		
	}

/*
 * (non-Javadoc)
 * @see com.pratik.clouds.heroku.AbstractCloud#checkAndCreateCloudOutputFolder(java.lang.String)
 * append cloud name at the end of ouptut folder and then create it
 * 
 */
	@Override
	public void checkAndCreateCloudOutputFolder(String cloudOutputFolder) {
		
		
		// TODO Auto-generated method stub
		String herokuCloudFolder = cloudOutputFolder+File.separator + this.cloudName();
		FileOperation.checkAndCreateFolder(herokuCloudFolder);
		
		
		
		
	}

	@Override
	public void createSingleDataNode(String number) {
		// TODO Auto-generated method stub
		String cloudFolder = Settings.cloudOutput+this.cloudName();
		String pathToFolder = cloudFolder + File.separator + "DATAWS"+number;
		FileOperation.createDataNode(pathToFolder);
		
		
	}

}
