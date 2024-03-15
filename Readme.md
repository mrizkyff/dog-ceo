## Tech Stack
- Java 21
- Springboot 3.*.*
- Modules
  - ## Dog CEO
    - ### Objectives done 
      - Tell us, in which line you use spring boot singleton pattern if you use java
        - [x] I use singleton pattern to create a single instance of ie beans in spring boot. This is done by using the `@Component`, `@Service`, `@Repository` , `@Configuration`, `@Bean` and `@Controller` annotations. These annotations are used to create a single instance of the class and make it available for autowiring.
        - [x] Provide Unit Test / Integration Test
        - [x] Provide RestClient timout bean configuration
        - [x] Using lambda & method reference
        - [x] If the dog breed is shiba, then you should take only an odd number of images.
        - [x] If the dog breed is sheepdog, then you should extract the sub breed, become the
          parent breed,
        - [x] If the dog breed is terrier, then you should extract the sub breed, become the
          parent breed and fetch the images using completable futures for java]
      - Swagger
        - http://localhost:8080/swagger-ui.html
  - ## Dog RestFul API
    - ### Objective done
      - CRUD
      - Unit Test & Integration Test
      - Redis Cached
      - H2 Database