#pragma once

#include <iostream>
#include <vector>
#include <memory>

using namespace std;

class Sorting {

private:
    vector<int> m_vector;
    void read_numbers_file();

public:
    Sorting() {
        read_numbers_file();
    }

    virtual ~Sorting() {
        cout << "Sorting destructor" << endl;
    }

    vector<int> & getVector()  {
        return m_vector;
    }

};