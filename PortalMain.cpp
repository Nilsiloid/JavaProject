#include "demo/DemoPortal.h"
#include <iostream>
#include <fstream>
using namespace std;
class PortalMain {
    public:
    static void Main() {
        DemoPortal demoportal;
        while(1) {
            string data;
            getline(std::cin, data);
            if(data == "End") {
                break;
            } else {
                demoportal.processUserCommand(data);
            }
        }
    }
};
int main() {
    PortalMain::Main();
    // DemoPortal dportal;
    // dportal.processUserCommand("Buy s 1");
    // dportal.processUserCommand("Buy s 2");
    // dportal.processUserCommand("List Book Price");
    // dportal.processUserCommand("List Book Name");
    // dportal.checkResponse();
}