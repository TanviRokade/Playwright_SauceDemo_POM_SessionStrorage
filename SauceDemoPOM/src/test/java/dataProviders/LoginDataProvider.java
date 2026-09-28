package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData()
    {
        String csvFilePath = "C:\\Users\\CCST\\Desktop\\SeleniumMaterial\\loginValidData.csv";
        List<Object[]> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath)))
        {
            String line;
            boolean firstLine =true;
            while((line=br.readLine())!=null)
            {
                if(firstLine){
                    firstLine=false;
                    continue;
                }
                if(line.trim().isEmpty())
                    continue;
                String[] values = line.split(",");
                String username= values[0].trim();
                String password= values[1].trim();
                records.add(new Object[]{username,password});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records.toArray(new Object[0][0]);
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData()
    {
        String csvFilePath = "C:\\Users\\CCST\\Desktop\\SeleniumMaterial\\loginInvalidData.csv";
        List<Object[]> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath)))
        {
            String line;
            boolean firstLine =true;
            while((line=br.readLine())!=null)
            {
                if(firstLine){
                    firstLine=false;
                    continue;
                }
                if(line.trim().isEmpty())
                    continue;
                String[] values = line.split(",");
                String username= values[0].trim();
                String password= values[1].trim();
                records.add(new Object[]{username,password});
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records.toArray(new Object[0][0]);
    }

}
