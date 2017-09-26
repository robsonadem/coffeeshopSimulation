import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**  ExperimentController performs various experiments and outputs to output.csv file
 * 
 * @author Robson
 *
 */
public class ExperimentController
{

    /**
     * Constructor for objects of class ExperimentController
     */

    public ExperimentController(double lambda){
    }

    public static void main(String [] args)throws FileNotFoundException
    {
        double p=2;
        double c=300;
        double r=(double)0.3;
        PrintWriter pw = new PrintWriter(new File("output.csv"));

        double[] lambdaValues={(double)0.2,(double)0.3,(double)0.5,(double)1.0,(double)1.5,(double)2.0};
        double dailyNetProfit=0;
        double avgDailyNetProfit=0;
        int i=0;
        while(i<lambdaValues.length){
            for(int s=1;s<11;s++){
                //for a given data calculate the average taking five simulations
                int j=0;
                while(j<5){
                    Simulation sim=new Simulation(s,p,c,lambdaValues[i],r);
                    sim.run();
                    dailyNetProfit+=sim.getDailyNetProfit();
                    j++;
                }
                avgDailyNetProfit=dailyNetProfit/5;
                StringBuilder sb = new StringBuilder();
                //outputting to cols and rows in excel file
                sb.append(lambdaValues[i]);
                sb.append(',');
                sb.append(s);
                sb.append(',');
                sb.append(avgDailyNetProfit);
                sb.append('\n');
                pw.write(sb.toString());
                dailyNetProfit=0;
            }
            dailyNetProfit=0;
            i++;
        }
        pw.close();
        System.out.println("---------Experiments have been processed. Find final data at output.csv ----------");

    }
}
