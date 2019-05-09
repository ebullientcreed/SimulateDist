import java.io.*;
import java.util.*;

class simulateDist{

	static List<Double> list=new ArrayList<Double>();

	//Random number generator, setting the seed to a particular value
	static Random gen=new Random();;
	static long seed=14329; 
	
	//Function to handle bernoulli distribution, binary outcome(True (1) or false(0))
	public static void bernoulli(float p,int n) {  
		gen.setSeed(seed);
		for(int i=1;i<=n;i++){
	 		double u=rndGenerator();
	 		System.out.println(u);
	 		if(u<p){System.out.println(1);}
	 		else{ System.out.println(0);}
	 	}
	} 

	//Binomial Distribution: number of successes in a sequence of bernoulli trials
	public static void binomial(float p,int n,int x) {  
	 	int succ=0;
	 	gen.setSeed(seed);
	 	for(int i=1;i<=n;i++){
	 		int temp=x;
	 		while(temp>=0){
		 		double u=rndGenerator();	 		
		 		if(u<p){succ++;}
		 		temp--;
	 		}
	 		System.out.println("Binomial Successes:"+succ);
	 		succ=0;
	 	}	 	
	 	
	}
	
	//Geometric distribution: Number of trials needed to achieve the first success in a sequence of bernoulli trials
	public static void geometric(float p,int n) {  
	 	
	 	gen.setSeed(seed);
	 	for(int i=1;i<=n;i++){
	 		double u=rndGenerator();
	 		System.out.println(u);
	 		int temp=1;
	 		while(u>p){
	 			temp+=1;
	 			u=rndGenerator();
	 			System.out.println(u);
	 		}	 		
	 		System.out.println("Geometric First success at:"+temp);	 		
	 	}
	 	
	 	
	}  

	//Negative binomial :Number of trials needed to obtain k success in a sequence of bernoulli trials
	public static void neg_binomial(float p,int n,int k) {  	 	
	 	gen.setSeed(seed);
	 	
	 	for(int i=1;i<=n;i++){
	 		double u=rndGenerator();
	 		int x=0;
	 		int succ=0;
	 		while(succ!=k){
	 			x=x+1;
	 			if(u<p){		 			
		 			succ++;					
				}
	 			u=rndGenerator();
	 			
	 		}
	 		System.out.println("Neg Binomial(Trials needed to get k Successes): "+x);
	 	}
	 			 	
	 
	}  

	//Poisson: Number of rare events occuring within a period of time
	public static void poisson(int n,int l) { 
	 	
	 	gen.setSeed(seed);
	 	double x = Math.exp(-l);
	 	for(int k=1;k<=n;k++){
	 		int i=0;
	 		double z= 1.0;
	 		do{
                i++;
                double u=rndGenerator();
                z=z*u;			
	 			System.out.println(z);              
            }while(z>x);
	 		System.out.println("Poisson :"+i--+"\n");
	 	}	
	 	
	} 

	//Arbitrary discrete distribution: Arbritrary discrete random variable 
	//X takes values x0,x1,.. with probabilities p0,p1..
	public static void arb_disc(int n) {
		gen.setSeed(seed);
	 	for(int k=1;k<=n;k++){
	 		double u=rndGenerator();
	 		int x=0;
	 		int s=0;
	 		while(u>s){
	 			s+=list.get(x);
	 			x++;
	 		}
	 		System.out.println("Arb Discrete"+k+":"+x--);
	 	}
	} 

	//Uniform distribution 
	public static void uniform(int n,double a,double b) {
		gen.setSeed(seed);
	 	for(int k=1;k<=n;k++){
	 		double u=rndGenerator();
	 		double f=a+u*(b-a);
	 		System.out.println("Uniform"+k+":"+f);
	 	}
	} 

