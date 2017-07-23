#!/usr/bin/env sh

. activate

$SCRIPT_PATH/perf_algs.sh  | egrep -e 'user' 
