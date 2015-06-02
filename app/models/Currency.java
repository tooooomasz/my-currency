package models;

/**
 * Created by Tomasz on 6/1/2015.
 */
public class Currency {
   // public int id;
    public String code;
   // public String name;

    public Currency(String code) {
     //   this.id = id;
        this.code = code;
     //   this.name = name;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                '}';
    }
}
