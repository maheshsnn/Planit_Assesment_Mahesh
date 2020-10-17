package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Propertiesfile {

	Properties prop;
	
	/*
	 *  class to read properties file
	 */

	public Propertiesfile() throws Exception {
		try {
			File config = new File("G:\\Photon_Automation\\Planit_Assesment_Mahesh\\Inputs\\Input");
			FileInputStream fis = new FileInputStream(config);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found please check in Propertiesfile.class");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */

	public String Quantity() {
		String qua = prop.getProperty("Quantity");
		return qua;
	}

	public String Country() {
		String country = prop.getProperty("Country");
		return country;
	}

	public String City() {
		String city = prop.getProperty("City");
		return city;
	}

	public String Address1() {
		String Address = prop.getProperty("Address1");
		return Address;
	}

	public String Zip() {
		String Zip = prop.getProperty("Zip");
		return Zip;
	}

	public String Phone() {
		String phn = prop.getProperty("Phone");
		return phn;
	}
}
