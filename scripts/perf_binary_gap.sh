#!/usr/bin/env sh
#echo SCRIPT PATH: $(realpath $0)
SCRIPT_PATH=$(realpath $(dirname $0))
ROOT_PATH=$(realpath $SCRIPT_PATH/..)
#echo ROOT PATH: $ROOT_PATH
perf=$ROOT_PATH/perf.log
#echo PERF Log: $perf

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
time -f "real %E\tuser %U\t sys %S\t" -o $perf  node binary_gap  2>/dev/null
sed '${s/$/\tnpm/}' $perf

cd $ROOT_PATH/src/main/java
#javac -source 1.9 -target 1.9 BinaryGap.java
javac -source 1.8 -target 1.8 -bootclasspath /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar BinaryGap.java 
echo --- java ---
time -f "real %E\tuser %U\t sys %S\t" -o $perf  java  BinaryGap
sed '${s/$/\tjava/}' $perf

cd $ROOT_PATH/src/main/c
cmake . 1>/dev/null
make 1>/dev/null
echo --- c ---
chmod +x BinaryGap
time -f "real %E\tuser %U\t sys %S\t" -o $perf  ./BinaryGap 
sed '${s/$/\tc/}' $perf

cd $ROOT_PATH/src/main/cs
dotnet restore 1>/dev/null
dotnet run 1>/dev/null
echo --- c# ---
time  -o $perf -f "real %E\tuser %U\t sys %S\t"  dotnet bin/Debug/netcoreapp2.0/cs.dll
sed '${s/$/\tc_sharp/}' $perf

cd $ROOT_PATH/src/main/pl
echo --- perl ---
time -o $perf -f "real %E\tuser %U\t sys %S\t" perl binary_gap.pl 1>/dev/null
sed '${s/$/\tperl/}' $perf


cd $ROOT_PATH/src/main/py
echo --- python ---
time -o $perf  -f "real %E\tuser %U\t sys %S\t" python3 binary_gap.py
sed '${s/$/\tpython/}' $perf
