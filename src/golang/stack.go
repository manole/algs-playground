package main

import (
	"errors"
	"fmt"
)

// Stack of elements
type Stack struct {
	elements []int
}

// Push adds an element on top of the stack.
func (s *Stack) Push(e int) {
	s.elements = append(s.elements, e)
}

// Pop return the element from the top of the stack.
func (s *Stack) Pop() (int, error) {
	if len(s.elements) < 1 {
		return 0, errors.New("Stack is empty")
	}
	value := s.elements[len(s.elements)-1]
	s.elements = s.elements[:len(s.elements)-1]
	return value, nil
}

// Tests the stack.
func main() {
	s := new(Stack)
	s.Push(10)
	value, err := s.Pop()
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("Got value %d\n", value)
	}
}
