package pl.camp.it.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.exception.NegativeDoubleValueException;
import pl.camp.it.exception.NegativeIntegerValueException;
import pl.camp.it.exception.NegativeSexCharException;
import pl.camp.it.model.BMI;
import pl.camp.it.utils.DBBMI;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {
    @Autowired
    DBBMI dbbmi;

    public List<BMI> bmiList = new ArrayList<>();
    public String incorrectInfo;

    public BMI findElement(String id){
        BMI user=new BMI();
        user = null;
        bmiList = dbbmi.loadDataFromFile();
        for(BMI temp: bmiList){
            if (temp.getId().equals(id)){
                user=temp;
                break;
            }
        }
        if (user==null){
            if (bmiList.isEmpty()){
                incorrectInfo = "Baza danych jest pusta!!!";
            }else {
                incorrectInfo = "Nie znaleziono obiektu w bazie o podanym ID!!!";
            }
        }
        return user;
     }

    public boolean correctData(String sex, String mass, String height, String age, int flag){
        try{
            bmiList =dbbmi.loadDataFromFile();
            BMI user = new BMI();

            user.setId(id());
            user.setSex(sex);
            user.setMass(mass);
            user.setHeight(height);
            user.setAge(age);
            user.setBmi(parseNumber(calculateBMI(mass, height)));

            bmiList.add(user);
             if (flag==1) {
                 dbbmi.saveToFile(bmiList);
             }
            return true;

        }catch(NegativeSexCharException |NegativeIntegerValueException | NegativeDoubleValueException e){
            incorrectInfo=e.toString();
            return false;
        }
    }

    public String calculateBMI(String mass, String height){
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        return String.valueOf(df.format(Double.parseDouble(mass)/
                                                ((Double.parseDouble(height)/100)*(Double.parseDouble(height)/100))));
    }

    public String parseNumber(String data){
        String wynik;
        try {
            wynik=String.valueOf(NumberFormat.getInstance().parse(data));
            return wynik;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String id(){
        int id = 1;
        for(int i=0; i<bmiList.size();i++){
            for(int k=0; k<bmiList.size();k++){
                if (bmiList.get(k).getId().equals(String.valueOf(id))){
                    ++id;
                    i=0;
                    break;
                }
            }
        }
        return String.valueOf(id);
    }

    public String discussionResult(String bmi){
        if (Double.parseDouble(bmi)<16.0){
            return "wygłodzenie!!!";
        } else{
            if (16.0<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 17.0){
                return "wychudzenie!!!";
            } else{
                if (17.0<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 18.5){
                    return "nieodwagę!!!";
                } else{
                    if (18.5<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 25.0){
                        return "wartość prawidłową. Brawo!!!";
                    } else{
                        if (25.0<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 30.0){
                            return "nadwagę!!!";
                        } else{
                            if (30.0<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 35.0){
                                return "I stopień otyłości!!!";
                            } else{
                                if (35.0<=Double.parseDouble(bmi) && Double.parseDouble(bmi) < 40.0){
                                    return "II stopień otyłości!!!";
                                } else{
                                    return "III stopień otyłości!!!";
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String incorrectInfo(){
        return incorrectInfo;
    }
}
