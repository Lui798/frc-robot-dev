package frc.robot.util;

public class Debug
{
    private static String oldString = "";

    public static void printOnce(String s)
    {
        if (!s.equals(oldString))
        {
            System.out.println(s);
            oldString = s;
        }
    }
}