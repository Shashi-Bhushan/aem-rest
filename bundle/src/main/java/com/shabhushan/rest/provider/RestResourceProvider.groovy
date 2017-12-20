package com.shabhushan.rest.provider

import com.shabhushan.rest.resource.CreateResource
import com.shabhushan.rest.resource.DeleteResource
import com.shabhushan.rest.resource.RandomizeResource
import com.shabhushan.rest.resource.ReadResource
import com.shabhushan.rest.resource.RootResource
import com.shabhushan.rest.resource.UpdateResource
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.resource.Resource
import org.apache.sling.spi.resource.provider.ResolveContext
import org.apache.sling.spi.resource.provider.ResourceContext
import org.apache.sling.spi.resource.provider.ResourceProvider
import org.osgi.service.component.ComponentContext

/**
 * @author Shashi Bhushan
 *
 * Change History:
 * Revision         Date            Dev             Comment
 * ----------------------------------------------------------------------------------------
 * 1.0              Nov 24, 2017    Shashi          Created Rest Resource Provider
 */

@Slf4j
@CompileStatic
@SuppressWarnings('GroovyUnusedDeclaration')
@Service(ResourceProvider)
@Component(label = 'Rest Resource Provider', metatype = true)
class RestResourceProvider extends ResourceProvider {

    @Property(label = "Provider Root Path", description = "Which Root Path This Resource Provider should handle", value = ['/rest'])
    public static final String PROVIDER_ROOT_PATH_KEY = PROPERTY_ROOT
    String providerRoot


    @Activate
    void activate(ComponentContext context) {
        def root = context.properties.get(PROVIDER_ROOT_PATH_KEY)
        providerRoot = root as String
    }

    @Override
    Resource getResource(
        @javax.annotation.Nonnull ResolveContext resolveContext,
        @javax.annotation.Nonnull String path,
        @javax.annotation.Nonnull ResourceContext resourceContext,
        @javax.annotation.CheckForNull Resource resource) {
        switch (path) {
            case ~/^\${providerRoot}\/create(\/.*)?$/:
                log.info "Resolving ${path} to Create Resource"
                return new CreateResource(resolveContext.resourceResolver, path)

            case ~/^\${providerRoot}\/read(\/.*)?$/:
                log.info "Resolving ${path} to Create Resource"
                return new ReadResource(resolveContext.resourceResolver, path)

            case ~/^\${providerRoot}\/update(\/.*)?$/:
                log.info "Resolving ${path} to Create Resource"
                return new UpdateResource(resolveContext.resourceResolver, path)

            case ~/^\${providerRoot}\/delete(\/.*)?$/:
                log.info "Resolving ${path} to Create Resource"
                return new DeleteResource(resolveContext.resourceResolver, path)

            case ~/^\${providerRoot}\/randomize(\/.*)?$/:
                log.info "Resolving ${path} to Create Resource"
                return new RandomizeResource(resolveContext.resourceResolver, path)

            default:
                log.info "Resolving ${path} to Default Resource"
                return new RootResource(resolveContext.resourceResolver, path)
        }
    }

    @Override
    Iterator<Resource> listChildren(
        @javax.annotation.Nonnull ResolveContext resolveContext, @javax.annotation.Nonnull Resource resource) {
        log.info "listChildren: Not Supported"

        throw new UnsupportedOperationException("List Children is not supported")
    }
}
