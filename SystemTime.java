public class SystemTime
{
    public static long time = 0;

    public static long getTime()
    {
        return time++;
    }
}
