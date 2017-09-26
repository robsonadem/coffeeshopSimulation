

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetNum()
    {
        Customer customer2 = new Customer(1, 2);
        assertEquals(1, customer2.getNum());
    }

    @Test
    public void getArrivalTime()
    {
        Customer customer1 = new Customer(2, 9);
        assertEquals(9, customer1.getArrivalTime(),0.1);
    }

    @Test
    public void testSetDepartureTime()
    {
        Customer customer1 = new Customer(1, 10);
        customer1.setDepartureTime(90);
        assertEquals(80, customer1.getWaitTime(), 0.1);
    }

    @Test
    public void testGetWaitTime()
    {
        Customer customer1 = new Customer(1, (float) 3.3);
        customer1.setDepartureTime((float)8.9);
        assertEquals(5.59999942779541, customer1.getWaitTime(), 0.1);
    }

    @Test
    public void testSetOverflow()
    {
        Customer customer1 = new Customer(1, 4.6);
        customer1.setOverflow();
        assertEquals(true, customer1.isOverflow());
    }

    @Test
    public void testIsOverflow()
    {
        Customer customer1 = new Customer(4, 4.5);
        assertEquals(false, customer1.isOverflow());
        customer1.setOverflow();
        assertEquals(true, customer1.isOverflow());
    }

    @Test
    public void testGetDepartureTime()
    {
        Customer customer2 = new Customer(2, 5);
        customer2.setDepartureTime(12);
        assertEquals(12.0, customer2.getDepartureTime(), 0.1);
    }

    @Test
    public void testSetServiceTime()
    {
        Customer customer1 = new Customer(1, 1);
        customer1.setServiceTime(8);
        customer1.setDepartureTime(90);
        assertEquals(81.0, customer1.getWaitTime(), 0.1);
    }
}









