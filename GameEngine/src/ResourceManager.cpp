#include "ResourceManager.h"

Resource* ResourceManager::findResourceByID(unsigned int ID) {
    std::map<unsigned int, std::list<Resource*> >::iterator it;

    for(it = Resources.begin(); it != Resources.end(); it++) {
        if(!(*it).second.empty()) {
            std::list<Resource*>::iterator listIterator;

            for(listIterator = (*it).second.begin(); listIterator != (*it).second.end(); listIterator++) {
                if((*listIterator)->ID == ID) {
                    return (*listIterator);
                }
            }
        }
    }

    return NULL;
}

void ResourceManager::clear() {
    std::map<unsigned int, std::list<Resource*> >::iterator it;

    for(it = Resources.begin(); it != Resources.end(); it++) {
        if(!(*it).second.empty()) {
            std::list<Resource*>::iterator listIterator;

            for(listIterator = (*it).second.begin(); listIterator != (*it).second.end(); listIterator++) {
                (*listIterator)->unload();
                SAFE_DELETE(*listIterator);
            }

            (*it).second.clear();
        }
    }

    Resources.clear();
}

/**
 * This modifies the current scope by first unloading all Resources
 * in the previous scope, then changing the scope and loading the new Resources.
 */
void ResourceManager::setCurrentScope(unsigned int Scope) {
    std::list<Resource*>::iterator listIterator;

    // The current scope is not global
    if(CurrentScope != 0) {
        for(listIterator = Resources[CurrentScope].begin(); listIterator != Resources[CurrentScope].end(); listIterator++) {
            (*listIterator)->unload();
        }
    }

    CurrentScope = Scope;

    for(listIterator = Resources[CurrentScope].begin(); listIterator != Resources[CurrentScope].end(); listIterator++) {
        (*listIterator)->load();
    }
}

/**
 * This method will load in the Resources from an XML file.
 * The expected format for the XML file is the following:
 * <resources>
 *  <scope val="0">
 *      <resource id="0" type="text" filename="gamedata.cfg"></resource>
 *  </scope>
 *  <scope val="1">
 *      <resource id="1" type="graphics" filename="background.png"></resource>
 *      <resource id="2" type="audio" filename="theme.wav"></resource>
 *  </scope>
 * </resources>
 */
bool ResourceManager::loadFromXMLFile(std::string Filename) {

    // Load in the entire document given by the Filename
    TiXmlDocument doc(Filename.c_str());

    if(doc.LoadFile()) {
        TiXmlNode* ResourceTree = doc.FirstChild("resources");

        if(ResourceTree) {
            loopScopes(ResourceTree);

            return true;
        }
    }

    return false;
}


void ResourceManager::loopScopes(TiXmlNode* ResourceTree) {
    for(TiXmlNode* scope = ResourceTree->FirstChild(); scope; scope = scope->NextSibling()) {
        TiXmlElement* ScopeElement = scope->ToElement();

        if(ScopeElement) {
            unsigned int ScopeValue = atoi(ScopeElement->Attribute("val"));

            loopResources(scope, ScopeValue);
        }
    }
}
void ResourceManager::loopResources(TiXmlNode* Scope, unsigned int ScopeValue) {
    for(TiXmlNode* child = Scope->FirstChild("resource"); child; child = child->NextSibling()) {
        TiXmlElement* Element = child->ToElement();

        if(Element) {
            Resource* Res = NULL;

            // Loops through each element on the <resource> object
            for(TiXmlAttribute* Attribute = Element->FirstAttribute(); Attribute; Attribute = Attribute->Next()) {
                std::string Name = Attribute->Name();
                std::string Value = Attribute->Value();

                if(Name == "type") {
                    if(Value == "graphics") {
                        // Render Manager creates the Resource
                    }

                    if(Value == "audio") {
                        // Audio Manager creates the Resource
                    }

                    if(Value == "text") {
                        // Something else creates the Resource
                    }
                }

                // Check that the Resource has been created first!
                if(Res) {
                    if(Name == "id") {
                        Res->ID = atoi(Value.c_str());
                    }

                    if(Name == "filename") {
                        Res->FileName = Value;
                    }
                }
            }

            if(Res) {
                Resources[ScopeValue].push_back(Res);
                ResourceCount++;
            }
        }
    }
}
