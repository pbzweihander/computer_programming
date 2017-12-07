
template <typename T> class Node {
public:
    T value;
    Node* next;

    Node(T i);
};

template <typename T> class Queue {
private:
    Node<T>* root;
    Node<T>* tail;

public:
    Queue();
    Queue(T seq[], int size);
    void push(T element);
    T pop();
    bool is_empty() const;
};

#include "queue_impl.h"
