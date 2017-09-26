
/**
 * Customer is a class representing each customer of the Coffee Shop
 * 
 * @author Robson Adem 
 * @version Final 10-03-17
 */
public class Customer
{
    private double  arrivalTime;
    private double  departureTime;
    private double  waitTime;
    private int num;
    private boolean isOverFlow;
    private double serviceTime;
    /**
     * Constructor for objects of class Customer
     */
    public Customer(int num, double arrivalTime)
    {
       this.arrivalTime=arrivalTime;
       this.num=num;
       this.isOverFlow=false;
    }
    
    /**
     * A method to return the arrivalTime of a customer
     * @return     double arrivalTime
     */
    public double getArrivalTime()
    {
        return this.arrivalTime;
    }
    
   
    /**
     * A method to return the customer number of a customer
     * @return     int customer number
     */
    public int getNum()
    {
        return this.num;
    }
    
     /**
     * A method to return the departure Time of a customer
     * @return     double departureTime
     */
    public double getDepartureTime()
    {
        return this.departureTime;
    }
    
    /**
     * A method to set the departure Time of a customer
     * @param     double departureTime
     */
    public void setDepartureTime(double time)
    {
        this.departureTime=time;
    }
    
    /**
     * A method to return the wait Time of a customer
     * @return     double wait Time
     */
    public double getWaitTime()
    {
        return departureTime-arrivalTime;
    }
    
    /**
     * A method to return if the customer is turned away
     * @return     boolean overFlow
     */
    public boolean isOverflow()
    {
        return isOverFlow;
    }
    
    /**
     * A method to set the customer to overflow
     * @return     boolean overFlow
     **/
    public void setOverflow()
    {
        isOverFlow=true;
    }
    
    /**
     * A method to set the service Time of a customer
     * @param     double time
     */
    public void setServiceTime(double time)
    {
        serviceTime=time;
    }

}
