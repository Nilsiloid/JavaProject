#include <string>
#include <vector>
#include <queue>
#include <iostream>
#include <fstream>
#include <utility>
#include <algorithm>
#include <functional>
#include <sstream> 
#include "DemoPortal.h"

int DemoPortal::requestID = 0; // initializing requestID to 0
int DemoPortal::currentRequest = 1; // initializing currentRequest to 1
int DemoPortal::portalID = 0; // initializing portalID to 0

std::vector<std::string> DemoPortal::split(std::string s) { 
    std::stringstream ss(s); // create a stream for the string 
    std::string word; // current word
    std::vector<std::string> splitS; // vector of strings
    while (ss >> word) {
        splitS.push_back(word); // adding to the list
    }
    return splitS; // returning
}

int DemoPortal::toInt(std::string s) {
    int ans = 0; // the int value of the stirng
    for (int i = 0; i < (int)s.size(); i++) {
        int digit = s[i] - '0'; // ascii number
        ans = ans * 10 + digit; 
    }
    return ans;
}

void DemoPortal::printAll(std::vector<std::string> data) {
    for (std::string word : data) { // for all strings in data
        std::cout << word << "\n"; // print
    }
}

void DemoPortal::Sort(std::string parameter, std::vector<std::string> &List) {
    int parameterIndex = -1; // which word in the string does the parameter occur in
    if(parameter == "Name") {  // if parameter is name
        parameterIndex = 0; // first word in string
    } else if(parameter == "Price") {
        parameterIndex = 2; // third word in the string
    }
    std::vector<std::pair<std::string, std::string>> products; // first attribute will be the the product's name/price and second the product line
    for (std::string s : List) { 
        std::vector<std::string> splitS = split(s); // splits
        products.push_back(make_pair(splitS[parameterIndex], s)); // adds it to the products list
    }
    std::sort(products.begin(), products.end(), // sorting
        [](const std::pair<std::string, std::string> &p1, const std::pair<std::string, std::string> &p2) 
              { return p1.first < p2.first; }); // lambda function to ensure stable sorting
    List.clear(); // clears the unsorted list
    for (auto p : products) {
        List.push_back(p.second); // adds to the list in sorted order
    }
}

void DemoPortal::writeToTerminal(std::vector<std::string> &printData) {
    std::string oldestCommand = userCommands.front(); // the oldest unprocessed user command
    userCommands.pop(); // removing the oldest from the queue
    std::vector<std::string> splitOld = split(oldestCommand); // splitting
    std::cout << oldestCommand << ": ";
    if (splitOld[0] == "List") { // if the command is to list 
        if (splitOld[2] != "Name" && splitOld[2] != "Price") { // if the attribute is other than name or price, its not allowed
            std::cout << "error\n";
        } else { // appropriate attribute
            std::cout << "\n";
            Sort(splitOld[2], printData); // sorts the data
            printAll(printData); // prints it
        }
    } else if (splitOld[0] == "Start" || splitOld[0] == "Buy") { // if the command is to start or buy 
        printAll(printData); // just print
    }
    else if (splitOld[0] != "Check") { // if the command is not to check and the previous, its not allowed
        std::cout << "error\n";
    }
    printData.clear(); // clears the vector
    currentRequest++; // next requestID to expect
}

DemoPortal::DemoPortal() { 
    std::ofstream Platoutfile; // to clear and to make PlatformToPortal.txt
    Platoutfile.open("PlatformToPortal.txt"); // opens file
    Platoutfile << ""; 
    Platoutfile.close(); // closes file 

    std::ofstream Portalout; // to clear and to make PortalToPlatform.txt
    Portalout.open("PortalToPlatform.txt"); // opens file
    Portalout << "";
    Portalout.close(); // closes file

    portalID++; // new portal is formed
}

void DemoPortal::processUserCommand(std::string command) {
    std::vector<std::string> splitCommand = split(command);

    std::ofstream outfile; 
    outfile.open("PortalToPlatform.txt", std::ios_base::app); // to append to PortalToPlatform.txt
    if (splitCommand[0] == "List") { // if command is to list
        requestID++; // valid request hence increment requestID
        userCommands.push(command); // add to userCommands
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << "\n"; // writes to file
    } else if (splitCommand[0] == "Buy") { // if the user command is to buy
        requestID++; // increment requestID
        userCommands.push(command); // add to userCommands
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << " " << splitCommand[2] << "\n"; // writes to file
    } else if (splitCommand[0] == "Check") { // if the user command is to check
        checkResponse(); 
    } else if (splitCommand[0] == "Start") { // if the user command is to start
        requestID++; // increment requestID
        userCommands.push(command); // add to userCommands
        outfile << portalID << " " << requestID << " " << "Start" << "\n"; // write to file
    }
    outfile.close(); // closes file
}

void DemoPortal::checkResponse() {
    std::ifstream infile;
    infile.open("PlatformToPortal.txt"); // opens file to read
    std::string data; // data in the file
    std::vector<std::string> printData, platformText; // printData is the data to be printed, platformText is list of strings from file
    while (getline(infile, data)) {
        platformText.push_back(data); // adds to plaformText
    }
    for (int i = 0; i < (int)platformText.size(); i++) {
        std::vector<std::string> splitData = split(platformText[i]);
        std::string withoutID = ""; // data without the platformID and request ID
        for (int i = 2; i < (int)splitData.size(); i++) {
            withoutID += splitData[i];
            withoutID += " ";
        }
        if (currentRequest != toInt(splitData[1])) { // data of new request detected
            writeToTerminal(printData); // writes the data till now to the terminal and clears printData
        }
        printData.push_back(withoutID); // adds to printData
        if (i == (int)platformText.size() - 1) { // if the last line in the file
            writeToTerminal(printData); // print
        }
    }

    std::ofstream outfile; // to clear PlatformToPortal after each check so that next check doesnt clash with the current check
    outfile.open("PlatformToPortal.txt"); // opens file
    outfile << "";
    outfile.close(); // closes file
}