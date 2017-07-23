#!/usr/bin/env bash

function set_env() {
    if  ! which realpath >/dev/null; then
        echo  'coreutils is not installed, realpath and dirname are not available'
        export SCRIPT_PATH=${BASH_SOURCE%/*}
        export ROOT_PATH=${SCRIPT_PATH%/*}
        export REQUIREMENTS_JAVA_PATH=$ROOT_PATH/requirements_java

        echo BASH_SOURCE: $BASH_SOURCE
        echo \$0\: $0
        echo SCRIPT_PATH: $SCRIPT_PATH 
        echo ROOT_PATH: $ROOT_PATH  
        echo REQUIREMENTS_JAVA_PATH: $REQUIREMENTS_JAVA_PATH
        echo --------------------------
    else
        export SCRIPT_PATH=$(realpath $(dirname $BASH_SOURCE))
        export ROOT_PATH=$(realpath $SCRIPT_PATH/..)
        export REQUIREMENTS_JAVA_PATH=$ROOT_PATH/requirements_java

        echo BASH_SOURCE: $(realpath $BASH_SOURCE)
        echo \$0\: $(realpath $0)
        echo SCRIPT_PATH: $SCRIPT_PATH 
        echo ROOT_PATH: $ROOT_PATH  
        echo REQUIREMENTS_JAVA_PATH: $REQUIREMENTS_JAVA_PATH
        echo --------------------------
    fi
}

set_env

if [ -e $1 ] 
then
   cd $1 
   . $SCRIPT_PATH/activate
else
   echo "running in travis ?"
   . $SCRIPT_PATH/activate 
fi

$SCRIPT_PATH/perf_algs.sh  | egrep -e 'user' 
