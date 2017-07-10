
#include <assert.h>
#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <unistd.h>
#include <time.h>

int execute(int value);

int main(int argc, char *argv[])
{
    int value = 654345;
    clock_t start = clock();
    int result = execute(value);
    clock_t end = clock();
    double elapsed_time = (end - start);
    printf("Binary Gap: %d [%f clock_t]\n", result, elapsed_time);
}

int execute(int value)
{
    while (value > 0 && fmod(value, 2) == 0)
        value = value / 2;

    double currentGap = 0;
    double maxGap = 0;
    while (value > 0)
    {
        double remainder = fmod(value, 2);
        if (remainder == 0)
        {
            currentGap++;
        }
        else if (currentGap != 0)
        {
            maxGap = fmax(currentGap, maxGap);
            currentGap = 0;
        }
        value = value / 2;
    }
    return maxGap;
}
