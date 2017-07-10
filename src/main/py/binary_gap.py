""" BinaryGap algorithm """

#!/usr/bin/env python3
# pylint: skip-file
from operator import mod
import time

# TODO: This code needs to be fixed
if False:
    def execute(value : int):
        while value > 0 and mod(value, 2) == 0:
            value = value / 2

        print("execute " + str(value))

        currentGap = 0
        maxGap = 0
        while value > 0:
            remainder = mod(value, 2)
            print("--> " + str(value) + " -" + str(remainder))
            if remainder == 0:
                currentGap += 1
            elif currentGap != 0:
                maxGap = max(currentGap, maxGap)
                currentGap = 0
                print(value + " " + maxGap + " " + currentGap)
            value = value / 2
        return maxGap


    if __name__ == "__main__":

        start = time.clock()
        result = execute(2323)
        end = time.clock()

        print("Binary Gap: {result} [{time}]".format(
            time=(end - start) * 1000,
            result=str(result)))
