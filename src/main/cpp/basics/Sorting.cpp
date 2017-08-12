
#include "Sorting.h"
#include <regex>
#include <iostream>
#include <fstream>
#include <chrono>
#include <boost/filesystem.hpp>
#include <codecvt>
#include "../utils/Utils.h"

using namespace std;
using namespace chrono;

vector<int> Sorting::read_numbers_file() {
    vector<int> vector;
    const string & txt_data = Utils::getCurrentDir();
    cout << " Opening file for " << txt_data << endl;
    using tl_point = high_resolution_clock::time_point;
    using hrc = high_resolution_clock;
    tl_point start = hrc::now();
    string filename;
    try {
        fstream dataFile;

        dataFile.open(txt_data.c_str(), ios::in);
        cout << " --- " <<  "/home/username/src/src-gitlab/Algorithms/data/random_numbers.txt" << endl;
        cout << " --- " <<  txt_data << endl;

        string line;
        while(getline(dataFile, line)) {
            cout << line << endl;
            vector.push_back(std::atoi(line.c_str()));
        }

        tl_point end = hrc::now();
        cout << "Read file " << txt_data << " in " << duration_cast<milliseconds>(end - start).count() << " ms " << endl;
    } catch(std::runtime_error & ex) {
        cout << ex.what() << endl;
    }
    return vector;
}
int main(int argc, char * argv[]) {
    vector<int> numbers = read_numbers_file();

}