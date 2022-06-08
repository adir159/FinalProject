package utilities;

import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class manageDDT extends commonOps{

    @DataProvider(name = "data-provider-users")
        public Object[][] getDataObject() throws ParserConfigurationException, IOException, SAXException {
            return getDataFromCSV(getData("DDTFile"));
        }

    public static List<String> readCSV(String csvFile)
    {
        File file = new File(csvFile);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Object[][] getDataFromCSV(String filePath)
    {
        Object[][] data = new Object[3][2];
        List<String> csvData = readCSV(filePath);
        for (int i = 0;i < csvData.size(); i++){
           data[i][0] = csvData.get(i).split(",")[0];
            data[i][1] = csvData.get(i).split(",")[1];
        }
        return data;
    }
}

