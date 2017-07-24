#!/usr/bin/env sh

execute_with_perf() {
   cd $ROOT_PATH/$1    
    time -f "%E,\t%U,\t%S,\t$2" $3 
    #time -f "real %E\tuser %U\t sys %S\t$2" $3
}

chmod +x $SCRIPT_PATH/install $SCRIPT_PATH/compile
$SCRIPT_PATH/install
$SCRIPT_PATH/compile


execute_with_perf "src/main/java" "java" "javac -cp . BinaryGap && java BinaryGap" $1
execute_with_perf "src/main/nodejs" "nodejs_slow" "node binary_gap_slow" $1
execute_with_perf "src/main/nodejs" "nodejs" "node binary_gap"
execute_with_perf "src/main/c" "c" "./BinaryGap"
execute_with_perf "src/main/cs" "c_sharp" "dotnet bin/Debug/netcoreapp2.0/cs.dll"
execute_with_perf "src/main/pl" "perl" "perl binary_gap.pl"
execute_with_perf "src/main/pl6" "perl6" "perl6 binary_gap.pl"
execute_with_perf "src/main/py" "python" "python3 binary_gap.py"
execute_with_perf "src/main/matlab" "matlab" "./binary_gap.m"
execute_with_perf "src/main/php7" "php7" "./binary_gap.php"
execute_with_perf "src/main/ruby" "ruby" "./BinaryGap.rb"
# Splitting the line in two, there seems to be an issue in travis
execute_with_perf "src/main/java/basics" "javac_sorting" "javac -cp . Sorting.java" 
execute_with_perf "src/main/java/basics" "java_sorting" "java Sorting 20000" 
execute_with_perf "src/main/java/basics" "java_sorting" "java Sorting 200000" 
execute_with_perf "src/main/java/basics" "java_sorting" "java Sorting 2000000" 


