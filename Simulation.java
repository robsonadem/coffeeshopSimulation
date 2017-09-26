import java.util.PriorityQueue;
import java.lang.Math;
import java.util.Random;
/**
 * A class that simulates based on event-driven simulation
 * 
 * @author Robson Adem 
 * @version Final 10-03-17
 */
public class Simulation 
{
    private int cashiers;
    private double profitPerCustomer;
    private double costOfEmployingCashier;
    private double arrivalRate;
    private double serviceRate;
    private double averageWaitingTime;
    private double dailyNetProfit;
    private PriorityQueue<Event> eventQueue;
    private CustomerList listOfCustomers;
    private double rateOverflow; 
    private Random random;
public static void main(String [] args){
        
        int s = 3;
        double p =2; 
        double c=300;
        double lambda = 2;
        double r = 0.3;
        Simulation sim=new Simulation(s,p,c,lambda,r);
        sim.run();

    }
    /**
     * Constructor for objects of class Simulation
     */
    public Simulation(int s, double p, double c,double lambda,double r )
    {
        cashiers=s;
        profitPerCustomer=p;
        costOfEmployingCashier=c;
        arrivalRate=lambda;
        serviceRate=r;
        eventQueue=new PriorityQueue<Event>();
        random= new Random();
        listOfCustomers=new CustomerList();
        listOfCustomers=generateCustomerList();
    }

    /**
     * A private event class used in constructing the priority queue of events
     * 
     * */
    private static  class Event implements Comparable<Event>
    {
        static final int ARRIVAL= 1;
        static final int DEPARTURE= 2;
        double time;
        int customer;
        int type;

        /**
         * Constructor for objects of class Events
         */
        public Event(int customer, double time, int type)
        {
            this.customer=customer;
            this.time=time;
            this.type=type;
        }

        /**
         * An example of a method - replace this comment with your own
         * 
         * @param  y   a sample parameter for a method
         * @return     the sum of x and y 
         */
        public int compareTo(Event e)
        {
            if(this.time > e.time)      return 1;
            else if(this.time < e.time) return -1;
            else                        return 0;
        }
    }

    /**
     * Runs all the simulations and prints results
     */
    public void run()
    {
        int availableCashiers=cashiers;
        double serviceTime=0;
        int  overflow=0;
        Event e=null; Event ev=null;
        PriorityQueue<Event> waitingQueue= new PriorityQueue<Event>();
        boolean waiting=false;

        for(int j=0;j<listOfCustomers.size();j++){
            int customerNum=listOfCustomers.getCustomer(j).getNum();
            double  arrivalTime=listOfCustomers.getCustomer(j).getArrivalTime();
            Event arrival= new Event(customerNum,arrivalTime,Event.ARRIVAL);
            eventQueue.add(arrival);
        }

        while(!eventQueue.isEmpty() ){
            e=eventQueue.poll();
            if(e.type==Event.ARRIVAL){

                if(availableCashiers>0){
                    //serving 
                    availableCashiers--;
                    serviceTime=nextServiceTime();
                    double departureTime=e.time+serviceTime;
                    Event departure= new Event(e.customer,departureTime,Event.DEPARTURE);
                    eventQueue.add(departure);
                    listOfCustomers.getCustomer(e.customer-1).setServiceTime(serviceTime);
                    listOfCustomers.getCustomer(e.customer-1).setDepartureTime(departureTime);
                    //System.out.println("At time "+e.time+ " Customer "+ e.customer+" is getting served by Cashier ");
                }
                else{
                    if((waitingQueue.size()<8*cashiers)) {

                        //System.out.println("At time "+e.time+  " Customer "+ e.customer + " is in the waiting line.");
                        waitingQueue.add(e);

                    }
                    else{
                        listOfCustomers.getCustomer(e.customer-1).setOverflow();
                        //System.out.println("At time "+e.time+ " Customer "+ e.customer + " has been turned away");
                        overflow++;
                    }

                }
            }
            else if (e.type==Event.DEPARTURE){
                availableCashiers++;
                listOfCustomers.getCustomer(e.customer-1).setServiceTime(serviceTime);
                //if there are customers in the waiting line, serve 
                if(!waitingQueue.isEmpty()) {
                    ev=waitingQueue.poll(); 
                    availableCashiers--;
                    serviceTime=nextServiceTime();
                    double departureTime=e.time+serviceTime;
                    Event departure= new Event(ev.customer,departureTime,Event.DEPARTURE);
                    eventQueue.add(departure);
                    listOfCustomers.getCustomer(ev.customer-1).setServiceTime(serviceTime);
                    listOfCustomers.getCustomer(ev.customer-1).setDepartureTime(departureTime);
                    //System.out.println("At time "+e.time+ " Customer "+ ev.customer+" is getting served by Cashier ");
                }
                //System.out.println("At time "+e.time+ " Customer "+ e.customer+ " has been served for "+serviceTime +" and has departed.");
            }

        }
        rateOverflow=(double)overflow/(listOfCustomers.size())*100;
        dailyNetProfit=getDailyNetProfit(overflow); 

        printResults();
    }

    /**
     * A method to print results 
     */
    private  void printResults()
    { 
        double sumWaiting=0;
        for(int j=0;j<listOfCustomers.size();j++){
            Customer c=listOfCustomers.getCustomer(j);
            if(!c.isOverflow()){
                System.out.println("Customer "+c.getNum()+" Arrived at "+c.getArrivalTime()+" Departed at "+c.getDepartureTime()+" Waited "+c.getWaitTime());
                sumWaiting+=c.getWaitTime();
            }
            else System.out.println("Customer "+c.getNum()+" Arrived at "+c.getArrivalTime()+ " OVERFLOW" );
        }
        averageWaitingTime=sumWaiting/listOfCustomers.size();
        System.out.println("*************         Final Result       ********************");
        System.out.println("* Rate of overflow= "+ rateOverflow+"%");
        System.out.println("* Average Waiting Time= "+ averageWaitingTime);
        System.out.println("* The daily Net profit= "+ dailyNetProfit);
        System.out.println("*************         Final Result       ********************");
    }
    
    /**
     * A method that returns the next service interval
     * @return     double service interval
     */
    private double nextServiceTime()
    {
        double temp=-(double)(Math.log(random.nextDouble()))/serviceRate;
        return temp;
    }

    /**
     * A method that returns the next arrival interval
     * @return     double arrival  interval
     */
    private double nextArrivalInterval()
    {
        double temp=-(double)(Math.log(random.nextDouble()))/arrivalRate;
        return temp;
    }

    /**
     * A method that gets the daily net profit of the coffee shop 
     * @return     double daily net profit 
     */
    public double getDailyNetProfit(int overflow)
    {
        return (listOfCustomers.size()-overflow)*profitPerCustomer-(cashiers*costOfEmployingCashier);
    }
    
    /**
     * A method that gets the daily net profit of the coffee shop 
     * @return     double daily net profit 
     */
    public double getDailyNetProfit()
    {
        return dailyNetProfit;
    }
    
    /**
     * A method to return a customerList with customers and their arrival time
     * @return     CustomerList
     */
    private CustomerList generateCustomerList()
    {   int number=1;
        double arrivalTime=0;
        CustomerList newList= new CustomerList();
        while(arrivalTime<960){
            arrivalTime+=nextArrivalInterval();
            Customer c=new Customer(number,arrivalTime);
            newList.addCustomer(c);
            number++;
        }
        return newList;
    }

}