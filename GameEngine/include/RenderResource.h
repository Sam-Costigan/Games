#ifndef RENDERRESOURCE_H
#define RENDERRESOURCE_H

#include "Resource.h"

class RenderResource : public Resource {
    private:
    protected:
    public:
        SDL_Surface* Surface;

        ~RenderResource();
        void load();
        void unload();
        RenderResource();
};

#endif // RENDERRESOURCE_H
