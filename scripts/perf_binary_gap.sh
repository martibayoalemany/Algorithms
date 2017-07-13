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

if  ! which perl6 > /dev/null; then
    sudo apt install perl6
fi

if ! which go > /dev/null; then
    umake go
    export GOROOT=/home/username/.local/share/umake/go/go-lang
    export PATH=/home/username/.local/share/umake/go/go-lang/bin:$PATH
fi

if ! which octave-cli > /dev/null; then
    sudo apt install octave-cli
fi

if ! which php > /dev/null; then
    sudo apt install php7.0-cli
fi

if ! which ruby > /dev/null; then
    sudo apt install ruby
fi


execute_with_perf() {
    cd $ROOT_PATH/$1
    time -f "real %E\tuser %U\t sys %S\t$2" $3   
}

cd $ROOT_PATH/src/main/java
#javac -source 1.9 -target 1.9 BinaryGap.java
javac -source 1.8 -target 1.8 -bootclasspath /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar BinaryGap.java 

cd $ROOT_PATH/src/main/nodejs
npm install .

cd $ROOT_PATH/src/main/c
cmake . 1>/dev/null
make 1>/dev/null
chmod +x BinaryGap

cd $ROOT_PATH/src/main/cs
dotnet restore 1>/dev/null
dotnet run 1>/dev/null

execute_with_perf "src/main/java" "java" "java BinaryGap"
execute_with_perf "src/main/nodejs" "nodejs_slow" "node binary_gap_slow"
execute_with_perf "src/main/nodejs" "nodejs" "node binary_gap"
execute_with_perf "src/main/c" "c" "./BinaryGap"
execute_with_perf "src/main/cs" "c_sharp" "dotnet bin/Debug/netcoreapp2.0/cs.dll"
execute_with_perf "src/main/pl" "perl" "perl binary_gap.pl"
execute_with_perf "src/main/pl6" "perl6" "perl6 binary_gap.pl"
execute_with_perf "src/main/py" "python" "python3 binary_gap.py"
execute_with_perf "src/main/matlab" "matlab" "./binary_gap.m"
execute_with_perf "src/main/php7" "php7" "./binary_gap.php"
execute_with_perf "src/main/ruby" "ruby" "./BinaryGap.rb"
