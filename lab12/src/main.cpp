#include "func.h"
#include "rpg.h"
#include <iostream>
#include <cstring>

using namespace std;

const string msg[] = { "Increasing", "Decreasing", "Constant", "Dynamic", "Others" };

void non_rpg_mode() {
    int n;
    cin >> n;
    int is[n];
    for (int i = 0; i < n; i++)
        cin >> is[i];
    FuncType ft = get_functype<int>(is, n);
    cout << msg[ft] << endl;
}

void rpg_mode() {
    int n;
    cin >> n;

    int levels[n];
    int exps[n];

    for (int i = 0; i < n; i++)
        cin >> levels[i];
    for (int i = 0; i < n; i++)
        cin >> exps[i];

    Character characters[n];
    for (int i = 0; i < n; i++) {
        characters[i].set(levels[i], exps[i]);
        if (!characters[i].check_validity()) {
            cout << "Invalid Input!" << endl;
            return;
        }
    }

    FuncType ft = get_functype<Character>(characters, n);
    cout << msg[ft] << endl;
}

int main(int argc, char* argv[]) {
    if (argc < 2)
        non_rpg_mode();
    else if (strcmp(argv[1], "rpg") == 0)
        rpg_mode();
}
