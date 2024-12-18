package tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.DATABASE_MIGRATION;
import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.JPA_PERSISTENCE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LIQUIBASE;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LIQUIBASE_ASYNC;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LIQUIBASE_LINTER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LOGS_SPY;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.MAVEN_JAVA;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.dbmigration.liquibase.application.LiquibaseApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class LiquibaseModuleConfiguration {

  private static final String SPRING_BOOT_DATABASE_MIGRATION = "Spring Boot - Database Migration";

  @Bean
  JHipsterModuleResource liquibaseModule(LiquibaseApplicationService liquibase) {
    return JHipsterModuleResource.builder()
      .slug(LIQUIBASE)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addIndentation().addSpringConfigurationFormat().build())
      .apiDoc(SPRING_BOOT_DATABASE_MIGRATION, "Add Liquibase")
      .organization(
        JHipsterModuleOrganization.builder().feature(DATABASE_MIGRATION).addDependency(JPA_PERSISTENCE).addDependency(LOGS_SPY).build()
      )
      .tags(liquibaseTags())
      .factory(liquibase::buildModule);
  }

  private String[] liquibaseTags() {
    return new String[] { "liquibase", "database", "migration", "spring", "spring-boot" };
  }

  @Bean
  JHipsterModuleResource liquibaseAsyncModule(LiquibaseApplicationService liquibase) {
    return JHipsterModuleResource.builder()
      .slug(LIQUIBASE_ASYNC)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addIndentation().addBasePackage().addSpringConfigurationFormat().build()
      )
      .apiDoc(SPRING_BOOT_DATABASE_MIGRATION, "Support updating the database asynchronously with Liquibase")
      .organization(JHipsterModuleOrganization.builder().addDependency(LIQUIBASE).build())
      .tags(liquibaseTags())
      .factory(liquibase::buildAsyncModule);
  }

  @Bean
  JHipsterModuleResource liquibaseLinterModule(LiquibaseApplicationService liquibase) {
    return JHipsterModuleResource.builder()
      .slug(LIQUIBASE_LINTER)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.EMPTY)
      .apiDoc(SPRING_BOOT_DATABASE_MIGRATION, "Configure a linter for the Liquibase migration scripts")
      .organization(JHipsterModuleOrganization.builder().addDependency(LIQUIBASE).addDependency(MAVEN_JAVA).build())
      .tags("server", "database", "migration", "liquibase", "linter")
      .factory(liquibase::buildLinterModule);
  }
}
