#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <fstream>
#include <utility>
#include <algorithm>
#include <functional> 
#include "DemoPortal.h"

int DemoPortal::requestID = 0;

std::vector<std::string> DemoPortal::split(std::string s) {
    std::vector<std::string> splitS;
    std::string current = "";
    for(char c: s) {
        if(c == ' ') {
            splitS.push_back(current);
            current = "";
        } else {
            current.push_back(c);
        }
    }
    splitS.push_back(current);
    return splitS;
}
void DemoPortal::printAll(std::vector<std::string> data) {
    for(std::string word: data) {
        std::cout << word << "\n";
    }
}

void DemoPortal::Sort(std::string parameter, std::vector<std::string> &List) {
    int parameterIndex = (parameter == "Name" ? 0 : 2);
    std::vector<std::pair<std::string, std::string>> products;
    for(std::string s: List) {
        std::vector<std::string> splitS = split(s);
        products.push_back(make_pair(splitS[parameterIndex], s));
    }
    std::sort(products.begin(), products.end(), 
    [](const std::pair<std::string, std::string> &p1, const std::pair<std::string, std::string> &p2) {
        return p1.first < p2.first; } ); // lambda function
    List.clear();
    for(auto p: products) {
        List.push_back(p.second);
    }
}

DemoPortal::DemoPortal() {
    portalID = "portal";
    userCommands.push("Start");
    std::ofstream outfile;
    outfile.open("PortalToPlatform.txt");
    outfile << portalID << " " << requestID << " " << "Start" << "\n";
}

void DemoPortal::processUserCommand(std::string command) {
    requestID++;
    userCommands.push(command);
    std::vector<std::string> splitCommand = split(command);

    std::ofstream outfile;
    outfile.open("PortalToPlatform.txt", std::ios_base::app);
    if(splitCommand[0] == "List") {
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << "\n";
    } else if(splitCommand[0] == "Buy") {
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << " " << splitCommand[2] << "\n";
    } else if(splitCommand[0] == "Check") {
        checkResponse();
    } 
    outfile.close();
}

void DemoPortal::checkResponse() {
    std::ifstream infile;
    infile.open("PlatformToPortal.txt");
    std::string data;
    std::vector<std::string> printData;
    while(getline(infile, data)) {
        if(data == "-") {
            std::string oldestCommand = userCommands.front();
            std::vector<std::string> splitOld = split(oldestCommand);
            std::cout << oldestCommand << ": ";
            if(splitOld[0] == "List") {
                if(splitOld[2] != "Name" && splitOld[2] != "Price") {
                    std::cout << "error\n";
                } else {
                    std::cout << "\n";
                    Sort(splitOld[2], printData);
                    printAll(printData);
                }
            } else if(splitOld[0] == "Start" || splitOld[0] == "Buy") {
                printAll(printData);
            } else {
                std::cout << "error\n";
            }
            printData.clear();
            userCommands.pop();
            continue;
        }
        std::vector<std::string> splitData = split(data);
        std::string withoutID = "";
        for(int i = 2; i < (int)splitData.size(); i++) {
            withoutID += splitData[i];
            withoutID += " ";
        }
        printData.push_back(withoutID);
    }
    infile.close();

    std::ofstream outfile;
    outfile.open("PlatformToPortal.txt");
    outfile << "";
    outfile.close();
}