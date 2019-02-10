package reusableMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	public Properties prop = new Properties();

	public Properties loadPropertiesFile(String fileName) {
		try {
			prop.load(new FileInputStream("properties/"+fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
