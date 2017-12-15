#include "MyVector.h"

void arrcpy(double dest[], const double from[], int size) {
    for(int i = 0; i < size; i++)
        dest[i] = from[i];
}

MyVector::MyVector(const double numList[], int dimension) {
    this->dimension = dimension;
    numbers = new double[dimension];
    arrcpy(this->numbers, numList, dimension);
}

MyVector::MyVector() {
    dimension = 0;
    numbers = new double[0];
}

void MyVector::to_stream(ostream& s) const {
    if (dimension == 0) {
        s << "()";
        return;
    }
    s << "(";
    for (int i = 0; i < dimension - 1; i++)
        s << numbers[i] << ", ";
    s << numbers[dimension - 1] << ")";
}

string MyVector::to_string() const {
    stringstream s;
    to_stream(s);
    return s.str();
}

void MyVector::print() const {
    to_stream(cout);
    cout << endl;
}

int MyVector::get_dimension() const {
    return dimension;
}

double MyVector::get(int index) const {
    return numbers[index];
}

MyVector MyVector::operator+(const MyVector& vec) const {
    if (dimension != vec.get_dimension()) {
        print_error('+', *this, vec);
        return MyVector();
    }
    double* n = new double[dimension];
    for (int i = 0; i < dimension; i++)
        n[i] = numbers[i] + vec.numbers[i];
    return MyVector(n, dimension);
}

MyVector MyVector::operator-(const MyVector& vec) const {
    if (dimension != vec.get_dimension()) {
        print_error('-', *this, vec);
        return MyVector();
    }
    double* n = new double[dimension];
    for (int i = 0; i < dimension; i++)
        n[i] = numbers[i] - vec.numbers[i];
    return MyVector(n, dimension);
}

double MyVector::operator*(const MyVector& vec) const {
    if (dimension != vec.get_dimension()) {
        print_error('*', *this, vec);
        return 0.0;
    }
    double d = 0.0;
    for (int i = 0; i < dimension; i++)
        d += numbers[i] * vec.numbers[i];
    return d;
}

MyVector MyVector::operator/(const MyVector& vec) const {
    print_error('/', *this, vec);
    return MyVector();
}

MyVector operator+(double num, const MyVector& vec) {
    double* d = new double[vec.get_dimension()];
    for (int i = 0; i < vec.get_dimension(); i++)
        d[i] = vec.get(i) + num;
    return MyVector(d, vec.get_dimension());
}

MyVector operator+(const MyVector& vec, double num) {
    return num + vec;
}

MyVector operator-(double num, const MyVector& vec) {
    print_error('-', num, vec);
    return MyVector();
}

MyVector operator-(const MyVector& vec, double num) {
    double* d = new double[vec.get_dimension()];
    for (int i = 0; i < vec.get_dimension(); i++)
        d[i] = vec.get(i) - num;
    return MyVector(d, vec.get_dimension());
}

MyVector operator*(double num, const MyVector& vec) {
    double* d = new double[vec.get_dimension()];
    for (int i = 0; i < vec.get_dimension(); i++)
        d[i] = vec.get(i) * num;
    return MyVector(d, vec.get_dimension());
}

MyVector operator*(const MyVector& vec, double num) {
    return num * vec;
}

MyVector operator/(double num, const MyVector& vec) {
    print_error('/', num, vec);
    return MyVector();
}

MyVector operator/(const MyVector& vec, double num) {
    double* d = new double[vec.get_dimension()];
    for (int i = 0; i < vec.get_dimension(); i++)
        d[i] = vec.get(i) / num;
    return MyVector(d, vec.get_dimension());
}

void print_error(char op, const MyVector& a, const MyVector& b) {
    cout << op << " cannot be applied to ";
    a.to_stream(cout);
    cout << " and ";
    b.to_stream(cout);
    cout << endl;
}

void print_error(char op, double a, const MyVector& b) {
    cout << op << " cannot be applied to " << a;
    cout << " and ";
    b.to_stream(cout);
    cout << endl;
}
