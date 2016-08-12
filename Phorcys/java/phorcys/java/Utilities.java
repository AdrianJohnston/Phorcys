package phorcys.java;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/*
 * A bunch of static utilities that could be useful in
 * the running of a simulation.
 *	//TODO: Document these.
 */
public class Utilities {
	
	/*Static constants*/
	public static String NODE_STRING_NAME = "*node";
	public static String EDGE_STRING_NAME = "*edges";
	
	public static double NEW_AGENT_START = -Double.MAX_VALUE;
	
	public static String GENERATE_NAME = "DEFAULT";
	
	public static long uid = -1;

	/*Useful methods for metrics and simulations*/
	
	private static Random random = new XSRandom(System.currentTimeMillis());
	private static final double FIFTY_PERCENT = 0.5;
	
	
	public static double generateRandomRangeDouble(double min, double max){
		if (min > max){
			double tmp = min;
			min = max;
			max = tmp;
		} else if (min == max){
			return min;
		}
		
		return (min + (random.nextDouble() * (max - min))); 
	}
	
	public static int generateRandomRangeInteger(int min, int max){
		if (min > max){
			int tmp = min;
			min = max;
			max = tmp;
		} else if (min == max){
			return min;
		}
//		return CASRandom.random((max - min) + 1) + min;
		return random.nextInt((max - min) + 1) + min;
	}
	
	public static boolean generateWithProbabilty(double probability){
		return (random.nextDouble() < probability);
	}
	
	public static boolean generateCoinFlip(){
		return generateWithProbabilty(FIFTY_PERCENT);
	}
	
	public static float generateRandomFloat(){
		return random.nextFloat();
	}
	public static double generateRandomDouble(){
		return random.nextDouble();
	}
	
	public static Vector2 randomiseVectorDouble(double xMin, double xMax, double yMin, double yMax){	
		double X = generateRandomRangeDouble(xMin, xMax);
		double Y = generateRandomRangeDouble(yMin, yMax);
		
		return new Vector2(X,Y);
	}
	
	public static Vector2 randomiseVectorDouble(double min, double max){	
		return randomiseVectorDouble(min,max,min,max);
	}
	
	//TODO: This is probably more useless than originally thought.
	public static Vector2 randomiseVectorInteger(int xMin, int xMax, int yMin, int yMax){	
		double X = generateRandomRangeInteger(xMin, xMax);
		double Y = generateRandomRangeInteger(yMin, yMax);
		
		return new Vector2(X,Y);
	}
	
	//TODO: Explain this one well.
	public static Vector2 randomiseBooleanVector(double xMin, double xMax, double yMin, double yMax){
		double X, Y;
		if (generateCoinFlip()){
			X = xMin;
		} else {
			X = xMax;			
		}
		
		if (generateCoinFlip()){
			Y = yMin;
		} else {
			Y = yMax;
		}
		
		return new Vector2(X,Y);			
	}
	
	public static Vector2 randomiseVectorInteger(int min, int max){			
		return randomiseVectorInteger(min,max,min,max);
	}
	
	public static String generateTimeStamp(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static double calculateDistance2D(Vector2 vectorA, Vector2 vectorB){
		return Math.sqrt(Math.pow(vectorA.getX() - vectorB.getX(), 2) + Math.pow(vectorA.getX() - vectorB.getX(),2));
	}
	
	public static void writeToFile(String fileContents, String absoluteFilePath){
		File outputFile = new File(absoluteFilePath);	
		System.out.println("Writing file to: " + absoluteFilePath);
		
		try{			
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile.getAbsoluteFile(), false)));
			printWriter.print(fileContents);
			printWriter.close();
			
		} catch (IOException exc){
			exc.printStackTrace();
		}
	}
	
	public static long generateUID(){
		uid++;
		return uid;
	}

}
