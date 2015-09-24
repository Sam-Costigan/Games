#ifndef EXCEPTION_H
#define EXCEPTION_H

#include <string>
#include <sstream>
#include <exception>

#ifndef THROW_EXCEPTION
#define THROW_EXCEPTION(ErrorNumber, ErrorDescription) throw Exception(ErrorNumber, ErrorDescription, __FILE__, __LINE__);
#endif // THROW_EXCEPTION

/**
 * Standard class for Exceptions used by the Game Engine. This allows a
 * typical format and class type that the Error Manager will expect,
 * allowing it to process all Exceptions in the same manner.
 */
class Exception : public std::exception {

    private:
    protected:
    public:
        int ErrorNumber;
        std::string ErrorDescription;
        std::string SourceFileName;
        int LineNumber;
        std::string ErrorText;

        // Override std::exception::what method.
        // Gives a string containing Error Number, Description, Source File and Line Number
        const char* what();

        // Define the constructor for an Exception. Takes all information relating to an Exception
        // and turns that information into
        Exception(int ErrorNumber, std::string ErrorDescription, std::string SourceFileName, int LineNumber);

        // This deconstructs the Exception when it is thrown, ensuring that Exceptions don't take up
        //any space in memory after they have been used for their intended purpose.
        ~Exception() throw() {}

};

#endif // EXCEPTION_H
