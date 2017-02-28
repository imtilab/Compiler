#Classwork02_ExpressionEvaluation

Task:

1. Write a java program to take a postfix expression (string) as input.
  - Identify the operators and operands [4]
  - Using stack evaluate the expression. [6]

* Assume there is always a space between operators and operands
    - 12 is evaluated as twelve and 1 2 is evaluated as two operands one and two.
    - Assume the all the operands are numeric
* Use +,-,*,/ as operators any other token is an operand
* You can use the stack class.


Sample Input:
```
5 3 + 12 * 3 /
8 3 * 12 / 3 +
8 12 3 / * 10 2 / -
```

Sample Output:
```
a. Operators: + * /; Operands: 5 3 12 3
b. Result: 32
a. Operators: * / +; Operands: 8 3 12 3
b. Result: 5
a. Operators: / * / -; Operands: 8 12 3 10 2
b. Result: 27
```


For part B:
- Tokenize the string
- If not operator
  - Push in stack
- If operator (op=operator)
  - Pop stack and store in ‘b’
  - Pop stack and store in ‘a’
  - Push a op b
- Repeat 2,3 until more tokens
