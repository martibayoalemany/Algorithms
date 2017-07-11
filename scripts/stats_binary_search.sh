#!/usr/bin/env sh
SCRIPT_PATH=$(realpath $(dirname $0))
$SCRIPT_PATH/perf_binary_gap.sh  | egrep -e 'user' 
