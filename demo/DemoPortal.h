#include <string>
#include <vector>
#include <queue>
#include <utility>
#include "../ecomm/Portal.h"

#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H

class DemoPortal : public Portal {
private: // private attribtes and methods
    std::queue<std::string> userCommands; // to save user commands
    static int requestID; // counts the number of requests
    static int currentRequest; // to keep track of the next requestid when check comes
    static std::string portalID; // portalID
    std::vector<std::string> split(std::string s); // function to split a string
    int toInt(std::string s); // function to convert a string of numbers to int
    void printAll(std::vector<std::string> data); // prints all the strings of a vector of strings
    void Sort(std::string parameter, std::vector<std::string> &List); // stable sorts the items with respect to the parameter
    void writeToTerminal(std::vector<std::string> &printData); // takes the list of strings and prints it to terminal accordingly

public:
    DemoPortal(); // constructor
    void processUserCommand(std::string command); // processes a user command
    void checkResponse(); // when user sends a check
};

#endif