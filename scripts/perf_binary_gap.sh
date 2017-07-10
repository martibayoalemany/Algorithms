#!/usr/bin/env bash
echo BASH_SOURCE: $(realpath $BASH_SOURCE)
SCRIPT_PATH=$(realpath $(dirname $BASH_SOURCE))
ROOT_PATH=$(realpath $SCRIPT_PATH/..)

if  ! which umake > /dev/null; then
    sudo apt install umake
fi

if  ! which node > /dev/null; then
    umake nodejs
    export PATH=$PATH:~/.local/share/umake/nodejs/nodejs-lang/bin
fi

cd $ROOT_PATH/src/main/nodejs
npm install .
echo --- node js ---
time -p node binary_gap 

cd $ROOT_PATH/src/main/java
javac -source 1.9 -target 1.9 BinaryGap.java 
echo --- java ---
time -p java  BinaryGap

cd $ROOT_PATH/src/main/c
cmake .
make
echo --- c ---
chmod +x BinaryGap
time -p ./BinaryGap 

