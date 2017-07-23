#!/usr/bin/env sh

cd $1

. activate

$SCRIPT_PATH/perf_algs.sh  | egrep -e 'user' 
