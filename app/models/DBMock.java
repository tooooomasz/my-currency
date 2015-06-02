package models;

import com.google.common.collect.Iterables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Tomasz on 6/1/2015.
 */
public class DBMock {

    private static Map<String, RateHistory> db;

    static {
        int daysBack = 100;
        db = new HashMap<String, RateHistory>();
        List<String> rates = new ArrayList<String>();
        rates.add("USDCHF");
        rates.add("PLNUSD");
        rates.add("CHFPLN");
        for (String rate : rates) {
            db.put(rate, generateRateHistory(rate, daysBack));
        }
    }

    private static RateHistory generateRateHistory(String name, int daysBack) {
        List<Double> values = new ArrayList<Double>();
        List<String> dates = new ArrayList<String>();

        Random generator = new Random();
        double init = generator.nextInt(7) + Math.random();

        Date end = new Date();
        Date start = new Date(end.getTime() - daysToMilliseconds(daysBack));

        DateFormat df = new SimpleDateFormat("MM.dd.yyyy");

        for (int i=0; i<daysBack; i++) {
            Date date = new Date(start.getTime() + daysToMilliseconds(i));
            if (date.getTime() >= end.getTime()) {
                break;
            }
            double diff = Math.random() * 0.1 - 0.05;
            if (i == 0) {
                values.add(init);
            } else {
                if (values.get(i-1) + diff > 0) {
                    values.add(values.get(i-1) + diff);
                } else {
                    values.add(values.get(i-1) - diff);
                }
            }
            dates.add(df.format(date));
        }

        return new RateHistory(name, values, dates);
    }

    public static RateHistory getRateHistory(String name) {
        return db.get(name);
    }

    public static Set<String> getAllRateNames() {
        return db.keySet();
    }

//    public static RateHistory getRateHistory(Currency base, Currency target, Date start, Date end) {
//        RateHistory rateHistory = new RateHistory();
//        rateHistory.base = base;
//        rateHistory.target = target;
//        rateHistory.values = new ArrayList<Rate>();
//        rateHistory.dates = new ArrayList<String>();
//        rateHistory.vals = new ArrayList<Double>();
//        Random generator = new Random();
//        DateFormat df = new SimpleDateFormat("MM.dd.yyyy");
//        double init = generator.nextInt(7) + Math.random();
//        rateHistory.values.add(new Rate(new Date(start.getTime()), init));
//        rateHistory.dates.add(df.format(new Date(start.getTime())));
//        rateHistory.vals.add(init);
//        for (int i = 1; true; i++) {
//            Date date = new Date(start.getTime() + daysToMilliseconds(i));
//            if (date.getTime() >= end.getTime()) {
//                break;
//            }
//            double diff = Math.random() * 0.1 - 0.05;
//            rateHistory.values.add(new Rate(date, rateHistory.values.get(i-1).value + diff));
//            rateHistory.dates.add(df.format(date));
//            rateHistory.vals.add(rateHistory.vals.get(i-1) + diff);
//        }
//        return rateHistory;
//    }
//
//    public static List<RateHistory> getAllRates() {
//        List<RateHistory> allRates = new ArrayList<RateHistory>();
//        List<Currency> allCurrencies = getAllCurrencies();
//        Currency base = allCurrencies.get(0);
//        for (Currency c : allCurrencies) {
//            if (c.code.equals(base.code)) {
//                continue;
//            } else {
//                Date end = new Date();
//                Date start = new Date(end.getTime() - daysToMilliseconds(14));
//                RateHistory rh = getRateHistory(base, c, start, end);
//                allRates.add(rh);
//            }
//        }
//        return allRates;
//    }
//
//    public static List<Currency> getAllCurrencies() {
//        List<Currency> allCurrencies = new ArrayList<Currency>();
//        allCurrencies.add(new Currency("USD"));
//        allCurrencies.add(new Currency("EUR"));
//        allCurrencies.add(new Currency("CHF"));
//        allCurrencies.add(new Currency("GBP"));
//        allCurrencies.add(new Currency("PLN"));
//        return allCurrencies;
//    }

    public static long daysToMilliseconds(int days) {
        return days * 86400000L;
    }
}
