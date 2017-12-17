
#include "MyVector.h"
#include <iomanip>

using namespace std;

int main(void) {
    double ad[] = {1.0, 2.0, 3.0, 4.0, 5.0};
    MyVector a = MyVector(ad, sizeof(ad) / sizeof(double));
    double bd[] = {2.0, 4.0, 6.0, 8.0, 10.0};
    MyVector b = MyVector(bd, sizeof(bd) / sizeof(double));
    double c = 3.14;

    cout << setprecision(14);
    cout << a * b << endl;
    (a + b).print();
    (a + c).print();
    (a * c).print();
    (a / c).print();
    (a - c).print();
}
