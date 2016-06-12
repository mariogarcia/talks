package generators;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static properties.SimpleProperty.SUM;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class SumCheckCodeTest {

    @Property public void commutative(Integer x, Integer y) {
        assertEquals(SUM(x, y), SUM(y, x));
    }

    @Property public void associative(Integer x, Integer y, Integer z) {
        assertEquals(SUM(x, SUM(y, z)), SUM(SUM(x,y), z));
    }

    @Property public void additive(Integer x) {
        assertEquals(SUM(x,0), x);
    }

    @Property public void distributive(Integer x, Integer y, Integer z) {
        assertTrue(SUM(x, y) * z == SUM(x * z, y * z));
    }
}
