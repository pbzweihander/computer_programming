#include "rpg.h"

Character::Character() {
    this->level = -1;
    this->exp = -1;
}

Character::Character(int level, int exp) {
    this->level = level;
    this->exp = exp;
}

void Character::set(int level, int exp) {
    this->level = level;
    this->exp = exp;
}

int Character::compare(const Character& c) const {
    int ld = level - c.level;
    if (ld == 0)
        return exp - c.exp;
    return ld;
}

bool Character::operator==(const Character& c) const {
    return compare(c) == 0;
}

bool Character::operator<(const Character& c) const {
    return compare(c) < 0;
}

bool Character::operator>(const Character& c) const {
    return compare(c) > 0;
}

bool Character::check_validity() const {
    return exp < (10 * (1 << (level - 1)));
}
