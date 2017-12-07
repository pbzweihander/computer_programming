#include <iostream>

template <typename T> Node<T>::Node(T element) {
    value = element;
    next = NULL;
}

template <typename T> Queue<T>::Queue() {
    root = NULL;
    tail = NULL;
}

template <typename T> Queue<T>::Queue(T seq[], int size) {
    root = NULL;
    tail = NULL;
    for (int i = 0; i < size; i++)
        push(seq[i]);
}

template <typename T> void Queue<T>::push(T element) {
    if (root == NULL) {
        root = new Node<T>(element);
        tail = root;
    } else {
        tail->next = new Node<T>(element);
        tail = tail->next;
    }
}

template <typename T> T Queue<T>::pop() {
    const T element = root->value;
    Node<T>* tmp = root;
    root = root->next;
    free(tmp);
    return element;
}

template <typename T> bool Queue<T>::is_empty() const {
    return root == NULL;
}
