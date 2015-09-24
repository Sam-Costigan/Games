#ifndef ERRORLOGMANAGER_H
#define ERRORLOGMANAGER_H

#include <string>
#include <sstream>
#include <iostream>
#include <time.h>
#include <iomanip>
#include <fstream>


#include "EngineObject.h"
#include "Exception.h"

/**
 * ErrorLogManager implements the singleton design pattern by maintaining
 * a protected constructors and a protected static reference to itself.
 * It exposes that reference through a public static method returning a pointer
 * to the single object.
 */
class ErrorLogManager : public EngineObject {
    public:
        // This is the method exposing the singleton object
        static ErrorLogManager* getErrorManager();
    protected:
        ErrorLogManager(){};
        virtual ~ErrorLogManager(){};
        // ErrorManager is the singleton static reference
        static ErrorLogManager ErrorManager;
    public:
        std::stringstream LogBuffer;

        // This creates the log file to write to for the duration
        // of this Error Managers existence.
        void create(std::string FileName);

        // This commits the content from the Log Buffer to the Log File,
        // then clears the buffer of its contents.
        void flush();

        // This closes off the file at the end of the Error Managers existence.
        void close();

        // This commits an Exception to the Log Buffer, to be written to file
        // whenever possible.
        void logException(Exception e);

        std::string getTimeString();
        std::ofstream LogFile;
};

#endif // ERRORLOGMANAGER_H
