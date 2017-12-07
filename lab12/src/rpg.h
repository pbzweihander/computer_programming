
class Character {
private:
    int level;
    int exp;

public:
    Character();
    Character(int level, int exp);
    void set(int level, int exp);
    int compare(const Character& c) const;
    bool operator==(const Character& c) const;
    bool operator<(const Character& c) const;
    bool operator>(const Character& c) const;
    bool check_validity() const;
};
