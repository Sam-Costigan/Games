#include <iostream>

#include "ErrorLogManager.h"
#include "Exception.h"

using namespace std;

int main() {
    ErrorLogManager* Log = ErrorLogManager::getErrorManager();
    Log->create("c:\\test.txt");

    try {
        THROW_EXCEPTION(1, "Error test");
    } catch(Exception& e) {
        Log->logException(e);
    }

    Log->close();

    return 0;
}
