package models;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by Tomasz on 6/1/2015.
 */
public class DBMockTest {

    @Test
    public void DBMockShouldGenerateHistory() {
        // given
        String name = "USDCHF";
        // when
        RateHistory rateHistory = DBMock.getRateHistory(name);
        // then
        System.out.println(rateHistory);
        Assert.assertTrue(rateHistory.getLabels().size() == 100);
    }
}
