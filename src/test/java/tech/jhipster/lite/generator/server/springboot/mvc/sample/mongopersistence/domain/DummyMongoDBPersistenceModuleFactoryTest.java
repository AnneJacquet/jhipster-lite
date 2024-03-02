package tech.jhipster.lite.generator.server.springboot.mvc.sample.mongopersistence.domain;

import static tech.jhipster.lite.module.infrastructure.secondary.JHipsterModulesAssertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.TestFileUtils;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.JHipsterModulesFixture;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@UnitTest
class DummyMongoDBPersistenceModuleFactoryTest {

  private static final DummyMongoDBPersistenceModuleFactory factory = new DummyMongoDBPersistenceModuleFactory();

  @Test
  void shouldBuildModule() {
    JHipsterModuleProperties properties = JHipsterModulesFixture.propertiesBuilder(TestFileUtils.tmpDirForTest())
      .basePackage("com.jhipster.test")
      .build();

    JHipsterModule module = factory.buildModule(properties);

    assertThatModuleWithFiles(module, dummyInMemoryRepository(), inMemoryBeersResetter())
      .hasPrefixedFiles(
        "src/main/java/com/jhipster/test/sample/infrastructure/secondary",
        "BeerDocument.java",
        "MongoDBBeersRepository.java",
        "SpringDataBeersRepository.java",
        "BeersCollectionChangeUnit.java"
      )
      .hasPrefixedFiles(
        "src/test/java/com/jhipster/test/sample/infrastructure/secondary",
        "BeerDocumentTest.java",
        "MongoDBBeersRepositoryIT.java",
        "MongoDBBeersResetter.java"
      )
      .doNotHaveFiles(
        "src/main/java/com/jhipster/test/sample/infrastructure/secondary/InMemoryBeersRepository.java",
        "src/test/java/com/jhipster/test/sample/infrastructure/secondary/InMemoryBeersResetter.java"
      );
  }

  private ModuleFile dummyInMemoryRepository() {
    return file(
      "src/test/resources/projects/dummy-feature/InMemoryBeersRepository.java",
      "src/main/java/com/jhipster/test/sample/infrastructure/secondary/InMemoryBeersRepository.java"
    );
  }

  private ModuleFile inMemoryBeersResetter() {
    return file(
      "src/test/resources/projects/dummy-feature/InMemoryBeersResetter.java",
      "src/test/java/com/jhipster/test/sample/infrastructure/secondary/InMemoryBeersResetter.java"
    );
  }
}
