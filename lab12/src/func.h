#include "queue.h"

typedef enum { Increase, Decrease, Constant, Dynamic, Others } FuncType;

template <typename T> FuncType get_functype(T seq[], int size);
template <typename T> FuncType state_increase(T last, Queue<T>& queue);
template <typename T> FuncType state_decrease(T last, Queue<T>& queue);
template <typename T> FuncType state_constant(T last, Queue<T>& queue);
template <typename T> FuncType state_dynamic(T last, Queue<T>& queue);

#include "func_impl.h"
