using System;
using System.Linq;
using System.Collections.Generic;
using System.Diagnostics;

namespace cs
{
    public class BinaryGap
    {

        public static void Main2(string[] args)
        {            
            Stopwatch stopwatch = Stopwatch.StartNew(); 
            int result = execute(654345);
            stopwatch.Stop();
            String msg = $"Binary Gap: {result} [{stopwatch.ElapsedMilliseconds} ms]";
            Console.WriteLine(msg);

        }

        public static int execute(int value) {
            while(value > 0  && value % 2 == 0)
                value = value / 2;

            int currentGap = 0;
            int maxGap = 0;
            while (value > 0) {
                int remainder = value % 2;
                if(remainder == 0) {
                    currentGap ++;
                } else if (currentGap != 0) {
                    maxGap = Math.Max(currentGap, maxGap);
                    currentGap = 0;
                }
                value = value / 2;
            }
            return maxGap;
        }
    }
}
