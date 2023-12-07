plugins {
    id("java")
    id("war")
    id("org.liquibase.gradle") version "2.2.0"
}

group = "ru.korolev.exchange"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.springframework:spring-beans:5.3.20")
    implementation("org.springframework:spring-context:5.3.19")
    implementation("org.springframework:spring-core:5.3.19")
    implementation("org.springframework:spring-web:5.3.19")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.springframework:spring-jdbc:5.1.10.RELEASE")
    implementation("org.springframework:spring-webmvc:5.3.20")
    implementation("javax.servlet:jstl:1.2")
    implementation("org.hibernate.validator:hibernate-validator:7.0.0.Final")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.springframework.kafka:spring-kafka:2.9.12")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.16")
}

tasks.test {
    useJUnitPlatform()
}

extra.apply {
    set("db_url", "jdbc:postgresql://localhost:5432/postgres")
    set("db_user", "postgres")
    set("db_pass", "postgres")
}

liquibase {
    activities.register("main") {
        val db_url by project.extra.properties
        val db_user by project.extra.properties
        val db_pass by project.extra.properties
        this.arguments = mapOf(
                "logLevel" to "info",
                "changeLogFile" to "src/main/resources/migration/db.changelog-master.xml",
                "url" to db_url,
                "username" to db_user,
                "password" to db_pass,
                "driver" to "org.postgresql.Driver"
        )
    }
    runList = "main"
}