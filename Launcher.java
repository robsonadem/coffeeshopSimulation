
/**
 * Write a description of class Launcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Launcher
{
     public static void main(String [] args){
        
        int s = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        double c=Double.parseDouble(args[2]);
        double lambda = Double.parseDouble(args[3]);
        double r = Double.parseDouble(args[4]);
        Simulation sim=new Simulation(s,p,c,lambda,r);
        sim.run();

    }
}
