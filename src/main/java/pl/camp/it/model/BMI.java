package pl.camp.it.model;

import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.exception.NegativeIntegerValueException;
import pl.camp.it.exception.NegativeDoubleValueException;
import pl.camp.it.exception.NegativeSexCharException;
import pl.camp.it.logic.Service;

import java.text.NumberFormat;
import java.text.ParseException;

public class BMI {

    private String sex;
    private String mass;
    private String height;
    private String age;
    private String id;
    private String bmi;

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) throws NegativeDoubleValueException{
        String regex = "^[0-9]+[.0-9]{1,3}$";
        if (bmi.matches(regex)) {
            this.bmi = bmi;
        } else{
            throw new NegativeDoubleValueException(bmi);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws NegativeIntegerValueException{
        String regex = "^[0-9]+$";
        if (id.matches(regex) && Integer.parseInt(id)>0) {
            this.id = id;
        } else{
            throw new NegativeIntegerValueException(id);
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) throws NegativeSexCharException {
        String regex = "^[K]{1}$|^[M]{1}$";
        if (sex.matches(regex)) {
            this.sex = sex;
        } else{
            throw new NegativeSexCharException(sex);
        }
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) throws NegativeDoubleValueException {
        String regex = "^[0-9]+$|^[0-9]+[.0-9]{1,3}$";
        if (mass.matches(regex) && Double.parseDouble(mass)>0 && Double.parseDouble(mass)<500) {
            this.mass = mass;
        } else{
            throw new NegativeDoubleValueException(mass);
        }
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) throws NegativeDoubleValueException {
        String regex = "^[0-9]+$|^[0-9]+[.0-9]{1,3}$";
        if (height.matches(regex) && Double.parseDouble(height)>19 && Double.parseDouble(height)<300) {
            this.height = height;
        }else {
            throw new NegativeDoubleValueException(height);
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) throws NegativeIntegerValueException {
        String regex = "^[0-9]+$";
        if (age.matches(regex) && Integer.parseInt(age)>0 && Integer.parseInt(age) <150){
            this.age = age;
        } else{
            throw new NegativeIntegerValueException(age);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.id).append(";").append(this.sex).append(";").append(this.mass).append(";").
                append(this.height).append(";").append(age).append(";").append(this.bmi);

        return stringBuilder.toString();
    }

}
