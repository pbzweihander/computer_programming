
#include "MyVector.h"

using namespace std;

int main(void) {
    double ad[] = {};
    MyVector a = MyVector(ad, sizeof(ad) / sizeof(double));
    double bd[] = {2.0, 4.0, 6.0, 8.0, 10.0};
    MyVector b = MyVector(bd, sizeof(bd) / sizeof(double));

    cout << (a / b).to_string() << endl;
}