	//Exponential distribution
	public static void exponential(int n,double l) { 
	 	
	 	gen.setSeed(seed);
	 	for(int k=1;k<=n;k++){
	 		double u=rndGenerator();
	 		double f=-(Math.log(u)/l);
	 		System.out.println("Exponential"+k+":"+f);
	 	}
	} 

	//Gamma Distribution: Sum of alpha independent exponential variables
	public static void gamma(int n,int a,int l) { 
	 	
	 	gen.setSeed(seed);

	 	for(int k=1;k<=n;k++){
	 		double x=0;
	 		double u=rndGenerator();
	 		int j=1;
	 		while(j<=a){
	 			x=x+(-Math.log(u)/l);
	 		}
	 		
	 		System.out.println("Gamma"+k+":"+x);
	 	}
	}

	//Normal distribution: using Box-Muller transformation
	public static void normal(int n,double m,double sg) { 
	 	
	 	gen.setSeed(seed);
	 	for(int k=1;k<=n;k++){
	 		double u1=rndGenerator();
	 		double u2=rndGenerator();

	 		double z1=-(Math.sqrt(-2*Math.log(u1))*Math.cos(2*Math.PI*u2));
	 		double z2=-(Math.sqrt(-2*Math.log(u2))*Math.sin(2*Math.PI*u2));
	 		System.out.println("Normal"+k+"n1:"+(z1*sg+m));
	 		System.out.println("Normal"+k+"n2:"+(z2*sg+m));
	 	}
	}  

	//Random number generator, the next random number
	//For a particular	seed, the sequence is same
	public static double rndGenerator(){		
		double num = gen.nextDouble();
		return num;
	}
	public static void main(String args[]){
		try{
			if(args.length>=2){				
				if(args[1].equalsIgnoreCase("bernoulli")){
					int n=Integer.parseInt(args[0]);
					float p=Float.parseFloat(args[2]);
					bernoulli(p,n);
					
				}
				if(args[1].equalsIgnoreCase("binomial")){
					int n=Integer.parseInt(args[0]);
					int x=Integer.parseInt(args[2]);
					float p=Float.parseFloat(args[3]);
					binomial(p,n,x);
					
				}
				if(args[1].equalsIgnoreCase("geometric")){
			
					int n=Integer.parseInt(args[0]);
					float p=Float.parseFloat(args[2]);
					geometric(p,n);					 	
					
				}
				if(args[1].equalsIgnoreCase("neg-binomial")){
					
					int n=Integer.parseInt(args[0]);
					int k=Integer.parseInt(args[2]);
					float p=Float.parseFloat(args[3]);
					neg_binomial(p,n,k);					
				}
				if(args[1].equalsIgnoreCase("poisson")){
					
					int n=Integer.parseInt(args[0]);
					int l=Integer.parseInt(args[2]);
					
					poisson(n,l);					
				}
				if(args[1].equalsIgnoreCase("arb-discrete")){
					for(int i=2;i<args.length;i++){
						list.add(Double.parseDouble(args[i]));
					}
					int n=Integer.parseInt(args[0]);
					arb_disc(n);					
				}
				if(args[1].equalsIgnoreCase("uniform")){
					int n=Integer.parseInt(args[0]);
					double a=Double.parseDouble(args[2]);
					double b=Double.parseDouble(args[3]);
					uniform(n,a,b);					
				}
				if(args[1].equalsIgnoreCase("exponential")){
					int n=Integer.parseInt(args[0]);
					double l=Double.parseDouble(args[2]);
					exponential(n,l);					
				}
				if(args[1].equalsIgnoreCase("gamma")){
					int n=Integer.parseInt(args[0]);
					int a=Integer.parseInt(args[2]);
					int l=Integer.parseInt(args[3]);
					gamma(n,a,l);					
				}
				if(args[1].equalsIgnoreCase("normal")){
					int n=Integer.parseInt(args[0]);
					double m=Double.parseDouble(args[2]);
					double sg=Double.parseDouble(args[3]);
					normal(n,m,sg);					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	} 
}