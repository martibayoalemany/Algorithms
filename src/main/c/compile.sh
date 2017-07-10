#!/usr/bin/env bash

gcc BinaryGap.c -o BinaryGap -lm 
# GProf profiling
gcc BinaryGap.c -o BinaryGapp -lm -pg
# gprof BinaryGapp gmon.out
