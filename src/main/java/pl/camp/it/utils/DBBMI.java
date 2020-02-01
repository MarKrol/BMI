package pl.camp.it.utils;

import org.springframework.stereotype.Component;
import pl.camp.it.exception.NegativeDoubleValueException;
import pl.camp.it.exception.NegativeIntegerValueException;
import pl.camp.it.exception.NegativeSexCharException;
import pl.camp.it.model.BMI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBBMI {
    private String dbFilePath = ".\\src\\main\\resources\\dbBMI.txt";

    public void saveToFile(List<BMI> bmiList){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dbFilePath,true))){
            bufferedWriter.write(bmiList.get(bmiList.size()-1).toString());
            bufferedWriter.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<BMI> loadDataFromFile(){

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(dbFilePath))){
            List<BMI> result = new ArrayList<>();
            BMI temp;
            String line;
            while((line=bufferedReader.readLine())!=null){
                if ((temp = convertReadLineToObject(line))!=null) {
                    result.add(temp);
                }
            }
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private BMI convertReadLineToObject(String line){
        String[] data=line.split(";");

        BMI bmiUser = new BMI();
        try {
            bmiUser.setId(data[0]);
            bmiUser.setSex(data[1]);
            bmiUser.setMass(data[2]);
            bmiUser.setHeight(data[3]);
            bmiUser.setAge(data[4]);
            bmiUser.setBmi(data[5]);
            return bmiUser;

        } catch(NegativeSexCharException | NegativeIntegerValueException | NegativeDoubleValueException e){
            e.printStackTrace();
            return null;
        }
    }
}
