#include <iostream>
#include <iomanip>

using namespace std;

int main(void) {
    string word;
    int sum = 0;
    int count = 0;

    while (cin >> word) {
        sum += word.length();
        count++;
    }
    cout << "Average Length: " << setprecision(2) << fixed << (double)sum / count << endl;
    cout << "# of words: " << count << endl;
}
