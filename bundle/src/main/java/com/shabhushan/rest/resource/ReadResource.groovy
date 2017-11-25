package com.shabhushan.rest.resource

import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.SyntheticResource

import javax.annotation.Nonnull

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              Nov 24, 2017    Shashi          Created Synthetic Resource
 */
class ReadResource extends RootResource {

    static final String READ_RESOURCE_TYPE = "${ROOT_RESOURCE_TYPE}/read"

    ReadResource(@Nonnull final ResourceResolver resourceResolver, @Nonnull final String resolutionPath,
                 String resourceType = READ_RESOURCE_TYPE) {
        super(resourceResolver, resolutionPath, resourceType)
    }

    @Override
    String getResourceType() {
        return READ_RESOURCE_TYPE
    }
}
