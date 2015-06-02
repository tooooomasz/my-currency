package models;

import java.util.Date;

/**
 * Created by Tomasz on 6/1/2015.
 */
public class Rate {

    public Date date;
    public double value;

    public Rate(Date date, double value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
