#include <iostream>
#include <cstring>
#include <cstdio>

using namespace std;

class Student {
private:
    char name[129];
    int grade;
public:
    Student() {
        strcpy(name, "");
        grade = 0;
    }

    void set(char name[], int grade) {
        strcpy(this->name, name);
        this->grade = grade;
    }

    int compare_with_name(const Student& other) const {
        for (int i = 0; name[i] != '\0'; i++) {
            if (other.name[i] == '\0')
                return strlen(name) - i;
            else if (other.name[i] != name[i])
                return name[i] - other.name[i];
        }
        return strlen(name) - strlen(other.name);
    }

    int compare_with_name_reversed(const Student& other) const {
        return -compare_with_name(other);
    }

    int compare_with_grade(const Student& other) const {
        return grade - other.grade;
    }

    int compare_with_grade_reversed(const Student& other) const {
        return -compare_with_grade(other);
    }
};

int main(void) {
    char criteria[7];
    char direction[12];
    scanf("Sorting criteria: %s (%s)", criteria, direction);
    direction[strlen(direction) - 1] = '\0';

    int n_of_students;
    cin >> n_of_students;

    Student students[n_of_students];
    for (int i = 0; i < n_of_students; i++) {
        char name[129];
        int grade;
        scanf("%s, %d", name, &grade);
        students[i].set(name, grade);
    }
}
