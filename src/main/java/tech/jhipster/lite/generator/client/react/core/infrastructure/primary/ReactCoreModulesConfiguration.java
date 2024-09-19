package tech.jhipster.lite.generator.client.react.core.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.CLIENT_CORE;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.client.react.core.application.ReactCoreApplicationService;
import tech.jhipster.lite.module.domain.resource.*;

@Configuration
class ReactCoreModulesConfiguration {

  public static final String REACT = "react";

  @Bean
  JHipsterModuleResource reactCoreModule(ReactCoreApplicationService react) {
    return JHipsterModuleResource.builder()
      .slug(REACT_CORE)
      .propertiesDefinition(properties())
      .apiDoc("Frontend - React", "Add React+Vite with minimal CSS")
      .organization(JHipsterModuleOrganization.builder().feature(CLIENT_CORE).addDependency(TYPESCRIPT).addDependency(PRETTIER).build())
      .tags("client", REACT)
      .factory(react::buildModule);
  }

  private JHipsterModulePropertiesDefinition properties() {
    return JHipsterModulePropertiesDefinition.builder().addProjectBaseName().addIndentation().build();
  }
}
