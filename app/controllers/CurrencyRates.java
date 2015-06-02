package controllers;

import models.Currency;
import models.DBMock;
import models.RateHistory;
import play.*;
import play.mvc.*;
import scala.collection.JavaConverters;
import views.html.allRates;
import views.html.index;
import views.html.rate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Tomasz on 6/1/2015.
 */
public class CurrencyRates extends Controller {

    public Result details(String name) {
        RateHistory rh = DBMock.getRateHistory(name);
        int size = rh.getValues().size();
        int daysBack = 10;
        List<Double> values = rh.getValues().subList(size-daysBack, size);
        List<String> labels = rh.getLabels().subList(size-daysBack, size);
        List<Double> changes = new ArrayList<Double>();

        List<Double> valsChan = rh.getValues().subList(size-daysBack-1, size);
        double change = 0;
        for (int i=0; i<valsChan.size(); i++) {
            if (i==0) {
                change = valsChan.get(i);
            } else {
                change = 100 * (valsChan.get(i) - valsChan.get(i-1)) / valsChan.get(i-1);
                changes.add(change);
            }
        }

        return ok(rate.render(name, values, labels, changes));
    }

    public Result all() {
        List<String> names = new ArrayList<String>();
        List<Double> values = new ArrayList<Double>();
        List<Double> changes = new ArrayList<Double>();

        Set<String> rates = DBMock.getAllRateNames();

        for (String rate : rates) {
            RateHistory rh = DBMock.getRateHistory(rate);
            names.add(rh.getName());
            double last = rh.getValues().get(rh.getValues().size()-1);
            values.add(rh.getValues().get(rh.getValues().size()-1));
            double lastButOne = rh.getValues().get(rh.getValues().size()-2);
            double change = 100 * (last - lastButOne) / lastButOne;
            changes.add(change);
        }

        return ok(allRates.render(names, values, changes));
    }

    public Result my() {
        return TODO;
    }
}
