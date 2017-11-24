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
class CreateResource extends SyntheticResource  {

    static final String ROOT_RESOURCE_TYPE = "${RootResource.ROOT_RESOURCE_TYPE}/create"

    CreateResource(@Nonnull final ResourceResolver resourceResolver,@Nonnull final String resolutionPath,
                                                                                        String resourceType = ROOT_RESOURCE_TYPE) {
        super(resourceResolver, resolutionPath, resourceType)
    }

    @Override
    String getResourceType() {
        return ROOT_RESOURCE_TYPE
    }
}
