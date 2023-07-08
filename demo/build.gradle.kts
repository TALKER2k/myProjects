plugins {
	java
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework:spring-core")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-web")
	implementation("org.springframework.security:spring-security-config")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
	implementation("org.springframework.security:spring-security-web")
	implementation("org.springframework.security:spring-security-config")
	implementation("org.springframework.boot:spring-boot-starter-validation:2.4.0")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()

}


//plugins {
//	java
//	id("org.springframework.boot") version "3.1.1"
//	id("io.spring.dependency-management") version "1.1.0"
//
//}
//
//group = "com.example"
//version = "0.0.1-SNAPSHOT"
//
//configurations {
//	compileOnly {
//		extendsFrom annotationProcessor
//	}
//}
//
//java {
//	sourceCompatibility = JavaVersion.VERSION_17
//
//}
//
//repositories {
//	mavenCentral()
//
//}
//
//dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-jdbc")
//	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("org.springframework:spring-core:6.0.6")
//	implementation("org.springframework.boot:spring-boot-starter-security")
//	implementation("org.springframework.security:spring-security-web")
//	implementation("org.springframework.security:spring-security-config")
//	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
//	implementation("org.springframework.security:spring-security-web:6.1.0")
//	implementation("org.springframework.security:spring-security-config:6.0.2")
//	runtimeOnly("org.postgresql:postgresql")
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.security:spring-security-test")
//
//}
//
//tasks.withType<Test> {
//	useJUnitPlatform()
//
//}
