#include <iostream>
#include <string>
using namespace std;

int main() {
    string op;
    double num1, num2, result;
    char var;
    string threeAddress;

    while (true) {
        cout << "Enter an arithmetic operation (+, -, *, /) or 'quit' to exit: ";
        cin >> op;

        if (op == "quit") {
            break;
        } else {
            cout << "Enter two numbers: ";
            cin >> num1 >> num2;

            cout << "Enter the variable to store the result: ";
            cin >> var;

            if (op == "+") {
                result = num1 + num2;
            } else if (op == "-") {
                result = num1 - num2;
            } else if (op == "*") {
                result = num1 * num2;
            } else if (op == "/") {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    cout << "Error: Division by zero" << endl;
                    continue;
                }
            } else {
                cout << "Invalid operation" << endl;
                continue;
            }

            // Generate three-address code
            threeAddress = var + " = " + to_string(num1) + " " + op + " " + to_string(num2);
            cout << "Three-address code: " << threeAddress << endl;
            cout << var << " = " << result << endl;
        }
    }

    return 0;
}
