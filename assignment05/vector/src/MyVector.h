#include <iostream>
#include <sstream>

using namespace std;

#ifndef MYVECTOR_H_INCLUDED
#define MYVECTOR_H_INCLUDED

class MyVector {
private:
   int dimension;
   double* numbers;

public:
    MyVector(double numList[], int dimension);
    MyVector();
    void print() const;
    MyVector operator+(const MyVector& vec) const;
    MyVector operator-(const MyVector& vec) const;
    double operator*(const MyVector& vec) const;
    MyVector operator/(const MyVector& vec) const;

    string to_string() const;
    int get_dimension() const;
    double get(int index) const;
};

MyVector operator+(double num, const MyVector& vec);
MyVector operator-(double num, const MyVector& vec);
MyVector operator/(double num, const MyVector& vec);
MyVector operator*(double num, const MyVector& vec);

MyVector operator+(const MyVector& vec, double num);
MyVector operator-(const MyVector& vec, double num);
MyVector operator/(const MyVector& vec, double num);
MyVector operator*(const MyVector& vec, double num);

void print_error(char op, const MyVector& a, const MyVector& b);
void print_error(char op, double a, const MyVector& b);

#endif
