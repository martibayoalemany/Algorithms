SCRIPT_PATH=$(realpath $(dirname $BASH_SOURCE))
jshell --class-path /home/username/.groovy/grapes/nz.ac.waikato.cms.weka/weka-stable/jars/weka-stable-3.8.1.jar $SCRIPT_PATH/weka_check.jsh
