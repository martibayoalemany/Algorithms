#ifndef ALGORITHMS_MAIN_UTILS_H
#define ALGORITHMS_MAIN_UTILS_H

#include <iostream>
using namespace std;

class Utils {

public:
    static string getCurrentDir();

    static const string getDataFile(basic_string<char> binary_path);

    static const string getDataFile();
};


#endif //ALGORITHMS_MAIN_UTILS_H
