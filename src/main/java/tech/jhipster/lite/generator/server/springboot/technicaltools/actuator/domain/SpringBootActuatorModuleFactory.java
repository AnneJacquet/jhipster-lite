package tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.artifactId;
import static tech.jhipster.lite.module.domain.JHipsterModule.groupId;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;

import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.shared.error.domain.Assert;

public class SpringBootActuatorModuleFactory {

  private static final GroupId SPRING_GROUP = groupId("org.springframework.boot");

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    //@formatter:off
    return moduleBuilder(properties)
      .javaDependencies()
        .addDependency(SPRING_GROUP, artifactId("spring-boot-starter-actuator"))
        .and()
      .springMainProperties()
        .set(propertyKey("management.endpoints.web.base-path"), propertyValue("/management"))
        .set(
          propertyKey("management.endpoints.web.exposure.include"),
          propertyValue("configprops", "env", "health", "info", "logfile", "loggers", "threaddump")
        )
        .set(propertyKey("management.endpoint.health.probes.enabled"), propertyValue(true))
        .set(propertyKey("management.endpoint.health.show-details"), propertyValue("always"))
        .set(propertyKey("management.endpoints.access.default"), propertyValue("none"))
        .set(propertyKey("management.endpoints.jmx.exposure.exclude"), propertyValue("*"))
        .and()
      .springLocalProperties()
        .set(propertyKey("management.endpoints.web.exposure.include"), propertyValue("*"))
        .set(propertyKey("management.endpoints.access.default"), propertyValue("unrestricted"))
        .and()
      .build();
    //@formatter:on
  }
}
