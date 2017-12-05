#include <assert.h>

#ifndef MYVECTOR_H_INCLUDED
#define MYVECTOR_H_INCLUDED

class MyVector{
   private:
   public:
    MyVector(double numList[], int size);
    MyVector();
    void print();
    MyVector operator+(MyVector vec);
    double operator*(MyVector vec);
    MyVector operator/(MyVector vec);
    MyVector operator-(MyVector vec);
};

#endif
