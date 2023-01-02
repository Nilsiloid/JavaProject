#include <string>
#include <vector>
#include <queue>
#include <utility>
#include "../ecomm/Portal.h"

#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H

class DemoPortal: public Portal {
    private:
    std::queue<std::string> userCommands; 
    static int requestID;
    std::string portalID;
    public:
    std::vector<std::string> split(std::string s);
    void printAll(std::vector<std::string> data);
    void Sort(STDC_HEADERS::string parameter, std::vector<std::string> &List);
    DemoPortal();
    void processUserCommand(std::string command);
    void checkResponse();
};

#endif