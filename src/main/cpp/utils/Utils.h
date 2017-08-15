#ifndef ALGORITHMS_MAIN_UTILS_H
#define ALGORITHMS_MAIN_UTILS_H

#include <iostream>
using namespace std;

class Utils {
private:
    static string getCurrentDir();

public:

    static const string getDataFile(basic_string<char> binary_path);

    static const string getDataFile();
};


#endif //ALGORITHMS_MAIN_UTILS_H
