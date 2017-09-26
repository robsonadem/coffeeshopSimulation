import java.util.*;
import java.util.ArrayList;
/**
 * CustomerList is a container class of the list of the customers of the Coffee Shop
 * 
 * @author Robson Adem 
 * @version Final 10-03-17
 */
public class CustomerList 
{
    private ArrayList<Customer> customerList;
    private int customerNum;
    /**
     * Constructor for objects of class CustomerList
     */
    public CustomerList()
    {
       customerList=new ArrayList<Customer>();
       //lambda=arrivalRate;
    }

    /**
     * A method to add customer to the customer list
     * @param       Customer c 
     */
    public void addCustomer(Customer c)
    {
        customerList.add(c);
    }
    
    /**
     * A method to return the size of the list
     * @return     int size
     */
    public int size()
    {
        return customerList.size();
    }
    
    /**
     * A method to get customer to the customer list
     * @param        int index 
     * @return      Customer c 
     */
    public Customer getCustomer(int index)
    {
       return  customerList.get(index);
    }
}
