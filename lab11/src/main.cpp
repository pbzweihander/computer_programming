#include <iostream>

using namespace std;

void copy_arr(int* dest, const int* source, const int size) {
    for (int i = 0; i < size; i++)
        dest[i] = source[i];
}

bool equals_arr(const int* a, const int* b, const int size) {
    for (int i = 0; i < size; i++)
        if (a[i] != b[i])
            return false;
    return true;
}

void push_arr_right(int* arr, const int size, const int n) {
    int* tmp = new int[n];
    copy_arr(tmp, arr, n);
    copy_arr(arr, arr + n, size - n);
    copy_arr(arr + size - n, tmp, n);
}

class Triangle {
private:
    int size;
    int* arr;

public:
    Triangle(const int size) {
        this->size = size;
        arr = new int[size];
    }

    int* get_arr() const {
        return arr;
    }

    void set(const int* arr) {
        copy_arr(this->arr, arr, size);
    }

    bool equals(const Triangle& other) const {
        int* others = new int[size];
        copy_arr(others, other.get_arr(), size);
        if (equals_arr(arr, others, size))
            return true;
        push_arr_right(others, size, size / 3);
        if (equals_arr(arr, others, size))
            return true;
        push_arr_right(others, size, size / 3);
        if (equals_arr(arr, others, size))
            return true;
        return false;
    }
};

int* get_int_arr(const int size) {
    int* arr = new int[size];
    for (int i = 0; i < size; i++) {
        int n;
        cin >> n;
        arr[i] = n;
    }
    return arr;
}

int main(void) {
    int size;
    cin >> size;
    Triangle a = Triangle(size * 3);
    Triangle b = Triangle(size * 3);

    a.set(get_int_arr(size * 3));
    b.set(get_int_arr(size * 3));

    cout << (a.equals(b) ? "True" : "False") << endl;
}
