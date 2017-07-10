using System;
using System.Linq;
using System.Collections.Generic;
using System.Diagnostics;

namespace Algorithms
{
    public class BinayGap
    {

        static void Main(string[] args)
        {            
            Stopwatch stopwatch = Stopwatch.StartNew(); 
            int result = execute(2323);
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
