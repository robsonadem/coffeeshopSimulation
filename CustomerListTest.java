

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerListTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerListTest
{
    /**
     * Default constructor for test class CustomerListTest
     */
    public CustomerListTest()
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
    public void testAddCustomer()
    {
        Customer customer1 = new Customer(1, 5);
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(customer1);
        Customer customer2 = customerList.getCustomer(0);
        assertEquals(5.0, customer2.getArrivalTime(), 0.1);
        assertEquals(1, customer2.getNum());
    }

    @Test
    public void testSize()
    {
        Customer customer1 = new Customer(3, 9);
        CustomerList customerList = new CustomerList();
        assertEquals(0, customerList.size());
        customerList.addCustomer(customer1);
        assertEquals(1, customerList.size());
    }

    @Test
    public void testGetCustomer()
    {
        Customer customer1 = new Customer(1, 5);
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(customer1);
        Customer customer2 = customerList.getCustomer(0);
        assertEquals(5.0, customer2.getArrivalTime(), 0.1);
    }
}



