plugins {
    id("java")
    id("war")
}

group = "ru.korolev.exchange"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.springframework:spring-test:5.1.10.RELEASE")
    testImplementation("org.assertj:assertj-core:3.24.2")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("org.springframework:spring-jdbc:5.1.10.RELEASE")
    implementation("javax.servlet:jstl:1.2")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("org.springframework.kafka:spring-kafka:2.9.12")
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.12.RELEASE")
    testImplementation("org.springframework.boot:spring-boot-test:2.3.12.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.3.12.RELEASE")
    implementation("co.elastic.logging:logback-ecs-encoder:1.5.0")
    implementation("org.hibernate:hibernate-core:6.0.1.Final")

}

tasks.test {
    useJUnitPlatform()
}
