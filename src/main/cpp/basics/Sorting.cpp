
#include "Sorting.h"
#include <regex>
#include <fstream>
#include <chrono>
#include <boost/filesystem.hpp>
#include <codecvt>
#include <algorithm>
#include "../utils/Utils.h"

using namespace std;
using namespace chrono;

void Sorting::read_numbers_file() {

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
            m_vector.push_back(atoi(line.c_str()));
        }

        tl_point end = hrc::now();
        cout << "Read file " << txt_data << " in " << duration_cast<milliseconds>(end - start).count() << " ms " << endl;
    } catch(std::runtime_error & ex) {
        cout << ex.what() << endl;
    }

}

int main(int argc, char * argv[]) {
    unique_ptr<Sorting> sorting = make_unique<Sorting>();
    vector<int>& v = sorting.get()->getVector();
    using cl = chrono::high_resolution_clock;
    using cl_point = cl::time_point;
    cl_point start = cl::now();

    {
        // Print the first 20 elements read from the file
        vector<int>::iterator it = v.begin();
        for (int j = 0; j < 20 && it < v.end(); j++, it++)
            cout << *it << "\t";
    }

    cout << "===========" << endl;
    {
        sort(v.begin(), v.end(), less_equal<int>());
        cl_point end = cl::now();
        cout << duration_cast<milliseconds>(end - start).count() << " ms" << endl;

        for (int i = 10000; i < 100000; i += 10000) {
            auto start = v.cbegin();
            cout << i << endl;
            for (int j = 0; j < 20 && start != v.cend(); j++, start++) {
                cout << *start << "\t";
            }
            cout << " ------------- " << endl;
        }
    }
}