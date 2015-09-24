#ifndef RESOURCEMANAGER_H
#define RESOURCEMANAGER_H

#include <map>
#include <list>
#include <string>
#include <iostream>

#include "TinyXML.h"

#include "Resource.h"

#ifndef SAFE_DELETE
#define SAFE_DELETE(a) {delete(a); (a) = NULL;}
#endif // SAFE_DELETE

/**
 * The Resource Manager stores all resources for the entire game in a map
 * of integers to lists of Resources. It knows which scope the game is currently in,
 * and can tell its Resources to load and unload themselves during their scope.
 */
class ResourceManager : public EngineObject {
    private:
        void loopScopes(TiXmlNode* ResourceTree);
        void loopResources(TiXmlNode* Scope, unsigned int ScopeValue);
    protected:
        unsigned int CurrentScope;
        unsigned int ResourceCount;
    public:
        // All Resources in the game are stored here and loaded/unloaded
        // during their respective scene.
        std::map<unsigned int, std::list<Resource*> > Resources;
        Resource* findResourceByID(unsigned int ID);
        // Clears the entire Resources map
        void clear();
        bool loadFromXMLFile(std::string Filename);
        void setCurrentScope(unsigned int Scope);

        const unsigned int getResourceCount() {
            return ResourceCount;
        }

        inline ResourceManager() {
            CurrentScope = 0;
            ResourceCount = 0;
        }

};

#endif // RESOURCEMANAGER_H
