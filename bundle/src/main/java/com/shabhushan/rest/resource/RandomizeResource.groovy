package com.shabhushan.rest.resource

import org.apache.sling.api.resource.ResourceResolver

import javax.annotation.Nonnull

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 *
 */
class RandomizeResource extends RootResource {

    static final String RANDOMIZE_RESOURCE_TYPE = "${ROOT_RESOURCE_TYPE}/randomize"

    RandomizeResource(@Nonnull final ResourceResolver resourceResolver, @Nonnull final String resolutionPath,
                 String resourceType = RANDOMIZE_RESOURCE_TYPE) {
        super(resourceResolver, resolutionPath, resourceType)
    }

    @Override
    String getResourceType() {
        return RANDOMIZE_RESOURCE_TYPE
    }
}
