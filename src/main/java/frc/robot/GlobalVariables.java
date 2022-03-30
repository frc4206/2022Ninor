package frc.robot;

public class GlobalVariables {
  
  private static GlobalVariables globVar;
  
  public static double pneumaticPressureSensor;
  public static double climberLeftEncoder;
  public static double climberRightEncoder;
  public static String climbtasknumber;
  public static boolean Limitswitch;

  public static double UpperVelocitySet = 7000.0;
  public static double LowerVelocitySet = 15000.0;
  public static double UpperShooterVelocity;
  public static double LowerShooterVelocity;

  public static double UpperVelocitySetXSpot = 20100;//currently testing the balls for this 21150.0
  public static double LowerVelocitySetXSpot = 6100;//this is the starting position for tomorow  7050.0

  public static double UpperVelocitySetSafe = 19300; // w/ 45 angle
  public static double LowerVelocitySetSafe = 6100; // w/ 45 angle

  public static double UpperVelocitySetWallHub = 3900.0;//these two numbers are done 
  public static double LowerVelocitySetWallHub = 8100.0;//these will be tuned later

  public static double UpperVelocitySetWallHubplus = 4100.0;//these two numbers are done 
  public static double LowerVelocitySetWallHubplus = 8300.0;//these will be tuned later

  public static double UpperVelocitySetWallLow = 3050.0;//completly untested
  public static double LowerVelocitySetWallLow = 3050.0;//not done at all

}
