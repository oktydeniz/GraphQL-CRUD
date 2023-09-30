package com.graphqlapp.graphql.controller;

import com.graphqlapp.graphql.model.Role;
import com.graphqlapp.graphql.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
class UserControllerTest {

    @Autowired GraphQlTester graphQlTester;

    @BeforeEach
    void setup() {
        createUser(new User("Test User", "test@gmail.com", Role.USER));
        createUser(new User("Test User 2", "test2@gmail.com", Role.USER));
        createUser(new User("Test User 3", "test3@gmail.com", Role.USER));
        createUser(new User("Test User 4", "test4@gmail.com", Role.ADMIN));
    }

    @Test
    void when_getAllUsers_should_return_userList(){
        // language=graphql
        String query = """
                query {
                    getAllUser{
                        id
                        userName
                        mail
                        created
                        role
                    }
                }""";
        graphQlTester.document(query).
                execute()
                .path("getAllUser")
                .entityList(User.class)
                .hasSize(4);
    }

    void createUser(User user){
        String mutation = """
                mutation{
                    createUser(userRequest:{userName:"%s", mail:"%s", role: %s}){
                        id
                        userName
                        mail
                    }
                }
                """.formatted(user.getUserName(), user.getMail(),user.getRole());

        graphQlTester.document(mutation).execute()
                .path("createUser");
    }

    @Test
    void when_createUser_should_createNewUserAndReturnUser(){
        String mutation = """
                mutation{
                    createUser(userRequest:{userName:"Test User 2", mail:"test2@gmail.com", role: ADMIN}){
                        id
                        userName
                        mail
                        role
                    }
                }
                """;
        graphQlTester.document(mutation).execute().path("createUser").entity(User.class);
    }
}