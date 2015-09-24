#include "Exception.h"

Exception::Exception(int ErrorNumber, std::string ErrorDescription, std::string SourceFileName, int LineNumber) {
    this->ErrorNumber = ErrorNumber;
    this->ErrorDescription = ErrorDescription;
    this->SourceFileName = SourceFileName;
    this->LineNumber = LineNumber;

    std::stringstream ErrorText;

    ErrorText << "Error Number: " << ErrorNumber << "\nError Description: " << ErrorDescription
    << "\nSource File: " << SourceFileName << "\nLine Number: " << LineNumber << "\n";

    this->ErrorText = ErrorText.str();
}

const char* Exception::what() {
    return ErrorText.c_str();
}
