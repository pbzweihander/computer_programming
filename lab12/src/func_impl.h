template <typename T> FuncType get_functype(T seq[], int size) {
    Queue<T> queue = Queue<T>(seq, size);
    T a = queue.pop();
    T b = queue.pop();
    if (a == b)
        return state_constant(b, queue);
    else if (a > b)
        return state_decrease(b, queue);
    else
        return state_increase(b, queue);
}

template <typename T> FuncType state_increase(T last, Queue<T>& queue) {
    if (queue.is_empty())
        return Increase;
    T now = queue.pop();
    if (last < now)
        return state_increase(now, queue);
    else if (last == now)
        return Others;
    else
        return state_dynamic(now, queue);
}

template <typename T> FuncType state_decrease(T last, Queue<T>& queue) {
    if (queue.is_empty())
        return Decrease;
    T now = queue.pop();
    if (last > now)
        return state_decrease(now, queue);
    else if (last == now)
        return Others;
    else
        return state_dynamic(now, queue);
}

template <typename T> FuncType state_constant(T last, Queue<T>& queue) {
    if (queue.is_empty())
        return Constant;
    T now = queue.pop();
    if (last == now)
        return state_constant(now, queue);
    else
        return Others;
}

template <typename T> FuncType state_dynamic(T last, Queue<T>& queue) {
    if (queue.is_empty())
        return Dynamic;
    T now = queue.pop();
    if (last == now)
        return Others;
    else
        return state_dynamic(now, queue);
}
