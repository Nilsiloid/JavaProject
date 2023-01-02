#include <string>
#include <vector>
#include <queue>
#include <utility>
#include "../ecomm/Portal.h"

#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H

class DemoPortal: public Portal {
    private:
    std::queue<std::string> userCommands; // to save user commands
    static int requestID;
    std::string portalID;
    std::vector<std::string> split(std::string s);
    void printAll(std::vector<std::string> data);
    void Sort(std::string parameter, std::vector<std::string> &List);
    public:
    DemoPortal();
    void processUserCommand(std::string command);
    void checkResponse();
};

#endif