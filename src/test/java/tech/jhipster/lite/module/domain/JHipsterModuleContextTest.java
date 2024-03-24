package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.emptyModuleBuilder;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.Logs;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.LogsSpyExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.javabuild.JavaBuildTool;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class JHipsterModuleContextTest {

  @Logs
  private LogsSpy logs;

  @Test
  void shouldGetDefaultContext() {
    Map<String, Object> context = JHipsterModuleContext.builder(emptyModuleBuilder()).build().get();

    assertThat(context)
      .hasSize(8)
      .containsEntry("baseName", "jhipster")
      .containsEntry("projectName", "JHipster Project")
      .containsEntry("packageName", "com.mycompany.myapp")
      .containsEntry("serverPort", 8080)
      .containsEntry("indentSize", 2)
      .containsEntry("javaVersion", "21")
      .containsEntry("configurationFormat", "yaml")
      .containsEntry("projectBuildDirectory", "target");
  }

  @Test
  void shouldEnrichContextWithJavaBuildTool() {
    JHipsterModuleContext context = JHipsterModuleContext.builder(emptyModuleBuilder()).build();

    Map<String, Object> newContext = context.withJavaBuildTool(JavaBuildTool.GRADLE).get();

    assertThat(newContext)
      .hasSize(8)
      .containsKey("baseName")
      .containsKey("projectName")
      .containsKey("packageName")
      .containsKey("serverPort")
      .containsKey("indentSize")
      .containsKey("javaVersion")
      .containsKey("configurationFormat")
      .containsEntry("projectBuildDirectory", "build");

    assertThat(context.get()).containsEntry("projectBuildDirectory", "target");
  }
}
