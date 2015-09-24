#include "ErrorLogManager.h"

ErrorLogManager ErrorLogManager::ErrorManager;

ErrorLogManager* ErrorLogManager::getErrorManager() {
    return &ErrorManager;
}

void ErrorLogManager::create(std::string FileName) {
    LogFile.open(FileName.c_str());
}

void ErrorLogManager::flush() {
    LogFile << LogBuffer.str();
    LogFile.flush();
    LogBuffer.str("");
}

void ErrorLogManager::close() {
    LogFile.close();
}

void ErrorLogManager::logException(Exception e) {
    LogBuffer << getTimeString() << "\n" << e.what();
    flush();
}

std::string ErrorLogManager::getTimeString() {
    std::stringstream TimeString;

    struct tm *pTime;
    time_t ctTime;
    time(&ctTime);
    pTime = localtime(&ctTime);

    TimeString << std::setw(2) << std::setfill('0') << pTime->tm_hour << ":";
    TimeString << std::setw(2) << std::setfill('0') << pTime->tm_min << ":";
    TimeString << std::setw(2) << std::setfill('0') << pTime->tm_sec << ":";

    return TimeString.str();
}
