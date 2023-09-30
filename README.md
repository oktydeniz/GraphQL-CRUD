```sh
# READ;
{
    getAllUser{
        id
        userName
    }
}
{
    getUserById(id: 1){
        id
        userName
    }
}
# CREATE;
mutation{
    createUser(userRequest:{userName:"User Name",
    mail:"user@test.com", role:USER}){
        id
        userName
    }
}
# UPDATE;
mutation {
    updateUser(userRequest:{id:1, userName:"Update Name",
    mail:"user@test.com", role:USER}){
        id
        userName
    }
}
# DELETE;
mutation {
    deleteUser(id: 2)
}
```