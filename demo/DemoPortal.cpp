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

int DemoPortal::requestID = 0;
int DemoPortal::currentRequest = 1;
int DemoPortal::portalID = 0;

std::vector<std::string> DemoPortal::split(std::string s)
{
    std::stringstream ss(s);
    std::string word;
    std::vector<std::string> splitS;
    while (ss >> word) {
        splitS.push_back(word);
    }

    return splitS;
}

int DemoPortal::toInt(std::string s)
{
    int ans = 0;
    for (int i = 0; i < (int)s.size(); i++)
    {
        int digit = s[i] - '0';
        ans = ans * 10 + digit;
    }
    return ans;
}

void DemoPortal::printAll(std::vector<std::string> data)
{
    for (std::string word : data)
    {
        std::cout << word << "\n";
    }
}

void DemoPortal::Sort(std::string parameter, std::vector<std::string> &List)
{
    int parameterIndex = (parameter == "Name" ? 0 : 2);
    std::vector<std::pair<std::string, std::string>> products;
    for (std::string s : List)
    {
        std::vector<std::string> splitS = split(s);
        if ((int)splitS.size() != 4)
        {
            products.push_back(make_pair("error", s));
            // std::cout << "HI";
            break;
        }
        products.push_back(make_pair(splitS[parameterIndex], s));
    }
    std::sort(products.begin(), products.end(),
              [](const std::pair<std::string, std::string> &p1, const std::pair<std::string, std::string> &p2)
              { return p1.first < p2.first; }); // lambda function
    List.clear();
    for (auto p : products)
    {
        List.push_back(p.second);
    }
}

void DemoPortal::writeToPlatform(std::vector<std::string> &printData)
{
    std::string oldestCommand = userCommands.front();
    std::vector<std::string> splitOld = split(oldestCommand);
    std::cout << oldestCommand << ": ";
    if (splitOld[0] == "List")
    {
        if (splitOld[2] != "Name" && splitOld[2] != "Price")
        {
            std::cout << "error\n";
        }
        else
        {
            std::cout << "\n";
            Sort(splitOld[2], printData);
            printAll(printData);
        }
    }
    else if (splitOld[0] == "Start" || splitOld[0] == "Buy")
    {
        printAll(printData);
    }
    else if (splitOld[0] != "Check")
    {
        std::cout << "error\n";
    }
    printData.clear();
    userCommands.pop();
    currentRequest++;
}

DemoPortal::DemoPortal()
{
    std::ofstream Platoutfile; // to clear PlatformToPortal
    Platoutfile.open("PlatformToPortal.txt");
    Platoutfile << "";
    Platoutfile.close();

    std::ofstream Portalout;
    Portalout.open("PortalToPlatform.txt");
    Portalout << "";
    Portalout.close();

    portalID++;
}

void DemoPortal::processUserCommand(std::string command)
{
    std::vector<std::string> splitCommand = split(command);

    std::ofstream outfile;
    outfile.open("PortalToPlatform.txt", std::ios_base::app);
    if (splitCommand[0] == "List")
    {
        requestID++;
        userCommands.push(command);
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << "\n";
    }
    else if (splitCommand[0] == "Buy")
    {
        requestID++;
        userCommands.push(command);
        outfile << portalID << " " << requestID << " " << splitCommand[0] << " " << splitCommand[1] << " " << splitCommand[2] << "\n";
    }
    else if (splitCommand[0] == "Check")
    {
        checkResponse();
    }
    else if (splitCommand[0] == "Start")
    {
        requestID++;
        userCommands.push(command);
        outfile << portalID << " " << requestID << " "
                << "Start"
                << "\n";
    }
    outfile.close(); // closes file
}

void DemoPortal::checkResponse()
{
    std::ifstream infile;
    infile.open("PlatformToPortal.txt");
    std::string data;
    std::vector<std::string> printData, platformText;
    while (getline(infile, data))
    {
        platformText.push_back(data);
    }
    for (int i = 0; i < (int)platformText.size(); i++)
    {
        std::vector<std::string> splitData = split(platformText[i]);
        std::string withoutID = "";
        for (int i = 2; i < (int)splitData.size(); i++)
        {
            withoutID += splitData[i];
            withoutID += " ";
        }
        if (currentRequest != toInt(splitData[1]))
        {
            writeToPlatform(printData);
        }
        printData.push_back(withoutID);
        if (i == (int)platformText.size() - 1)
        {
            writeToPlatform(printData);
        }
    }

    std::ofstream outfile; // to clear PlatformToPortal
    outfile.open("PlatformToPortal.txt");
    outfile << "";
    outfile.close();
}