package com.wreulicke.jaxrs;

import java.io.IOException;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.Populator;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;
import org.glassfish.hk2.utilities.DuplicatePostProcessor;
import org.glassfish.jersey.ServiceLocatorProvider;

public class DiscoveryFeature implements Feature {

  @Override
  public boolean configure(FeatureContext context) {
    ServiceLocator locator = ServiceLocatorProvider.getServiceLocator(context);
    DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);
    Populator populator = dcs.getPopulator();

    try {
      populator.populate(new ClasspathDescriptorFileFinder(this.getClass()
        .getClassLoader()), new DuplicatePostProcessor());
    } catch (IOException | MultiException ex) {
      throw new RuntimeException(ex);
    }
    return true;
  }

}
