#include <iostream>
#include <cstring>
#include <cstdio>

using namespace std;

typedef struct {
    char name[129];
    int grade;
} Student;

int compare_with_name(const Student* a, const Student* b) {
    int s = strcmp(a->name, b->name);
    if (s == 0)
        return a->grade - b->grade;
    return s;
}

int compare_with_name(const void* a, const void* b) {
    return compare_with_name((Student*) a, (Student*) b);
}

int compare_with_name_reversed(const void* a, const void* b) {
    return compare_with_name(b, a);
}

int compare_with_grade(const Student* a, const Student* b) {
    return a->grade - b->grade;
}

int compare_with_grade(const void* a, const void* b) {
    return compare_with_grade((Student*) a, (Student*) b);
}

int compare_with_grade_reversed(const void* a, const void* b) {
    return compare_with_grade(b, a);
}

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
        cin >> name >> grade;
        name[strlen(name) - 1] = '\0';
        strcpy(students[i].name, name);
        students[i].grade = grade;
    }

    if (strcmp(criteria, "Name") == 0) {
        if (strcmp(direction, "Increasing") == 0)
            qsort(students, n_of_students, sizeof(Student), compare_with_name);
        else if (strcmp(direction, "Decreasing") == 0)
            qsort(students, n_of_students, sizeof(Student), compare_with_name_reversed);
    } else if (strcmp(criteria, "Grade") == 0) {
        if (strcmp(direction, "Increasing") == 0)
            qsort(students, n_of_students, sizeof(Student), compare_with_grade);
        else if (strcmp(direction, "Decreasing") == 0)
            qsort(students, n_of_students, sizeof(Student), compare_with_grade_reversed);
    }

    for (int i = 0; i < n_of_students; i++) {
        cout << students[i].name << ", " << students[i].grade << endl;
    }
}
