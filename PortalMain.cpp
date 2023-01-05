#include "demo/DemoPortal.h"
#include <iostream>
#include <fstream>

class PortalMain { // portalMain class
public: // public function
    static void Main() { // the main
        DemoPortal demoportal; // object of demoportal
        while(1) { // input loop
            std::string data; 
            getline(std::cin, data); // taking whole line as input 
            if(data == "End") { // if the user wants to end
                break;
            } else {
                demoportal.processUserCommand(data);  // asks demoportal to process user commands 
            }
        }
    }
};
int main() {
    PortalMain::Main(); // runs Main of PortalMain
}