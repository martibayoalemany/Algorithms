#!/usr/bin/env sh

[ -e $1 ] && cd $1 || echo "running in travis ?" 

. activate

$SCRIPT_PATH/perf_algs.sh  | egrep -e 'user' 
