package Connection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ActionTest {

    @Test
    public void get_avg_mark() {
        int mark_1 = 7;
        int mark_2 = 9;
        int mark_3 = 10;
        int mark_4 = 6;
        ArrayList marks = new ArrayList();
        marks.add(mark_1);
        marks.add(mark_2);
        marks.add(mark_3);
        marks.add(mark_4);

        double expected = 8.0;
        double actual = Action.get_avg_mark(marks);

        Assert.assertEquals(expected, actual, 0);
    }
}