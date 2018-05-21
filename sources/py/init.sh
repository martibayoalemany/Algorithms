function py_init() {

    touch requirements.txt
    touch requirements_3_5.txt

    if ! which python3 > /dev/null; then
       sudo apt install python3 -y
    fi
    
    if ! which pyvenv > /dev/null; then
       sudo apt install python3-venv
    fi

    if ! which pip > /dev/null; then
       sudo apt install python-pip -y
    fi

    
    # python 3
    if [ ! -e .venv3 ]; then
        python3 -m venv .venv3 
    fi
    source .venv3/bin/activate
    pip install --upgrade pip
    pip install -r requirements_3_5.txt

    if [ -e ${ROOT_PATH}/requirements.txt ] >/dev/null; then
        # python 2.7
        if [ ! -e ~/.venv27 ]; then
            virtualenv ~/.venv27
        fi
        source ~/.venv27/bin/activate
        pip install --upgrade pip
        pip install -r requirements.txt
    fi

    #ctags -af .tags_py -R ~/.venv3/lib/python3.5/site-packages    
}

py_init