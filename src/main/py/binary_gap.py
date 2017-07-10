""" BinaryGap algorithm """

#!/usr/bin/env python3
# pylint: skip-file
from operator import mod

def execute(value):
    while value > 0 and (mod(value, 2) == 0):
        value = value / 2

    currentGap = 0
    maxGap = 0
    while int(value) > 0:
        remainder = int(mod(value, 2))
        if remainder == 0:
            currentGap += 1
        elif currentGap != 0:
            maxGap = max(currentGap, maxGap)
            currentGap = 0
        value = value / 2
    return maxGap

if __name__ == "__main__":
    result = execute(654345)

    print("Binary Gap: {result} ".format(
        result=str(result)))
