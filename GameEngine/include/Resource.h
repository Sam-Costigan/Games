#ifndef RESOURCE_H
#define RESOURCE_H

#include <string>

#include "EngineObject.h"

typedef enum {
    RESOURCE_NULL = 0,
    RESOURCE_GRAPHIC = 1,
    RESOURCE_MOVIE = 2,
    RESOURCE_AUDIO = 3,
    RESOURCE_TEXT = 4,
} RESOURCE_TYPE;

/**
 * This represents an abstraction of a single Resource used by the game. It stores data relating
 * to that Resource, including a unique ID, the Resources type and the file name.
 * Each Manager which relies on Resources should create their own implementation of a Resource,
 * which will handle the loading and unloading of that Resource when asked to.
 */
class Resource : public EngineObject {

    private:
    protected:
    public:
        unsigned int ID;
        std::string FileName;
        RESOURCE_TYPE Type;

        virtual ~Resource(){};
        // The load and unload methods are to be implemented by the
        // concrete Resource implementations in each Manager
        virtual void load(){};
        virtual void unload(){};

        inline Resource() {
            ID = 0;
            Type = RESOURCE_NULL;
        }

};

#endif // RESOURCE_H
