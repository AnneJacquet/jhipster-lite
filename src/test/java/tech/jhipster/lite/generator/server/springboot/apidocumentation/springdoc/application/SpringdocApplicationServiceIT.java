package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.application.SpringdocAssert.*;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.application.SpringdocAssert.assertFileContent;
import static tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoc.domain.SpringdocConstants.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.module.infrastructure.secondary.TestJHipsterModules;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class SpringdocApplicationServiceIT {

  private static final String CUSTOM_API_TITLE = "Custom API title";
  private static final String CUSTOM_API_DESCRIPTION = "Custom API description";
  private static final String CUSTOM_LICENSE_NAME = "Custom 2.0";
  private static final String CUSTOM_LICENSE_URL = "https://custom-license-url.com";
  private static final String CUSTOM_EXT_DOC_DESCRIPTION = "Custom external doc description";
  private static final String CUSTOM_EXT_DOC_URL = "https://custom-doc-url.com";

  @Autowired
  private SpringdocApplicationService springdocApplicationService;

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    // Given
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    Map<String, Object> projectConfig = new HashMap<>();
    projectConfig.put(API_TITLE_CONFIG_KEY, CUSTOM_API_TITLE);
    projectConfig.put(API_DESCRIPTION_CONFIG_KEY, CUSTOM_API_DESCRIPTION);
    projectConfig.put(API_LICENSE_NAME_CONFIG_KEY, CUSTOM_LICENSE_NAME);
    projectConfig.put(API_LICENSE_URL_CONFIG_KEY, CUSTOM_LICENSE_URL);
    projectConfig.put(API_EXT_DOC_DESCRIPTION_CONFIG_KEY, CUSTOM_EXT_DOC_DESCRIPTION);
    projectConfig.put(API_EXT_DOC_URL_CONFIG_KEY, CUSTOM_EXT_DOC_URL);
    project.getConfig().putAll(projectConfig);

    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    // When
    springdocApplicationService.init(project);

    // Then
    assertDependencies(project);
    assertJavaFiles(project);
    assertProperties(project);

    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, project.getBaseName().orElseThrow().toLowerCase());
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_API_TITLE);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_API_DESCRIPTION);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_LICENSE_NAME);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_LICENSE_URL);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_EXT_DOC_DESCRIPTION);
    assertFileContent(project, SPRINGDOC_CONFIGURATION_JAVA, CUSTOM_EXT_DOC_URL);
  }

  @Test
  @DisplayName("should init with Security JWT")
  void shouldInitWithSecurityJWT() {
    // Given
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    // When
    springdocApplicationService.initWithSecurityJWT(project);

    // Then
    assertDependencies(project);
    assertJavaFilesWithSecurityJWT(project);
    assertProperties(project);
  }
}
