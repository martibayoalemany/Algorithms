#!/usr/bin/env sh

source activate

$SCRIPT_PATH/perf_algs.sh  | egrep -e 'user' 
